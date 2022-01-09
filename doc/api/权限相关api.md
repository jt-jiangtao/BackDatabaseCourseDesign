### 接口与权限对应关系

|      接口      |  权限   | 说明 |
| :------------: | :-----: | :--: |
| /api/student/* | STUDENT |      |
| /api/student/* | TEACHER |      |
|  /api/dept/*   |  DEPT   |      |
|   /api/all/*   |   ALL   |      |

> 所有需要鉴权的接口（身份认证），都需要传token
>
> 文档中未写全

