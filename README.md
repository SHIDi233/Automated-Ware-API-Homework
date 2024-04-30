# assignment1-javaEE

## API Design

[ApiDesignAndTestDoc.md](./ApiDesignAndTestDoc.md)



## Implementation and Display

### Spring MVC and boot

**Environment:**

- jdk - 17

- spring boot - 3.2.5

**Implements and Results：**

​	<img src="images/image-20240429203532182.png" alt="image-20240429203532182" style="zoom:50%;" /> 

​	<img src="images/image-20240429203618941.png" alt="image-20240429203618941" style="zoom:33%;" /> 



### Data Repository

**Environment：**

- MyBatis - 3.0.3
- MySQL - 8



**Database Design：**

<img src="./images/ware.png" alt="ware" style="zoom:33%;" />



**Implements：**

​	We use mybatis to connect to database by mapper annotation.

​	<img src="images/image-20240429204200085.png" alt="image-20240429204200085" style="zoom:33%;" /> 



### Testing

**Environment：**

- boot testing framework
- mockMVC



**Implements：**

​	We use MockMVC to simulate user HTTP request operations to complete testing

​	<img src="images/image-20240429204900042.png" alt="image-20240429204900042" style="zoom: 50%;" /> 



**Results：**

​	All test cases passed

​	<img src="images/image-20240429205322335.png" alt="image-20240429205322335" style="zoom: 33%;" /> 

​	In addition, we also use APIfox to test APIs.

​	<img src="images/image-20240429205722783.png" alt="image-20240429205722783" style="zoom: 25%;" />   <img src="images/image-20240429205750162.png" alt="image-20240429205750162" style="zoom: 25%;" /> 

