### 登录==ok==

> POST  /api/login
>

#### 请求参数

##### Object

|   属性   |  类型  |        说明        | 备注 | 是否必填 |
| :------: | :----: | :----------------: | :--: | :------: |
| username |  int   | 用户名，学号或工号 |      |    是    |
| password | string |        密码        |      |    是    |

#### 返回结果

##### data

|   属性   |       类型       |          说明          |                      备注                       |
| :------: | :--------------: | :--------------------: | :---------------------------------------------: |
|  token   |      string      |         token          |                                                 |
| roleList |   list\<role>    | 角色，用户所对应的身份 | STUDENT、TEACHER、SUPER_MANAGER、DEPT_MANAGER等 |
| username |       int        |         用户名         |                                                 |
|   info   | Student\|Teacher |      登录用户信息      |                                                 |

##### role
|   属性   |  类型  |   说明   |           备注           |
| :------: | :----: | :------: | :----------------------: |
|    id    |  int   |  角色id  |                          |
| roleName | String | 角色名称 | 可以根据角色名来显示功能 |
##### Teacher

|     属性      |  类型  |    说明    | 备注 |
| :-----------: | :----: | :--------: | :--: |
|   deptName    | string |  学院名称  |      |
|   groupName   | string | 课题组编号 |      |
|   teacherId   | string |  教师编号  |      |
|  taskGroupId  |  int   | 课题组编号 |      |
| specialtyName | string |  专业名称  |      |
|      sex      | string |    性别    |      |
|  specialtyId  |  int   |  专业编号  |      |
|     name      | string |    姓名    |      |
|    deptId     |  int   |  学院名称  |      |

##### Student

|     属性      |  类型  |   说明   | 备注 |
| :-----------: | :----: | :------: | :--: |
|   studentId   | string | 学生编号 |      |
|   deptName    | string | 学院名称 |      |
|    classId    |  int   | 班级编号 |      |
|   nowGrade    |  int   |   级别   | 2019 |
| specialtyName | string | 专业名称 |      |
|      sex      | string |   性别   |      |
|  specialtyId  |  int   | 专业编号 |      |
|     name      | string |   姓名   |      |
|    deptId     |  int   | 学院编号 |      |
|  classNumber  |  int   |  班级号  |      |

#### 成功示例

- 学生登录

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/login",
    "data": {
        "identity": "STUDENT",
        "roleList": [
            {
                "roleName": "STUDENT",
                "id": 1,
                "status": true
            }
        ],
        "username": "201921098412",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTWVNURU0iLCJleHAiOjE2NDQzMzU4MTgsImlhdCI6MTY0MjI2MjIxOCwianRpIjoiMTA1ZmFlNTMtZTYwMi00M2ExLWI3NDAtNzE2ZDdlYzkyZDkwIiwidXNlcm5hbWUiOiIyMDE5MjEwOTg0MTIifQ.ekP9MKiRldmJwoWY2kngSh9uLK-qiLQHHI6DWAfseu4",
        "info": {
            "studentId": "201921098412",
            "deptName": "计算机科学学院",
            "classId": 4,
            "nowGrade": 2019,
            "specialtyName": "软件工程",
            "sex": "男",
            "specialtyId": 1,
            "name": "蒋涛",
            "deptId": 1,
            "classNumber": 1904
        }
    },
    "time": "2022-01-15 23:56:58"
}
```

- 教师登录

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/login",
    "data": {
        "identity": "TEACHER",
        "roleList": [
            {
                "roleName": "TEACHER",
                "id": 2,
                "status": true
            }
        ],
        "username": "3725001",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTWVNURU0iLCJleHAiOjE2NDQzMzYyMDEsImlhdCI6MTY0MjI2MjYwMSwianRpIjoiOWEzNWQxMjAtM2RmYS00ZGUyLTgxYzgtNTU2MDAyMjMxNDczIiwidXNlcm5hbWUiOiIzNzI1MDAxIn0.nPL_-TqQrcPB0fCARVqTD1hLj7vX-4mnB_buLqh3ilA",
        "info": {
            "deptName": "计算机科学学院",
            "groupName": "软工课题组1",
            "teacherId": "3725001",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "sex": "男",
            "specialtyId": 1,
            "name": "曾广平",
            "deptId": 1
        }
    },
    "time": "2022-01-16 00:03:21"
}
```



