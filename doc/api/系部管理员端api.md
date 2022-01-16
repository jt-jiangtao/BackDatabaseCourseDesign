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

|    属性     | 类型 |   说明   |        备注        | 是否必填 |
| :---------: | :--: | :------: | :----------------: | :------: |
|   deptId    | int  | 学院编号 |                    |    是    |
| specialtyId | int  | 专业编号 | 默认-1，表示全专业 |    是    |

#### 返回参数

##### data

|      属性      | 类型 |   说明   | 备注 |
| :------------: | :--: | :------: | :--: |
| List<Teacher\> | list | 教师列表 |      |

##### Teacher

|        属性         |  类型  |    说明    | 备注 |
| :-----------------: | :----: | :--------: | :--: |
|      birthday       | string |    生日    |      |
| educationBackground | string |  毕业院校  |      |
|      deptName       | string |  学院名称  |      |
|         sex         | string |    性别    |      |
|       deptId        |  int   |  学院编号  |      |
|      entryTime      | string |  入职时间  |      |
|      groupName      | string | 课题组名称 |      |
|      teacherId      |  int   |  教师编号  |      |
|     taskGroupId     |  int   | 课题组编号 |      |
|    specialtyName    | string |  专业名称  |      |
|   identityNumber    | string | 身份证号码 |      |
|    homeLocation     | string |  家庭住址  |      |
|     specialtyId     |  int   |  专业编号  |      |
|        name         | string |    姓名    |      |
|  professionalTitle  | string |    职称    |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/teacher",
    "data": [
        {
            "birthday": "1992-01-09",
            "educationBackground": "educationBackground_1",
            "deptName": "计算机科学学院",
            "sex": "男",
            "deptId": 1,
            "entryTime": "2013-01-09",
            "groupName": "软工课题组1",
            "teacherId": "3725001",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "identityNumber": "421302201301094528",
            "homeLocation": "homeLocation_3725001",
            "specialtyId": 1,
            "name": "曾广平",
            "professionalTitle": "title_1"
        },
        {
            "birthday": "1998-01-13",
            "educationBackground": "educationBackground_2",
            "deptName": "计算机科学学院",
            "sex": "男",
            "deptId": 1,
            "entryTime": "2009-01-09",
            "groupName": "软工课题组1",
            "teacherId": "3725002",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "identityNumber": "421302200901093928",
            "homeLocation": "homeLocation_3725002",
            "specialtyId": 1,
            "name": "杨喜敏",
            "professionalTitle": "title_2"
        },
        {
            "birthday": "2022-01-15",
            "educationBackground": "2",
            "deptName": "计算机科学学院",
            "sex": "2",
            "deptId": 1,
            "entryTime": "2022-01-15",
            "groupName": "软工课题组1",
            "teacherId": "3725004",
            "taskGroupId": 1,
            "specialtyName": "软件工程",
            "identityNumber": "2",
            "homeLocation": "2",
            "specialtyId": 1,
            "name": "2",
            "professionalTitle": "2"
        }
    ],
    "time": "2022-01-16 12:52:49"
}
```



### 查看班级信息(专业)==ok==

> POST  /api/dept/class

#### 请求参数

##### Body

|    属性     | 类型 |   说明   |        备注        | 是否必填 |
| :---------: | :--: | :------: | :----------------: | :------: |
|   deptId    | int  | 学院编号 |                    |    是    |
| specialtyId | int  | 专业编号 | 默认-1，表示全专业 |    是    |

#### 返回参数

##### data

|     属性     | 类型 |   说明   | 备注 |
| :----------: | :--: | :------: | :--: |
| List<Class\> | list | 班级列表 |      |

##### Class

|     属性      |  类型  |   说明   |     备注     |
| :-----------: | :----: | :------: | :----------: |
|   deptName    | string | 学院名称 |              |
|    number     |  int   |  班级号  |              |
|    classId    |  int   | 班级编号 | 全校唯一编号 |
| specialtyName | string | 专业名称 |              |
|  specialtyId  |  int   | 专业编号 |              |
|    deptId     |  int   | 学院编号 |              |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/class",
    "data": [
        {
            "deptName": "计算机科学学院",
            "number": 1901,
            "classId": 1,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1902,
            "classId": 2,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1903,
            "classId": 3,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1904,
            "classId": 4,
            "specialtyName": "软件工程",
            "specialtyId": 1,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1901,
            "classId": 5,
            "specialtyName": "计算机科学与技术",
            "specialtyId": 2,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1902,
            "classId": 6,
            "specialtyName": "计算机科学与技术",
            "specialtyId": 2,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1903,
            "classId": 7,
            "specialtyName": "计算机科学与技术",
            "specialtyId": 2,
            "deptId": 1
        },
        {
            "deptName": "计算机科学学院",
            "number": 1904,
            "classId": 8,
            "specialtyName": "计算机科学与技术",
            "specialtyId": 2,
            "deptId": 1
        }
    ],
    "time": "2022-01-16 12:58:59"
}
```



