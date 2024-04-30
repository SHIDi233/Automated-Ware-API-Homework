---
title: AutoWareManage
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.23"

---

# AutoWareManage

Base URLs:

# Authentication

* API Key (apikey-header-token)
    - Parameter Name: **token**, in: header. 

# 用户

## POST 用户登录

POST /user/login

权限允许：所有

> Body 请求参数

```json
{
  "mail": "testnew",
  "password": "12345"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjozfQ.EiAVkPoalHhJ3qdE1Eqb-zyjFbSafiJA0FZ1vNTYWMgZYfUk0kpQlrTFMXaThDveUjK2IdnovG7ht1mguxhYaw"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» data|object|true|none||none|
|»» token|string|true|none||none|
|» code|integer|true|none||none|
|» msg|string|true|none||none|

## POST 用户注册

POST /user/register

权限允许：所有

> Body 请求参数

```json
{
  "name": "testnew",
  "mail": "testnew",
  "password": "12345"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|JSESSIONID|cookie|string| 否 |none|
|body|body|object| 否 |none|
|» name|body|string| 是 |none|
|» mail|body|string| 是 |none|
|» password|body|string| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": "注册成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|string|true|none||none|

# 仓库

## POST 创建仓库

POST /wares

> Body 请求参数

```json
{
  "wareName": "testnewWare"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "wareID": 13
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» wareID|integer|true|none||none|

## GET 查看仓库列表

GET /wares

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "wareID": 8,
      "wareName": "waretest5"
    },
    {
      "wareID": 9,
      "wareName": "waretest6"
    },
    {
      "wareID": 10,
      "wareName": "waretest10"
    },
    {
      "wareID": 11,
      "wareName": "waretest5"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» wareID|integer|true|none||none|
|»» wareName|string|true|none||none|

## GET 查看仓库库存

GET /wares/{wID}/stock

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|wID|path|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "cargoID": 7,
      "cargoName": "testCargo3",
      "num": 100,
      "cargoDescription": "testCargo3Des"
    },
    {
      "cargoID": 8,
      "cargoName": "childCargo51",
      "num": 3,
      "cargoDescription": "childCargo51Des"
    },
    {
      "cargoID": 12,
      "cargoName": "childCargo72",
      "num": 6,
      "cargoDescription": "childCargo72Des"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» cargoID|integer|true|none||none|
|»» cargoName|string|true|none||none|
|»» num|integer|true|none||none|
|»» cargoDescription|string|true|none||none|

## POST 入库

POST /wares/{wID}/stock

> Body 请求参数

```json
{
  "cargoID": 25,
  "stockNum": 1
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|wID|path|string| 是 |none|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## PUT 出库

PUT /wares/{wID}/stock

> Body 请求参数

```json
{
  "cargoID": 25,
  "stockNum": 1
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|wID|path|integer| 是 |none|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## DELETE 删除仓库

DELETE /wares/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## POST 成员添加

POST /wares/{wID}/auth

> Body 请求参数

```json
{
  "email": "123",
  "role": "user"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|wID|path|integer| 是 |none|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## GET 成员查看

GET /wares/{wID}/auth

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|wID|path|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "uID": 1,
      "role": "user",
      "name": "apple"
    },
    {
      "uID": 2,
      "role": "user",
      "name": "apple"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» uID|integer|true|none||none|
|»» role|string|true|none||none|
|»» name|string|true|none||none|

## DELETE 成员删除

DELETE /wares/{wID}/auth/{uID}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|wID|path|integer| 是 |none|
|uID|path|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

# 货物

## GET 查看某种货物种类详细

GET /cargo/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "cargoId": 11,
    "cargoName": "childCargo71",
    "cargoDescription": "childCargo71Des",
    "parent": 7
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» cargoID|integer|true|none||none|
|»» cargoName|string|true|none||none|
|»» cargoDescription|string|true|none||none|
|»» parent|integer|true|none||none|

## DELETE 删除货物种类

DELETE /cargo/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## PUT 修改货物种类

PUT /cargo/{id}

> Body 请求参数

```json
{
  "cargoName": "222t",
  "cargoDescription": "success"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|integer| 是 |none|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## POST 增加货物根种类

POST /cargo

> Body 请求参数

```json
{
  "cargoName": "222t",
  "cargoDescription": "success"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» cargoId|integer|true|none||none|

## GET 获得全部货物种类

GET /cargo

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "name": "testCargo1",
      "description": "testCargo1Des",
      "childrenTypes": [
        {
          "name": "childCargo51",
          "description": "childCargo51Des",
          "childrenTypes": null,
          "id": 8
        },
        {
          "name": "childCargo52",
          "description": "childCargo52Des",
          "childrenTypes": null,
          "id": 9
        }
      ],
      "id": 5
    },
    {
      "name": "testCargo2",
      "description": "testCargo2Des",
      "childrenTypes": [
        {
          "name": "childCargo61",
          "description": "childCargo61Des",
          "childrenTypes": null,
          "id": 10
        }
      ],
      "id": 6
    },
    {
      "name": "testCargo3",
      "description": "testCargo3Des",
      "childrenTypes": [
        {
          "name": "childCargo71",
          "description": "childCargo71Des",
          "childrenTypes": null,
          "id": 11
        },
        {
          "name": "childCargo72",
          "description": "childCargo72Des",
          "childrenTypes": null,
          "id": 12
        },
        {
          "name": "childCargo73",
          "description": "childCargo73Des",
          "childrenTypes": null,
          "id": 13
        }
      ],
      "id": 7
    },
    {
      "name": "testCargo66",
      "description": "testCargo66Des",
      "childrenTypes": null,
      "id": 15
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» name|string|true|none||none|
|»» description|string|true|none||none|
|»» childrenTypes|[object]¦null|true|none||none|
|»»» name|string|true|none||none|
|»»» description|string|true|none||none|
|»»» childrenTypes|null|true|none||none|
|»»» id|integer|true|none||none|
|»» id|integer|true|none||none|

## POST 增加货物子种类

POST /cargo/{cargoID}

> Body 请求参数

```json
{
  "cargoName": "222t",
  "cargoDescription": "success"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|cargoID|path|integer| 是 |none|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» cargoId|integer|true|none||none|

# 数据模型

