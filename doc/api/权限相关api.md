### 接口与权限对应关系

|      接口      |      权限      | 说明 |
| :------------: | :------------: | :--: |
| /api/student/* |    STUDENT     |      |
| /api/student/* |    TEACHER     |      |
|  /api/dept/*   |  DEPT_MANAGER  |      |
|   /api/all/*   | SCHOOL_MANAGER |      |

> 所有需要鉴权的接口（身份认证），都需要传token
>
> 只有 `/api/login` `/api/bank/update`  `/api/yearTerm` 接口不需要传token 

### 其它
- 如果在数据库的表中不带上teacherId，其它teacherId也可以对其它教师端课程进行修改或删除