### 查看教师录入成绩情况==ok==

> 调用教师端查看录入成绩情况接口
>
> 不提供所有教师录入情况



### 专业成绩分布==ok==

> POST  /api/dept/distribute

#### 请求参数

##### Body

|    属性     |  类型  |       说明       |       备注       | 是否必填 |
| :---------: | :----: | :--------------: | :--------------: | :------: |
|   deptId    |  int   |     学院编号     |                  |    是    |
| specialtyId |  int   |     专业编号     | 默认-1，表示全部 |    否    |
|    year     | string |       学年       |    默认本学年    |    否    |
|    term     | string |       学期       |    默认本学期    |    否    |
|     all     |  bool  | 是否为全学年学期 |     默认true     |    否    |
|    grade    |  int   |       年级       | 默认-1，表示全部 |    否    |
|   classId   |  int   |     班级编号     | 默认-1，表示全部 |    否    |

#### 返回参数

##### data

|      属性      | 类型 |   说明   | 备注 |
| :------------: | :--: | :------: | :--: |
| List<Student\> | list | 学生列表 |      |

##### Student

|        属性        |     类型      |        说明        | 备注 |
| :----------------: | :-----------: | :----------------: | :--: |
|        name        |    string     |        姓名        |      |
|     studentId      |    string     |      学生编号      |      |
|   specialtyName    |    string     |      专业名称      |      |
|     className      |    string     |      课程名称      |      |
| studentSpecialtyId |      int      | 学生所处的专业编号 |      |
|   studentClassId   |      int      |      班级编号      |      |
|      nowGrade      |      int      |        年级        |      |
|     scorePoint     |    double     |        绩点        |      |
|      courses       | list<Course\> |      课程列表      |      |

##### Course

