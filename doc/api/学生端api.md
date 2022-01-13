### 登录==ok==

> POST  /api/login
>
> ```
> SELECT number, identity FROM user WHERE number = 201921098412 AND password = 201921098412 AND status = 1;
> ```

#### 请求参数

##### Object

|   属性   |  类型  |        说明        | 备注 | 是否必填 |
| :------: | :----: | :----------------: | :--: | :------: |
| username |  int   | 用户名，学号或工号 |      |    是    |
| password | string |        密码        |      |    是    |

#### 返回结果

##### data

|   属性   |    类型     |          说明          |                      备注                       |
| :------: | :---------: | :--------------------: | :---------------------------------------------: |
|  token   |   string    |         token          |                                                 |
| roleList | list\<role> | 角色，用户所对应的身份 | STUDENT、TEACHER、SUPER_MANAGER、DEPT_MANAGER等 |
| username |     int     |         用户名         |                                                 |

##### role

```
"id": 1,
"roleName": "STUDENT",
"status": true
```



### 更改账号密码==ok==

> POST  /api/bank/update

#### 请求参数

##### Body

#### 返回参数

##### data





### 选择学年==ok==

> GET /api/yearTerm
>
> ```
> SELECT * FROM yearTerm;
> ```

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



### 查询成绩==ok==

> POST /api/student/score

#### 请求参数

##### Body

| 属性  |  类型  |                      说明                      |               备注               | 是否必填 |
| :---: | :----: | :--------------------------------------------: | :------------------------------: | :------: |
| year  | string |                      学年                      |     2021-2022（默认本学年）      |    否    |
| term  | string |                      学期                      | 第一学期、第二学期（默认本学期） |    否    |
|  all  |  bool  | 是否为所有学年成绩（该参数优先级比学年学期高） |         默认查询所有成绩         |    否    |
| token | string |                token，验证身份                 |                                  |    是    |
| type  | string |          成绩类型，平时成绩、原始成绩          | ORDINARY（默认值）、COMPOSITION  |    否    |

#### 返回结果

- 平时成绩返回

#### data

```
SELECT * FROM ordinaryScoreView
SELECT * FROM ordinaryScoreView WHERE year = '2021-2022' AND term = '第二学期';
```

|    属性    |  类型  |   说明   | 备注 |
| :--------: | :----: | :------: | :--: |
|    year    | string |   学年   |      |
|    term    | string |   学期   |      |
| courseName | string | 课程名称 |      |
|    time    |  date  | 考核时间 |      |
|    name    | string | 考核类型 |      |
|   score    | double | 考核成绩 |      |

- 原始成绩返回

#### data

```
SELECT year,term, courseName,property, score, studyMode, grade, studentId, a.courseId, CONCAT(rank, "/", count) as r
FROM (
select *,case when courseId = @last_courseId then @rank:=@rank+1 else @rank:=1 end as rank
,@last_courseId:=courseId
from scoreCalculatedView,(select @last_courseId:= '' , @rank:=0) T
order by courseId, grade desc) as a
JOIN
(
    SELECT COUNT(*) as count, courseId
    FROM scoreCalculatedView as b
    GROUP BY b.courseId
) as c ON a.courseId = c.courseId;
# 在上面的基础上查询
```

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



### 绩点查询==ok==

> POST /api/student/gpa

#### 请求参数

##### Body

| 属性  |  类型  |                    说明                    |        备注        | 是否必填 |
| :---: | :----: | :----------------------------------------: | :----------------: | :------: |
| year  | string |                    学年                    |     2021-2022      |    否    |
| term  | string |                    学期                    | 第一学期、第二学期 |    否    |
|  all  |  bool  | 是否为综合绩点（该参数优先级比学年学期高） |                    |    是    |
| token | string |              token，验证身份               |                    |    是    |

#### 返回结果

##### data

| 属性 |  类型  | 说明 |        备注        |
| :--: | :----: | :--: | :----------------: |
| year | string | 学年 |     2021-2022      |
| term | string | 学期 | 第一学期、第二学期 |
| all  |  bool  | 综合 |                    |
| gpa  | double | 绩点 |                    |
|  id  |  int   | 学号 |                    |

