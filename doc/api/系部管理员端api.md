### 查看所有课程信息(专业)==ok==

> POST  /api/dept/courses

#### 请求参数

##### Body

|    属性     |  类型  |        说明        |         备注         | 是否必填 |
| :---------: | :----: | :----------------: | :------------------: | :------: |
| specialtyId |  int   |      专业编号      | 默认为-1，查询全学院 |    否    |
|    year     | string |        学年        |      默认本学年      |    否    |
|    term     | string |        学期        |      默认本学期      |    否    |
|     all     | string | 是否为全部学年学期 |       默认true       |    否    |
|   deptId    |  int   |      学院编号      |                      |    是    |

#### 返回参数

##### data

|     属性      | 类型 |   说明   | 备注 |
| :-----------: | :--: | :------: | :--: |
| List<Course\> | list | 课程列表 |      |

##### Course

|      属性       |  类型  |     说明     |    备注    |
| :-------------: | :----: | :----------: | :--------: |
|    deptName     | string |   部门名称   |            |
|   teacherName   | string |   教师名称   |            |
|      year       | string |   课程学年   |            |
|     deptId      |  int   |   部门编号   |            |
|  examinationId  |  int   |   考试编号   |            |
|   classNumber   |  int   | 课程班级编号 |            |
|      score      | double |     学分     |            |
|    groupName    | string |  课题组名称  |            |
| ordinaryScoreId |  int   | 平时成绩编号 |            |
|   courseName    | string |   课程名称   |            |
|    teacherId    | string |   教师编号   |            |
|   taskGroupId   |  int   |  课题组编号  |            |
|  specialtyName  | string |   专业名称   |            |
|   specialtyId   |  int   |   专业编号   |            |
|    property     | string |   课程性质   | 专业必修课 |
|    startTime    | string |   开课时间   |   第n周    |
|      term       | string |     学期     |            |
|     endTime     | string |   结课时间   |            |
|      time       |  int   |    课时数    |            |
|    courseId     |  int   |   课程编号   |            |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/courses",
    "data": [
        {
            "deptName": "计算机科学学院",
            "teacherName": "曾广平",
            "year": "2021-2022",
            "deptId": 1,
            "examinationId": 4,
            "classNumber": 1,
            "score": 4.5,
            "groupName": "软工课题组1",
            "ordinaryScoreId": 1,
            "courseName": "数据库",
            "teacherId": "3725001",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "property": "专业必修课",
            "startTime": "第1周",
            "term": "第二学期",
            "endTime": "第16周",
            "time": 48,
            "courseId": 1
        },
        {
            "deptName": "计算机科学学院",
            "teacherName": "曾广平",
            "year": "2021-2022",
            "deptId": 1,
            "examinationId": 3,
            "classNumber": 1,
            "score": 1.0,
            "groupName": "软工课题组1",
            "ordinaryScoreId": 2,
            "courseName": "软件工程导论",
            "teacherId": "3725001",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "property": "专业必修课",
            "startTime": "第6周",
            "term": "第二学期",
            "endTime": "第10周",
            "time": 24,
            "courseId": 2
        },
        {
            "deptName": "计算机科学学院",
            "teacherName": "杨喜敏",
            "year": "2021-2022",
            "deptId": 1,
            "examinationId": 2,
            "classNumber": 1,
            "score": 2.5,
            "groupName": "软工课题组1",
            "ordinaryScoreId": 3,
            "courseName": "c",
            "teacherId": "3725002",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "property": "专业必修课",
            "startTime": "第1周",
            "term": "第二学期",
            "endTime": "第16周",
            "time": 32,
            "courseId": 3
        },
        {
            "deptName": "计算机科学学院",
            "teacherName": "杨喜敏",
            "year": "2021-2022",
            "deptId": 1,
            "examinationId": 1,
            "classNumber": 1,
            "score": 2.5,
            "groupName": "软工课题组1",
            "ordinaryScoreId": 4,
            "courseName": "c++",
            "teacherId": "3725002",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "property": "专业必修课",
            "startTime": "第1周",
            "term": "第二学期",
            "endTime": "第16周",
            "time": 32,
            "courseId": 4
        }
    ],
    "time": "2022-01-15 23:17:21"
}
```



### 查看专业信息==ok==

> POST  /api/dept/specialty

#### 请求参数

##### Body
|  属性  | 类型 |   说明   | 备注 | 是否必填 |
| :----: | :--: | :------: | :--: | :------: |
| deptId | int  | 学院编号 |      |    是    |


#### 返回参数

##### data

|      属性      | 类型 |   说明   | 备注 |
| :------------: | :--: | :------: | :--: |
| List<Specity\> | list | 课程列表 |      |

##### Course
|     属性      |  类型  |   说明   | 备注 |
| :-----------: | :----: | :------: | :--: |
|   deptName    | string | 学院名称 |      |
| specialtyName | string | 专业名称 |      |
|  specialtyId  |  int   | 专业编号 |      |
|    deptId     |  int   | 学院编号 |      |


#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/specialty",
    "data": [
        {
            "deptName": "计算机科学学院",
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "specialtyName": "计算机科学与技术",
            "specialtyId": 2,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "specialtyName": "智能科学与技术",
            "specialtyId": 3,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "specialtyName": "网络工程",
            "specialtyId": 4,
            "deptId": 1
        }
    ],
    "time": "2022-01-15 23:29:04"
}
```



### 查看教师信息(专业)==ok==

> POST  /api/dept/teacher

#### 请求参数

##### Body

#### 返回参数

##### data



### 查看班级信息(专业)==ok==

> POST  /api/dept/class

#### 请求参数

##### Body

#### 返回参数

##### data



### 查看教师录入成绩情况==ok==

> 调用教师端查看录入成绩情况接口
>
> 不提供所有教师录入情况



### 专业成绩分布==ok==

> POST  /api/dept/distribute

#### 请求参数

##### Body

#### 返回参数

##### data



### 专业不及格人数(挂科)==ok==

> POST  /api/dept/fail

#### 请求参数

##### Body

#### 返回参数

##### data



### 专业最低、最高、平均（绩点）、排名==ok==

> POST  /api/dept/extreme

#### 请求参数

##### Body

#### 返回参数

##### data
