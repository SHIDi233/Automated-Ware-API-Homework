package com.studio314.autowaremanagesys;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.studio314.autowaremanagesys.controller.UserController;
import com.studio314.autowaremanagesys.service.LoginService;
import com.studio314.autowaremanagesys.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AutoWareManageSysApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private LoginService loginService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    static class UserInfo {
        public int id;
        public String token;

        /**
         * 构造函数
         * @param id 用户id
         * @param token 用户token
         */
        public UserInfo(int id, String token) {
            this.id = id;
            this.token = token;
        }
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * 注册测试用户
     * @return 用户信息
     * @throws Exception 异常
     */
    UserInfo registerTestUser() throws Exception {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("name", "TestUser");
        userInfo.put("mail", "fuckya@mail.com");
        userInfo.put("password", "114514");

        UserInfo info = new UserInfo(0, "");

        {
            MvcResult mvcResult = mockMvc.perform(post("/users/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(userInfo)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1))
                    .andExpect(jsonPath("$.msg").value("success"))
                    .andExpect(jsonPath("$.data").value("注册成功"))
                    .andReturn();
            Map<String, Object> responseMap = JSON.parseObject(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Object>>() {});
            Map<?, ?> data = (Map<?, ?>) responseMap.get("data");
            info.id = Integer.parseInt(data.get("id").toString());
        }
        {
            MvcResult mvcResult = mockMvc.perform(post("/users/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(new HashMap<>(){
                                {
                                    put("mail", userInfo.get("mail"));
                                    put("password", userInfo.get("password"));
                                }
                            })))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1))
                    .andExpect(jsonPath("$.msg").value("success"))
                    .andReturn();
            Map<String, Object> responseMap = JSON.parseObject(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Object>>() {});
            Map<?, ?> data = (Map<?, ?>) responseMap.get("data");
            info.token = data.get("token").toString();
        }

        return info;
    }

    /**
     * 登录管理员用户
     * @return 管理员用户信息
     * @throws Exception 异常
     */
    UserInfo loginAdminUser() throws Exception {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("mail", "admin@mail.com");
        userInfo.put("password", "114514");

        UserInfo info = new UserInfo(0, "");


        MvcResult mvcResult = mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(userInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andReturn();
        Map<String, Object> responseMap = JSON.parseObject(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Object>>() {});
        Map<?, ?> data = (Map<?, ?>) responseMap.get("data");
        info.token = data.get("token").toString();

        return info;
    }

    /**
     * 测试查询所有货物
     * @throws Exception 异常
     */
    void queryAllCargos() throws Exception {
        UserInfo userInfo = loginAdminUser();

        mockMvc.perform(get("/cargos")
                        .header("token", userInfo.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
    }

    /**
     * 测试查询某种货物
     * @throws Exception 异常
     */
    void queryCargo() throws Exception {
        UserInfo userInfo = loginAdminUser();

        mockMvc.perform(get("/cargos/1")
                        .header("token", userInfo.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
    }

    /**
     * 测试创建仓库
     * @param userInfo 用户信息
     * @return 仓库id
     * @throws Exception 异常
     */
    List<Integer> createWare(UserInfo userInfo) throws Exception {
        List<Integer> wareIds = new ArrayList<>();
        {
            MvcResult mvcResult = mockMvc.perform(post("/wares")
                            .header("token", userInfo.token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(new HashMap<>(){
                                {
                                    put("wareName", "TestWare1");
                                }
                            })))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1))
                    .andExpect(jsonPath("$.msg").value("success"))
                    .andReturn();
            Map<String, Object> responseMap = JSON.parseObject(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Object>>() {});
            Map<?, ?> data = (Map<?, ?>) responseMap.get("data");
            wareIds.add(Integer.parseInt(data.get("wareId").toString()));
        }
        {
            MvcResult mvcResult = mockMvc.perform(post("/wares")
                            .header("token", userInfo.token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(new HashMap<>(){
                                {
                                    put("wareName", "TestWare2");
                                }
                            })))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1))
                    .andExpect(jsonPath("$.msg").value("success"))
                    .andReturn();
            Map<String, Object> responseMap = JSON.parseObject(mvcResult.getResponse().getContentAsString(), new TypeReference<Map<String, Object>>() {});
            Map<?, ?> data = (Map<?, ?>) responseMap.get("data");
            wareIds.add(Integer.parseInt(data.get("wareId").toString()));
        }
        return wareIds;
    }

    /**
     * 测试查询仓库
     * @param userInfo 用户信息
     * @throws Exception 异常
     */
    void queryWare(UserInfo userInfo) throws Exception {
        mockMvc.perform(get("/wares")
                        .header("token", userInfo.token)
                        .param("uID", String.valueOf(userInfo.id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
    }

    /**
     * 测试入库
     * @param wareID 仓库id
     * @param cargoIDs 货物id列表
     * @throws Exception 异常
     */
    void createStock(int wareID, List<Integer> cargoIDs) throws Exception {
        List<Integer> stockIDs = new ArrayList<>();

        for (int cargoID : cargoIDs) {
            mockMvc.perform(post("/stocks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(new HashMap<>(){
                                {
                                    put("wareID", wareID);
                                    put("cargoID", cargoID);
                                    put("stock", 100);
                                }
                            })))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1))
                    .andExpect(jsonPath("$.msg").value("success"));
        }
    }

    /**
     * 测试出库
     * @param wareID 仓库id
     * @param cargoIDs 货物id列表
     * @throws Exception 异常
     */
    void outStock(int wareID, List<Integer> cargoIDs) throws Exception {
        List<Integer> stockIDs = new ArrayList<>();

        for (int cargoID : cargoIDs) {
            mockMvc.perform(put("/stocks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JSON.toJSONString(new HashMap<>() {
                                {
                                    put("wareID", wareID);
                                    put("cargoID", cargoID);
                                    put("stock", 100);
                                }
                            })))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1))
                    .andExpect(jsonPath("$.msg").value("success"));
        }
    }

    /**
     * 测试查询所有货物
     * @throws Exception 异常
     */
    void queryAllStock(int wareID) throws Exception {
        mockMvc.perform(get("/wares/" + wareID + "/stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
    }

}