|          属性           |  类型  |     说明     |    备注    |
| :---------------------: | :----: | :----------: | :--------: |
|          year           | string |     学年     |            |
|          term           | string |     学期     |            |
|       courseName        | string |   课程名称   |            |
|        property         | string |   课程性质   |            |
|       courseScore       | double |   课程学分   |            |
|        studyMode        | string |   修读方式   | 初修、重修 |
|          score          | double |   最终分数   |            |
|        courseId         |  int   |   课程编号   |            |
|        teacherId        |  int   |   教师编号   |            |
|          grade          | string |     等级     |    A、B    |
|    examinationScore     | double |   考试分数   |            |
| ordinaryScoreProportion | double | 平时成绩占比 |            |
|  examinationProportion  | double |   考试占比   |            |
|      examinationId      |  int   |   考试编号   |            |
|  studentExaminationId   |  int   | 学生考试编号 |            |
|      ordinaryScore      | double |   平时成绩   |            |
|      teacherDeptId      |  int   | 课程开设学院 |            |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/distribute",
    "data": [
        {
            "name": "张三",
            "nowGrade": 2018,
            "courses": []
        },
        {
            "name": "蒋涛",
            "studentId": "201921098412",
            "specialtyName": "软件工程",
            "className": "1904班",
            "studentSpecialtyId": 1,
            "studentClassId": 4,
            "nowGrade": 2019,
            "scorePoint": 2.1250000000000004,
            "courses": [
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c++",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 83.2,
                    "courseId": 4,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 99.0,
                    "ordinaryScoreProportion": 0.2,
                    "examinationProportion": 0.8,
                    "examinationId": 1,
                    "studentExaminationId": 3,
                    "ordinaryScore": 20.0,
                    "teacherDeptId": 1
                },
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 90.35,
                    "courseId": 3,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 88.0,
                    "ordinaryScoreProportion": 0.5,
                    "examinationProportion": 0.5,
                    "examinationId": 2,
                    "studentExaminationId": 5,
                    "ordinaryScore": 92.7,
                    "teacherDeptId": 1
                },
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "数据库",
                    "property": "专业必修课",
                    "courseScore": 4.5,
                    "studyMode": "初修",
                    "score": 54.0,
                    "courseId": 1,
                    "teacherId": "3725001",
                    "grade": "B",
                    "examinationScore": 42.0,
                    "ordinaryScoreProportion": 0.4,
                    "examinationProportion": 0.6,
                    "examinationId": 4,
                    "studentExaminationId": 29,
                    "ordinaryScore": 72.0,
                    "teacherDeptId": 1
                }
            ]
        },
        {
            "name": "欧阳华清",
            "studentId": "201921098380",
            "specialtyName": "计算机科学与技术",
            "className": "1901班",
            "studentSpecialtyId": 2,
            "studentClassId": 5,
            "nowGrade": 2019,
            "scorePoint": 3.851,
            "courses": [
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c++",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 89.52,
                    "courseId": 4,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 90.0,
                    "ordinaryScoreProportion": 0.2,
                    "examinationProportion": 0.8,
                    "examinationId": 1,
                    "studentExaminationId": 4,
                    "ordinaryScore": 87.6,
                    "teacherDeptId": 1
                },
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 87.5,
                    "courseId": 3,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 92.0,
                    "ordinaryScoreProportion": 0.5,
                    "examinationProportion": 0.5,
                    "examinationId": 2,
                    "studentExaminationId": 6,
                    "ordinaryScore": 83.0,
                    "teacherDeptId": 1
                }
            ]
        }
    ],
    "time": "2022-01-16 13:16:04"
}
```



### 专业不及格人数(挂科)==ok==

> POST  /api/dept/fail

#### 请求参数

##### Body

|    属性     |  类型  |       说明       |       备注       | 是否必填 |
| :---------: | :----: | :--------------: | :--------------: | :------: |
|   deptId    |  int   |     学院编号     |                  |    是    |
| specialtyId |  int   |     专业编号     | 默认-1，表示全部 |    否    |
|    year     | string |       学年       |    默认本学年    |    否    |
|    term     | string |       学期       |    默认本学期    |    否    |
|     all     |  bool  | 是否为全学年学期 |     默认true     |    否    |
|    grade    |  int   |       年级       | 默认-1，表示全部 |    否    |
|   classId   |  int   |     班级编号     | 默认-1，表示全部 |    否    |

#### 返回参数

##### data

> 和专业成绩分布数据结构相同，但是返回内容只为不及格学生和对应学科

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/fail",
    "data": [
        {
            "name": "蒋涛",
            "studentId": "201921098412",
            "specialtyName": "软件工程",
            "className": "1904班",
            "studentSpecialtyId": 1,
            "studentClassId": 4,
            "nowGrade": 2019,
            "scorePoint": 2.1250000000000004,
            "courses": [
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "数据库",
                    "property": "专业必修课",
                    "courseScore": 4.5,
                    "studyMode": "初修",
                    "score": 54.0,
                    "courseId": 1,
                    "teacherId": "3725001",
                    "grade": "B",
                    "examinationScore": 42.0,
                    "ordinaryScoreProportion": 0.4,
                    "examinationProportion": 0.6,
                    "examinationId": 4,
                    "studentExaminationId": 29,
                    "ordinaryScore": 72.0,
                    "teacherDeptId": 1
                }
            ]
        }
    ],
    "time": "2022-01-16 13:26:21"
}
```



### 专业最低、最高、平均（绩点）、排名==ok==

> POST  /api/dept/extreme

#### 请求参数

##### Body

