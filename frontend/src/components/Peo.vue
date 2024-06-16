<template>
    <div style="display: flex;width:100%">
        <div style="width:25%;float: left">
            <el-card style="width: 100%">
                <el-input v-model="search" size="small" placeholder="Type to search" />
                <el-table :data="filterTableData" style="width: 100%" @row-click="click" v-loading="loading" :row-class-name="tableRowClassName">
                    <el-table-column prop="wareName" label="仓库名称" style="width:100%" />
                </el-table>
            </el-card>
        </div>
        <div style="width:74%;margin-left: 1%;margin-right: 5%;">
            <el-card style="width: 100%">
                <div>
                    <div style="float: left;">
                        <h4>当前仓库：{{ nowName }}</h4>
                    </div>
                    <div style="float: right;">
                        <el-button type="success" :disabled="nowName=='无'" size="small" @click="dialogFormVisible=true;">添加角色</el-button>
                    </div>
                </div>
                <div style="margin-top: 40px;">
                    <el-table
                        ref="multipleTableRef"
                        :data="tableData_2"
                        style="width: 100%;"
                        border
                        v-loading="loading_2"
                    >
                        <el-table-column type="selection" width="38px" />
                        <el-table-column property="name" label="名称"/>
                        <el-table-column property="role" label="角色"/>
                        <!-- <el-table-column property="num" label="当前库存" /> -->
                        <el-table-column fixed="right" width="80">
                            <template #default="scope">
                                <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button> -->
                                <el-button type="danger" :disabled="nowName=='无'" size="small" @click="del(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <el-dialog v-model="dialogFormVisible" title="添加角色" width="500">
                    <el-form  ref="ruleFormRef" :model="form" label-width="auto" :rules="rules" style="max-width: 600px">
                        <el-form-item label="人物邮箱:" prop="name">
                            <el-input v-model="form.id"/>
                        </el-form-item>
                        <el-form-item label="角色" prop="description">
                            <el-input disabled="true" v-model="form.num" />
                        </el-form-item>
                        <!-- <el-form-item label="库区容量:" prop="capacity">
                            <el-input type="number" v-model="form.capacity" />
                        </el-form-item>
                        <el-form-item label="库区类型:" prop="cargoType">
                            <el-input type="number" v-model="form.cargoType" />
                        </el-form-item> -->
                    </el-form>
                    <div style="float:flex;text-align: center;">
                        <el-button v-loading="loading_3" type="primary"  @click="commit(ruleFormRef)">添加</el-button>
                    </div>
                </el-dialog>
                <el-dialog v-model="dialogFormVisible_2" title="出库" width="500">
                    <el-form  ref="ruleFormRef" :model="form" label-width="auto" :rules="rules" style="max-width: 600px">
                        <el-form-item label="货物ID:" prop="name">
                            <el-input v-model="form.id"/>
                        </el-form-item>
                        <el-form-item label="出库数目:" prop="description">
                            <el-input v-model="form.num" />
                        </el-form-item>
                        <!-- <el-form-item label="库区容量:" prop="capacity">
                            <el-input type="number" v-model="form.capacity" />
                        </el-form-item>
                        <el-form-item label="库区类型:" prop="cargoType">
                            <el-input type="number" v-model="form.cargoType" />
                        </el-form-item> -->
                    </el-form>
                    <div style="float:flex;text-align: center;">
                        <el-button v-loading="loading_3" type="primary"  @click="commit_2(ruleFormRef)">出库</el-button>
                    </div>
                </el-dialog>
            </el-card>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import { ref } from 'vue';
    import { reactive } from 'vue'
    import axios from 'axios'
    axios.defaults.baseURL = '/api' 
    import { ElNotification } from 'element-plus'
    import { onMounted, computed } from 'vue';
    import type { FormInstance, FormRules } from 'element-plus'

    const loading = ref(true);
    const loading_2 = ref(false);
    const loading_3 = ref(false);

    let search = ref(' ')
    interface User {
        id:number
        name: string
    }
    
    const tableRowClassName = ({
        row,
    }: {
        row: User
        }) => {
            if (row.id == nowID.value) {
                return 'warning-row'
            }
            return ''
    }

    const dialogFormVisible=ref(false);
    const dialogFormVisible_2=ref(false);

    interface RuleForm {
        id:string
        num:string
    };
    const form = reactive<RuleForm>({
        id:'',
        num:'user',
    });
    const ruleFormRef = ref<FormInstance>()
    const rules = reactive<FormRules<RuleForm>>({
        // name:[
        //     { required: true, message: 'Please input name', trigger: 'blur'},
        // ],
        // cargoType:[
        //     { required: true, message: 'Please input cargoType', trigger: 'blur'},
        // ],
        // capacity:[
        //     { required: true, message: 'Please input capacity', trigger: 'blur'},
        // ],
        // description:[
        //     { required: true, message: 'Please input role', trigger: 'blur'},
        // ],
    });

    let tableData : User[]=[
        {
            id: 1,
            name: '',
        }
    ];
    const tableData_2 = ref([

    ]);
    const filterTableData = computed(() =>
        tableData.filter(
            (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
        )
    )

    const nowID = ref(0)
    const nowName = ref('无')

    const click =(
        row: any,
    )=>{
        console.log(row);
        nowID.value=row.wareID;
        nowName.value = row.wareName;
        // alert(nowID.value);
        load();
    };

    const load=()=>{    
        loading_2.value=true;
        axios.get(`/wares/${nowID.value}/auth`)
        .then(function (res) {
            if(res.data.msg=='success'){
                tableData_2.value = res.data.data.data;
                loading_2.value=false;
            }
            else{
                ElNotification({
                    title: '错误',
                    message: res.data.data,
                    type: 'error',
                })
                loading_2.value=false;
            }
        })
        .catch(function (error) {
            ElNotification({
                title: '网络错误',
                message: error,
                type: 'error',
            })
            loading_2.value=false;  
        });
    };

    const refresh=()=>{
        loading.value=true;
        axios.get('/wares')
        .then(function (res) {
            if(res.data.msg=='success'){
                tableData = res.data.data;
                loading.value=false;
                console.log(tableData);
                search.value='';
            }
            else{
                ElNotification({
                    title: '错误',
                    message: res.data.data,
                    type: 'error',
                })
            }
        })
        .catch(function (error) {
            ElNotification({
                title: '网络错误',
                message: error,
                type: 'error',
            })
        });
    };

    onMounted(() => {
        refresh();
    });
    const commit=(formEl: FormInstance | undefined)=>{
        // if (!formEl) {return;}
        formEl.validate((valid, fields) => {
            if (true) {
                loading_3.value=true;
                axios.post(`/wares/${nowID.value}/auth`, 
                { "email":form.id,"role": form.num})
                .then(function (res) {
                    if(res.data.msg=='success'){
                        ElNotification({
                            title: 'Success',
                            message: '添加成功',
                            type: 'success',
                        });
                        form.id='';
                        loading_3.value=false;
                        dialogFormVisible.value=false;
                        refresh();
                        load();
                    }
                    else{
                        ElNotification({
                            title: '创建错误',
                            message: res.data.message,
                            type: 'error',
                        });
                        loading_3.value=false;
                    }
                })
                .catch(function (error) {
                    ElNotification({
                        title: '网络错误',
                        message: error,
                        type: 'error',
                    });
                    loading_3.value=false;
                });
            } else {
                return;
            }
        });
    }
    const del=(a,b)=>{
        loading_3.value=true;
        // axios.delete(`/wares/${nowID.value}/auth`, 
        // {"cargoID":Number(form.id), "uID":Number(b.wareID)})
        axios.delete(`/wares/${nowID.value}/auth/${b.uID}`)
        .then(function (res) {
            if(res.data.msg=='success'){
                ElNotification({
                    title: 'Success',
                    message: '删除成功',
                    type: 'success',
                });
                loading_3.value=false;
                dialogFormVisible_2.value=false;
                refresh();
                load();
            }
            else{
                ElNotification({
                    title: '创建错误',
                    message: res.data.message,
                    type: 'error',
                });
                loading_3.value=false;
            }
        })
        .catch(function (error) {
            ElNotification({
                title: '网络错误',
                message: error,
                type: 'error',
            });
            loading_3.value=false;
        });

    }
</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>