### 更改账号密码==ok==

> POST  /api/bank/update

#### 请求参数

##### Body
|   属性   |  类型  |  说明  |            备注            | 是否必填 |
| :------: | :----: | :----: | :------------------------: | :------: |
| username | string | 用户名 |                            |    是    |
| password | String |  密码  |                            |    是    |
|          | String | 新密码 | 必须包含字母和数字，6-18位 |    是    |


#### 返回参数

##### data

null

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/bank/update",
    "time": "2022-01-15 22:50:06"
}
```



### 选择学年==ok==

> GET /api/yearTerm
>

#### 返回结果

##### data

|   属性    | 类型 | 说明 | 备注 |
| :-------: | :--: | :--: | :--: |
| yearTerms | list |      |      |

##### yearTerms

| 属性 |  类型  | 说明 |        备注        |
| :--: | :----: | :--: | :----------------: |
| year | string | 学年 |     2021-2022      |
| term | string | 学期 | 第一学期、第二学期 |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/yearTerm",
    "data": {
        "year": "2021-2022",
        "yearTerms": [
            {
                "id": 1,
                "year": "2018-2019",
                "term": "第一学期"
            },
            {
                "id": 2,
                "year": "2018-2019",
                "term": "第二学期"
            },
            {
                "id": 3,
                "year": "2019-2020",
                "term": "第一学期"
            },
            {
                "id": 4,
                "year": "2019-2020",
                "term": "第二学期"
            },
            {
                "id": 5,
                "year": "2020-2021",
                "term": "第一学期"
            },
            {
                "id": 6,
                "year": "2020-2021",
                "term": "第二学期"
            },
            {
                "id": 7,
                "year": "2021-2022",
                "term": "第一学期"
            },
            {
                "id": 8,
                "year": "2021-2022",
                "term": "第二学期"
            },
            {
                "id": 9,
                "year": "2022-2023",
                "term": "第一学期"
            },
            {
                "id": 10,
                "year": "2022-2023",
                "term": "第二学期"
            },
            {
                "id": 11,
                "year": "2023-2024",
                "term": "第一学期"
            },
            {
                "id": 12,
                "year": "2023-2024",
                "term": "第二学期"
            }
        ],
        "term": "第一学期"
    },
    "time": "2022-01-15 22:54:06"
}
```



### 查询成绩==ok==

> POST /api/student/score

#### 请求参数

##### Body

|   属性    |  类型  |                      说明                      |                             备注                             | 是否必填 |
| :-------: | :----: | :--------------------------------------------: | :----------------------------------------------------------: | :------: |
|   year    | string |                      学年                      |                   2021-2022（默认本学年）                    |    否    |
|   term    | string |                      学期                      |               第一学期、第二学期（默认本学期）               |    否    |
|    all    |  bool  | 是否为所有学年成绩（该参数优先级比学年学期高） | 默认true， 默认查所有学期成绩，需要查指定学期学年成绩时需要指定为false |    否    |
|   token   | string |                token，验证身份                 |                                                              |    是    |
|   type    | string |          成绩类型，平时成绩、原始成绩          |               ORDINARY（默认值）、COMPOSITION                |    否    |
| studentId | String |                      学号                      |                                                              |    是    |

#### 返回结果

- 平时成绩返回

#### data