|    属性     |  类型  |       说明       |       备注       | 是否必填 |
| :---------: | :----: | :--------------: | :--------------: | :------: |
|   deptId    |  int   |     学院编号     |                  |    是    |
| specialtyId |  int   |     专业编号     | 默认-1，表示全部 |    否    |
|    year     | string |       学年       |    默认本学年    |    否    |
|    term     | string |       学期       |    默认本学期    |    否    |
|     all     |  bool  | 是否为全学年学期 |     默认true     |    否    |
|    grade    |  int   |       年级       | 默认-1，表示全部 |    否    |
|   classId   |  int   |     班级编号     | 默认-1，表示全部 |    否    |

#### 返回参数

##### data

|    属性    |  类型   |     说明     |                备注                 |
| :--------: | :-----: | :----------: | :---------------------------------: |
| maxStudent | Student | 绩点最高学生 | 和专业成绩分布中Student数据结构相同 |
|    min     | double  |   最低绩点   |                                     |
|    avg     | double  |   平均绩点   |                                     |
|    max     | double  |   最高绩点   |                                     |
| minStudent | Student | 绩点最低学生 | 和专业成绩分布中Student数据结构相同 |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/dept/extreme",
    "data": {
        "maxStudent": {
            "name": "欧阳华清",
            "studentId": "201921098380",
            "specialtyName": "计算机科学与技术",
            "className": "1901班",
            "studentSpecialtyId": 2,
            "studentClassId": 5,
            "nowGrade": 2019,
            "scorePoint": 3.851,
            "courses": [
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c++",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 89.52,
                    "courseId": 4,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 90.0,
                    "ordinaryScoreProportion": 0.2,
                    "examinationProportion": 0.8,
                    "examinationId": 1,
                    "studentExaminationId": 4,
                    "ordinaryScore": 87.6,
                    "teacherDeptId": 1
                },
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 87.5,
                    "courseId": 3,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 92.0,
                    "ordinaryScoreProportion": 0.5,
                    "examinationProportion": 0.5,
                    "examinationId": 2,
                    "studentExaminationId": 6,
                    "ordinaryScore": 83.0,
                    "teacherDeptId": 1
                }
            ]
        },
        "min": 2.1250000000000004,
        "avg": 2.9880000000000004,
        "max": 3.851,
        "minStudent": {
            "name": "蒋涛",
            "studentId": "201921098412",
            "specialtyName": "软件工程",
            "className": "1904班",
            "studentSpecialtyId": 1,
            "studentClassId": 4,
            "nowGrade": 2019,
            "scorePoint": 2.1250000000000004,
            "courses": [
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c++",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 83.2,
                    "courseId": 4,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 99.0,
                    "ordinaryScoreProportion": 0.2,
                    "examinationProportion": 0.8,
                    "examinationId": 1,
                    "studentExaminationId": 3,
                    "ordinaryScore": 20.0,
                    "teacherDeptId": 1
                },
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "c",
                    "property": "专业必修课",
                    "courseScore": 2.5,
                    "studyMode": "初修",
                    "score": 90.35,
                    "courseId": 3,
                    "teacherId": "3725002",
                    "grade": "A",
                    "examinationScore": 88.0,
                    "ordinaryScoreProportion": 0.5,
                    "examinationProportion": 0.5,
                    "examinationId": 2,
                    "studentExaminationId": 5,
                    "ordinaryScore": 92.7,
                    "teacherDeptId": 1
                },
                {
                    "year": "2021-2022",
                    "term": "第二学期",
                    "courseName": "数据库",
                    "property": "专业必修课",
                    "courseScore": 4.5,
                    "studyMode": "初修",
                    "score": 54.0,
                    "courseId": 1,
                    "teacherId": "3725001",
                    "grade": "B",
                    "examinationScore": 42.0,
                    "ordinaryScoreProportion": 0.4,
                    "examinationProportion": 0.6,
                    "examinationId": 4,
                    "studentExaminationId": 29,
                    "ordinaryScore": 72.0,
                    "teacherDeptId": 1
                }
            ]
        }
    },
    "time": "2022-01-16 13:27:48"
}
```