​	**Complete test report：** [AutoWareManageTestDoc.md](https://github.com/SHIDi233/Automated-Ware-API-Homework/blob/master/ApiDesignAndTestDoc.md)



### Authentication and Session Control

**Environment：**

- spring security - 6.2.4
- jjwt - 0.11.2
- redis - 6.0.16



**Implements：**

1. Defined a `SecurityFilterChain` bean to configure HttpSecurity.
   <img src="images/image-20240430190743693.png" alt="image-20240430190743693" style="zoom:25%;" />

2. Implement `UserDetailsService` and `UserDetails` to define customized authentication process

3. Define a filter `JwtAuthenticationTokenFilter` before `WebAsyncManagerIntegrationFilter`. When the user login in, the jwt will be storaged in the redis cache. When the user request for other api with jwt, this filter will check the jwt and read user's information from redis cache.
   <img src="images/image-20240430191527470.png" alt="image-20240430191527470" style="zoom:33%;" /> 

4. When the user login, `UserDetailsService` will give him a `GrantedAuthority` class as role. Then we use annotation `@PreAuthorize("hasRole('admin')")` to specify the roles allowed by the interface.

   <img src="./images/image-20240430192013916.png" alt="image-20240430192013916" style="zoom: 25%;" /> 



### Caching

**Environment：**

- redis - 6.0.16

**Implement：**

The main components of Spring Cache include:

1. `@Cacheable`：triggers caching, you can specify the key and the name of the cache.
2. `@CachePut`：puts the return value directly into the cache without affecting method execution.
3. `@CacheEvict`：remove the cache.



- We use Redis as cache carrier. Set up redis configuration such as IP, port, password, expiration time, etc.

​		<img src="images/image-20240430182854030.png" alt="image-20240430182854030" style="zoom:33%;" />

- Extend `CachingConfigurerSupport` to config cache.

​		<img src="images/image-20240430182821899.png" alt="image-20240430182821899" style="zoom: 25%;" />

- Finally, we use annotation `Cacheable` to implement.

​		<img src="images/image-20240430192737281.png" alt="image-20240430192737281" style="zoom:33%;" />



**Results：**

​	Twice requests time comparison：

​	**request all the warehouses：**

- <img src="./images/image-20240430190544727.png" alt="image-20240430190544727" style="zoom:25%;" /> 

​		216ms



- <img src="./images/image-20240430190606312.png" alt="image-20240430190606312" style="zoom:25%;" /> 

​		90ms	

​	**request for all cargoes：**

- <img src="./images/image-20240430192455533.png" alt="image-20240430192455533" style="zoom:25%;" /> 

​		242ms



- <img src="./images/image-20240430192540451.png" alt="image-20240430192540451" style="zoom:25%;" /> 

​		127ms

​	A significant speed increase can be observed, proving that the caching mechanism is in effect.



### Rate Limiting

**Environment：**

- google.guava - 20.0



**Profile：**

​	The Token Bucket is a method used for rate limiting, where the bucket holds a certain number of tokens. Tokens are added to the bucket at a predetermined rate. When the bucket is full, any additional tokens overflow and are discarded, preventing the bucket from exceeding its capacity.  

​	The evaluation of traffic compliance is based on whether the number of tokens in the bucket is sufficient to forward the packets. Each packet that needs to be forwarded must obtain a certain number of tokens from the bucket (the specific number depends on the size of the packet) to be forwarded normally. 

​	If there are enough tokens in the bucket to forward the packet, the traffic is said to be compliant or within the agreed value. If there are not enough tokens, the traffic is considered non-compliant or exceeding the limit.



**Implements：**

- implement `@Limiting`

​	<img src="images/image-20240430184105748.png" alt="image-20240430184105748" style="zoom:33%;" /> 

​	<img src="images/image-20240430184122195.png" alt="image-20240430184122195" style="zoom:25%;" /> 



**Results：**

​	When we request frequently, the server will return:

​	 <img src="images/image-20240430190918218.png" alt="image-20240430190918218" style="zoom:33%;" />



### Log

**Implements：**

​	We use annotation `@Slf4j` and `log.info()` to output log.

​	<img src="images/image-20240430200427923.png" alt="image-20240430200427923" style="zoom:33%;" /> 



**Display：**

​	<img src="images/image-20240430200240629.png" alt="image-20240430200240629" style="zoom:33%;" /> 



# Screenshots

## 登录注册

### 注册

输入邮箱、密码、名字即可完成注册。

<img src="./images/image-20240430152028471.png" alt="image-20240430152028471" style="zoom:25%;" />	

### 登录

输入邮箱与密码实现用户登录。

<img src="./images/image-20240430152321870.png" alt="image-20240430152321870" style="zoom:25%;" />
登录后右上角显示登录成功并跳转到首页。

<img src="./images/image-20240430152333654.png" alt="image-20240430152333654" style="zoom:25%;" />

## 仓库库区操作

### 仓库

#### 添加仓库

点击添加仓库，输入要添加的仓库名称，即可添加。

<img src="./images/image-20240430153042340.png" alt="image-20240430153042340" style="zoom:25%;" />

点击创建仓库后，右上角显示创建成功，并且可在仓库列表中查看添加后的仓库ID与名称。

<img src="./images/image-20240430153144342.png" alt="image-20240430153144342" style="zoom:25%;" />

#### 删除仓库

点击表格中的仓库进行删除，成功后右上角会显示删除成功字样。

<img src="./images/image-20240430153159952.png" alt="image-20240430153159952" style="zoom:25%;" />

#### 仓库列表查看

在本页面可以看到用户所拥有的所有仓库ID与名称。

<img src="./images/image-20240430153238238.png" alt="image-20240430153238238" style="zoom:25%;" />

### 库存操作

#### 入库

输入对应仓库想入库的货物ID与入库的货物数目，再点击入库按钮即可实现货物入库。

<img src="./images/image-20240430155109596.png" alt="image-20240430155109596" style="zoom:25%;" />

入库成功后右上角会显示入库成功字样，并且在该页面可以看到刚刚入库的20个通讯工具。

<img src="./images/image-20240430155149042.png" alt="image-20240430155149042" style="zoom:25%;" />



#### 出库

输入仓库内已有的想出库的货物ID和对应出库数目，点击出库按钮即可实现货物出库。

<img src="./images/image-20240430155210673.png" alt="image-20240430155210673" style="zoom:25%;" />

出库成功后右上角显示出库成功字样，可以在库存表中看到通讯工具库存变为10，成功出库了10个商品。

<img src="./images/image-20240430155221037.png" alt="image-20240430155221037" style="zoom:25%;" />

#### 库存查看

在该页面可以查看仓库中所有库存名称、简介与库存数。

<img src="./images/image-20240430155256347.png" alt="image-20240430155256347" style="zoom:25%;" />

### 仓库人员

#### 角色添加

输入想要添加人物的邮箱和想让其担任的角色，点击添加即可实现角色的添加。

<img src="./images/image-20240430155451183.png" alt="image-20240430155451183" style="zoom:25%;" />

添加后可看到右上角的添加成功字样并在角色列表中看到刚刚添加的人物名称和角色(TestUser) 。

<img src="./images/image-20240430155551911.png" alt="image-20240430155551911" style="zoom:25%;" />

#### 角色查看

在该页面可以看到所有当前仓库内的人物名称和角色。

<img src="./images/image-20240430155726860.png" alt="image-20240430155726860" style="zoom:25%;" />

## 物料管理操作

### 分类

我们对物料，即货物的种类做全局规定。

#### 节点添加

##### 根节点添加

对货物种类增加根节点，输入根节点名称及描述，点击创建。

<img src="./images/image-20240430155956810.png" alt="image-20240430155956810" style="zoom:25%;" />

创建后即可在树表中看到通讯工具根节点已被加入。

<img src="./images/image-20240430160009806.png" alt="image-20240430160009806" style="zoom:25%;" />

##### 子节点添加

点击根节点加号，输入子节点名称及描述，点击创建。

<img src="./images/image-20240430160206507.png" alt="image-20240430160206507" style="zoom:25%;" />

创建后可在树表中看大通讯工具下手机子类已被创建。

<img src="./images/image-20240430160226773.png" alt="image-20240430160226773" style="zoom:33%;" />

##### 节点列表查看

当前页面可以查看所有种类（以树形结构展示）。

<img src="./images/image-20240430160304198.png" alt="image-20240430160304198" style="zoom:25%;" />

##### 节点修改

点击节点中的修改，填入修改后的节点名称与描述，点击提交。

<img src="./images/image-20240430160324899.png" alt="image-20240430160324899" style="zoom:25%;" />

修改后可在页面中看到节点名称已经更改。

<img src="./images/image-20240430160826278.png" alt="image-20240430160826278" style="zoom:33%;" />