|    属性    |  类型  |   说明   | 备注 |
| :--------: | :----: | :------: | :--: |
|    year    | string |   学年   |      |
|    term    | string |   学期   |      |
| courseName | string | 课程名称 |      |
|    time    |  date  | 考核时间 |      |
|    name    | string | 考核类型 |      |
|   score    | double | 考核成绩 |      |
|  courseId  |  int   | 课程编号 |      |
| studentId  | string |   学号   |      |

- 原始成绩返回

#### data

|    属性    |  类型  |   说明   | 备注 |
| :--------: | :----: | :------: | :--: |
|    year    | string |   学年   |      |
|    term    | string |   学期   |      |
| courseName | string | 课程名称 |      |
|  property  | string | 课程类别 |      |
|   score    | double |   学分   |      |
|   grade    |  int   |   成绩   |      |
| studyMode  | string | 修读方式 |      |
|     r      | string | 班级排名 |      |
|  courseId  |  int   | 课程编号 |      |
| studentId  | string |   学号   |      |

#### 成功示例

- 平时成绩返回

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/student/score",
    "data": [
        {
            "courseId": 1,
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "数据库",
            "time": "2021-11-04",
            "name": "实验2",
            "score": 72.0,
            "studentId": "201921098412"
        },
        {
            "courseId": 3,
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "c",
            "time": "2021-12-14",
            "name": "考核",
            "score": 99.0,
            "studentId": "201921098412"
        },
        {
            "courseId": 3,
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "c",
            "time": "2022-01-03",
            "name": "实验",
            "score": 90.0,
            "studentId": "201921098412"
        },
        {
            "courseId": 4,
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "c++",
            "time": "2022-01-10",
            "name": "考勤",
            "score": 100.0,
            "studentId": "201921098412"
        }
    ],
    "time": "2022-01-15 23:02:10"
}
```

- 原始成绩返回

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/student/score",
    "data": [
        {
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "c++",
            "property": "专业必修课",
            "score": 2.5,
            "studyMode": "初修",
            "grade": 83.2,
            "studentId": "201921098412",
            "courseId": 4,
            "r": "2/2"
        },
        {
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "c",
            "property": "专业必修课",
            "score": 2.5,
            "studyMode": "初修",
            "grade": 90.35,
            "studentId": "201921098412",
            "courseId": 3,
            "r": "1/2"
        },
        {
            "year": "2021-2022",
            "term": "第二学期",
            "courseName": "数据库",
            "property": "专业必修课",
            "score": 4.5,
            "studyMode": "初修",
            "grade": 54.0,
            "studentId": "201921098412",
            "courseId": 1,
            "r": "1/1"
        }
    ],
    "time": "2022-01-15 23:03:00"
}
```



### 绩点查询==ok==

> POST /api/student/gpa

#### 请求参数

##### Body

|   属性    |  类型  |                    说明                    |        备注        | 是否必填 |
| :-------: | :----: | :----------------------------------------: | :----------------: | :------: |
|   year    | string |                    学年                    |     2021-2022      |    否    |
|   term    | string |                    学期                    | 第一学期、第二学期 |    否    |
|    all    |  bool  | 是否为所有学期（该参数优先级比学年学期高） |     默认为true     |    否    |
|   token   | string |              token，验证身份               |                    |    是    |
| studentId | string |                    学号                    |                    |    是    |

#### 返回结果

##### data

| 属性 |  类型  | 说明 |        备注        |
| :--: | :----: | :--: | :----------------: |
| year | string | 学年 |     2021-2022      |
| term | string | 学期 | 第一学期、第二学期 |
| all  |  bool  | 综合 | 优先级比year、term高 |
| gpa  | double | 绩点 |                    |
|  studentId  |  int   | 学号 |                    |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/student/gpa",
    "data": {
        "all": true,
        "studentId": "201921098412",
        "year": "2021-2022",
        "gpa": 2.1250000000000004,
        "term": "第一学期"
    },
    "time": "2022-01-15 23:10:27"
}
```

