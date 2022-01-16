### 登陆==ok==

> 同学生端



### 所有所带班级==ok==

> POST /api/teacher/course

#### 请求参数

##### Body

|   属性    |  类型  |      说明       |    备注    | 是否必填 |
| :-------: | :----: | :-------------: | :--------: | :------: |
|   token   | string | token，验证身份 |            |    是    |
|   year    | string |      学年       | 默认本学年 |    否    |
|   term    | string |      学期       | 默认本学期 |    否    |
| teacherId | string |    教师编号     |            |    是    |

> 没有year和term参数默认为本学期

#### 返回结果

##### data

|  属性   |     类型      |   说明   | 备注 |
| :-----: | :-----------: | :------: | :--: |
| courses | list\<course> | 班级列表 |      |
|  year   |    string     |   学年   |      |
|  term   |    string     |   学期   |      |

##### course

|   属性    |  类型  |   说明   |     备注     |
| :-------: | :----: | :------: | :----------: |
|    id     |  int   | 课程编号 |              |
| startTime | string | 开课时间 |              |
|  endTime  | string | 结课时间 |              |
|   score   | double |   学分   |              |
|   time    |  int   |   学时   |              |
| introduce | string |   简介   |              |
| property  | string | 课程性质 | 专业必修课等 |
|   name    | string | 课程名称 |              |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/course",
    "data": {
        "courses": [
            {
                "id": 3,
                "teacherId": "3725002",
                "startTime": "第1周",
                "endTime": "第16周",
                "score": 2.5,
                "time": 32,
                "property": "专业必修课",
                "name": "c",
                "classNumber": 1
            },
            {
                "id": 4,
                "teacherId": "3725002",
                "startTime": "第1周",
                "endTime": "第16周",
                "score": 2.5,
                "time": 32,
                "property": "专业必修课",
                "name": "c++",
                "classNumber": 1
            }
        ],
        "year": "2021-2022",
        "term": "第一学期"
    },
    "time": "2022-01-16 13:58:14"
}
```



### 平时成绩与期末考试比例查看==ok==

> POST  /api/teacher/proportion/get

#### 请求参数

##### Body

| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| token     |        |          |      | 是       |
| courseId  | int    | 课程编号 |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回结果

##### data

| 属性             | 类型   | 说明         | 备注 |
| ---------------- | ------ | ------------ | ---- |
| normalProportion | double | 平时成绩占比 |      |
| examProportion   | double | 考试成绩占比 |      |
| courseId         | int    | 课程编号     |      |
| name             | string | 课程名称     |      |
| teacherId        | int    | 教师编号     |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/proportion/get",
    "data": {
        "name": "数据库",
        "normalProportion": 0.4,
        "examProportion": 0.6,
        "courseId": 1,
        "teacherId": "3725001"
    },
    "time": "2022-01-16 14:05:37"
}
```



### 平时成绩与期末开始比例修改==ok==

> POST  /api/teacher/proportion/update

#### 请求参数

##### Body

| 属性             | 类型   | 说明         | 备注 | 是否必填 |
| ---------------- | ------ | ------------ | ---- | -------- |
| token            |        |              |      | 是       |
| courseId         | int    | 课程编号     |      | 是       |
| normalProportion | double | 平时成绩占比 |      | 是       |
| examProportion   | double | 考试成绩占比 |      | 是       |
| teacherId        | string | 教师编号     |      | 是       |

#### 返回结果

##### data


| 属性             | 类型   | 说明         | 备注 |
| ---------------- | ------ | ------------ | ---- |
| normalProportion | double | 平时成绩占比 |      |
| examProportion   | double | 考试成绩占比 |      |
| id               | int    | 课程编号     |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/proportion/update",
    "data": {
        "examProportion": 0.8,
        "normalProportion": 0.2,
        "id": 1
    },
    "time": "2022-01-16 14:14:33"
}
```



### 平时成绩项修改、增加==ok==

> POST /api/teacher/normalScore/item/update

#### 请求参数

##### Body
| 属性      | 类型        | 说明           | 备注 | 是否必填 |
| --------- | ----------- | -------------- | ---- | -------- |
| items     | List<item\> | 修改平时成绩项 |      | 是       |
| courseId  | int         | 课程编号       |      | 是       |
| teacherId | int         | 教师编号       |      | 是       |

##### item

| 属性       | 类型   | 说明     | 备注 | 是否必填 |
| ---------- | ------ | -------- | ---- | -------- |
| name       | string | 考核项   |      | 是       |
| time       | string | 考核时间 |      | 是       |
| proportion | double | 占比     |      | 是       |

#### 返回参数

##### data

| 属性        | 类型 | 说明 | 备注 |
| ----------- | ---- | ---- | ---- |
| List<item\> | list |      |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/item/update",
    "data": [
        {
            "name": "实验3",
            "time": "2021-11-03",
            "proportion": 1.0
        }
    ],
    "time": "2022-01-16 14:36:34"
}
```



### 平时成绩项查看==ok==

> POST /api/teacher/normalScore/item/get

#### 请求参数

##### Body
| 属性      | 类型 | 说明     | 备注 | 是否必填 |
| --------- | ---- | -------- | ---- | -------- |
| courseId  | int  | 课程编号 |      | 是       |
| teacherId | int  | 教师编号 |      | 是       |

#### 返回参数

##### data
| 属性        | 类型 | 说明      | 备注 |
| ----------- | ---- | --------- | ---- |
| list<Item\> | list | item list |      |

##### Item

| 属性            | 类型   | 说明           | 备注 |
| --------------- | ------ | -------------- | ---- |
| id              | int    | 平时成绩项编号 |      |
| ordinaryScoreId | int    | 平时成绩编号   |      |
| name            | string | 名称           |      |
| time            | string | 考核时间       |      |
| proportion      | double | 占比           |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/item/get",
    "data": [
        {
            "id": 46,
            "ordinaryScoreId": 1,
            "name": "实验2",
            "time": "2021-11-04",
            "proportion": 1.0
        }
    ],
    "time": "2022-01-16 14:22:02"
}
```



### 平时成绩查看==ok==

> POST /api/teacher/normalScore/get

#### 请求参数

##### Body
| 属性      | 类型   | 说明           | 备注           | 是否必填 |
| --------- | ------ | -------------- | -------------- | -------- |
| courseId  | int    | 课程编号       |                | 是       |
| itemId    | int    | 平时成绩项编号 | 默认-1，全部项 | 否       |
| teacherId | string | 教师编号       |                | 是       |

#### 返回参数

##### data

| 属性   | 类型   | 说明     | 备注 |
| ------ | ------ | -------- | ---- |
| undo   | Object | 未完成项 |      |
| finish | Object | 已完成项 |      |

##### undo

| 属性                        | 类型    | 说明         | 备注         |
| --------------------------- | ------- | ------------ | ------------ |
| ordinaryScoreItemProportion | double  | 考核占比     | 平时分项占比 |
| ordinaryScoreItemName       | string  | 考核名称     |              |
| ordinaryScoreProportion     | double  | 平时分占比   |              |
| examinationId               | int     | 考试编号     |              |
| classNumber                 | int     | 班级编号     |              |
| studentId                   | int     | 学生编号     |              |
| score                       | double  | 学分数       |              |
| courseName                  | striing | 课程名称     |              |
| teacherId                   | int     | 教师编号     |              |
| studentName                 | string  | 学生姓名     |              |
| property                    | string  | 课程性质     |              |
| ordianryScoreItemId         | int     | 平时分项编号 |              |
| startTime                   | string  | 开课时间     |              |
| endTime                     | string  | 结课时间     |              |
| courseId                    | int     | 课程编号     |              |
| studyMode                   | string  | 修读方式     |              |

##### finish

| 属性                       | 类型   | 说明               | 备注 |
| -------------------------- | ------ | ------------------ | ---- |
| year                       | string | 学年               |      |
| term                       | string | 学期               |      |
| courseName                 | string | 课程名称           |      |
| time                       | string | 考核时间           |      |
| typeName                   | string | 考核名称           |      |
| score                      | double | 分数               |      |
| studentId                  | int    | 学生编号           |      |
| studentName                | string | 学生姓名           |      |
| proportionInCourse         | double | 在课程占比         |      |
| proportionInOrdinary       | double | 在平时成绩中占比   |      |
| itemId                     | int    | 平时成绩项编号     |      |
| studentOrdinaryScoreItemId | int    | 学生平时成绩项编号 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/get",
    "data": {
        "undo": [
            {
                "ordinaryScoreItemProportion": 1.00,
                "ordinaryScoreItemName": "实验3",
                "ordinaryScoreProportion": 0.20,
                "examinationId": 4,
                "classNumber": 1,
                "studentId": "201921098380",
                "score": 4.5,
                "courseName": "数据库",
                "teacherId": "3725001",
                "studentName": "欧阳华清",
                "property": "专业必修课",
                "ordianryScoreItemId": 48,
                "startTime": "第1周",
                "endTime": "第16周",
                "courseId": 1,
                "studyMode": "初修"
            }
        ],
        "finish": [
            {
                "year": "2021-2022",
                "term": "第一学期",
                "courseName": "数据库",
                "time": "2021-11-03",
                "typeName": "实验3",
                "score": 72.0,
                "studentId": "201921098412",
                "studentName": "蒋涛",
                "proportionInCourse": 0.2,
                "proportionInOrdinary": 1.0,
                "itemId": 48,
                "studentOrdinaryScoreItemId": 24
            }
        ]
    },
    "time": "2022-01-16 14:43:48"
}
```



### 平时成绩增加（excel：下载模板文件）==ok==

> POST /api/teacher/normalScore/download/excel

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data

null

#### 成功示例

 [OrdinaryScore_1_3725001_20220116150021.xls](.\OrdinaryScore_1_3725001_20220116150021.xls) 



### 平时成绩增加（excel：上传文件）==ok==

> POST /api/teacher/normalScore/upload/excel

#### 请求参数

##### Body

| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| file      | 文件   | 文件     |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data

| 属性        | 类型 | 说明   | 备注 |
| ----------- | ---- | ------ | ---- |
| List<Item\> | list | 成功项 |      |

##### item
| 属性                | 类型   | 说明             | 备注 |
| ------------------- | ------ | ---------------- | ---- |
| score               | double | 成绩             |      |
| studentId           | string | 学生编号         |      |
| ordinaryScoreItemId | int    | 学生平时成绩项名 |      |


#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/upload/excel",
    "data": [
        {
            "score": 66.0,
            "studentId": "201921098412",
            "ordinaryScoreItemId": 48
        },
        {
            "score": 77.0,
            "studentId": "201921098380",
            "ordinaryScoreItemId": 48
        }
    ],
    "time": "2022-01-16 15:05:18"
}
```

#### 失败示例

- 此时所有成绩都没有写入数据库

```
{
    "code": 1802,
    "message": "学生平时成绩分数录入错误",
    "request": "POST /api/teacher/normalScore/upload/excel",
    "data": [
        {
            "studentId": "201921098380",
            "score": 110.0,
            "errorItemName": "实验3( 100.0% )"
        }
    ],
    "time": "2022-01-16 15:06:44"
}
```



### 平时成绩增加（sql：上传文件）==ok==

> POST /api/teacher/normalScore/upload/sql

#### 请求参数

##### Body

| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| file      | 文件   | 文件     |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data

> 参考上传excel平时成绩文件

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/upload/sql",
    "data": [
        {
            "score": 99.0,
            "studentId": "201921098412",
            "ordinaryScoreItemId": 48
        },
        {
            "score": 99.0,
            "studentId": "201921098380",
            "ordinaryScoreItemId": 48
        }
    ],
    "time": "2022-01-16 15:17:13"
}
```



### 平时成绩增加（json提交）==ok==

> POST /api/teacher/normalScore/add

#### 请求参数

##### Body
| 属性      | 类型        | 说明     | 备注 | 是否必填 |
| --------- | ----------- | -------- | ---- | -------- |
| items     | List<item\> | 平时成绩 |      | 是       |
| courseId  | int         | 课程编号 |      | 是       |
| teacherId | string      | 教师编号 |      | 是       |

##### item
| 属性                | 类型   | 说明             | 备注 | 是否必填 |
| ------------------- | ------ | ---------------- | ---- | -------- |
| score               | double | 成绩             |      | 是       |
| studentId           | string | 学生编号         |      | 是       |
| ordinaryScoreItemId | int    | 学生平时成绩项名 |      | 是       |


#### 返回参数

##### data
> 参考上传excel平时成绩文件

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/add",
    "data": [
        {
            "score": 98.0,
            "studentId": "201921098412",
            "ordinaryScoreItemId": 48
        },
        {
            "score": 98.0,
            "studentId": "201921098380",
            "ordinaryScoreItemId": 48
        }
    ],
    "time": "2022-01-16 15:23:20"
}
```




### 平时成绩删除==ok==

>  POST /api/teacher/normalScore/delete

#### 请求参数

##### Body
| 属性                   | 类型       | 说明                       | 备注 | 是否必填 |
| ---------------------- | ---------- | -------------------------- | ---- | -------- |
| studentOrdinaryScoreId | List<int\> | 待删除所有学生平时成绩编号 |      | 是       |
| courseId               | int        | 课程编号                   |      | 是       |
| teacherId              | string     | 教师编号                   |      | 是       |

#### 返回参数

##### data
| 属性    | 类型       | 说明             | 备注 |
| ------- | ---------- | ---------------- | ---- |
| suucess | List<int\> | 所有删除成功编号 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/delete",
    "data": {
        "success": [
            24
        ],
        "status": "[24] 删除成功"
    },
    "time": "2022-01-16 15:28:00"
}
```



### 平时成绩修改==ok==

> POST /api/teacher/normalScore/update

#### 请求参数

##### Body
| 属性                   | 类型   | 说明             | 备注 | 是否必填 |
| ---------------------- | ------ | ---------------- | ---- | -------- |
| studentOrdinaryScoreId | int    | 学生平时成绩编号 |      | 是       |
| courseId               | int    | 课程编号         |      | 是       |
| teacherId              | string | 教师编号         |      | 是       |
| newScore               | int    | 修改后的成绩     |      | 是       |

#### 返回参数

##### data

| 属性                   | 类型   | 说明             | 备注 |
| ---------------------- | ------ | ---------------- | ---- |
| newScore               | double | 修改后的成绩     |      |
| studentOrdinaryScoreId | int    | 学生平时成绩编号 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/normalScore/update",
    "data": {
        "newScore": 100.0,
        "studentOrdinaryScoreId": 28
    },
    "time": "2022-01-16 15:31:38"
}
```




### 汇总成绩的查看==ok==

> POST /api/teacher/examScore/getFinal

#### 请求参数

##### Body
| 属性      | 类型 | 说明     | 备注 | 是否必填 |
| --------- | ---- | -------- | ---- | -------- |
| courseId  | int  | 课程编号 |      | 否       |
| teacherId | int  | 教师编号 |      | 是       |

#### 返回参数

##### data
| 属性                 | 类型 | 说明     | 备注 |
| -------------------- | ---- | -------- | ---- |
| List<StudentCourse\> | list | 汇总成绩 |      |

##### StudentCourse
| 属性          | 类型   | 说明     | 备注       |
| ------------- | ------ | -------- | ---------- |
| studentId     | int    | 学生编号 |            |
| score         | double | 课程学分 |            |
| courseName    | string | 课程名称 |            |
| specialtyName | string | 专业名称 |            |
| year          | string | 学年     |            |
| grade         | string | 分数     |            |
| studentName   | string | 学生姓名 |            |
| property      | string | 课程性质 |            |
| term          | string | 学期     |            |
| className     | string | 学生班级 | 非课程班级 |
| studyMode     | sring  | 修读方式 |            |
| courseId      | int    | 课程编号 |            |


#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/getFinal",
    "data": [
        {
            "studentId": "201921098380",
            "score": 4.5,
            "courseName": "数据库",
            "specialtyName": "计算机科学与技术",
            "year": "2021-2022",
            "grade": 90.400,
            "studentName": "欧阳华清",
            "property": "专业必修课",
            "term": "第一学期",
            "className": "1901班",
            "studyMode": "初修",
            "courseId": 1
        }
    ],
    "time": "2022-01-16 15:32:40"
}
```



### 考试成绩查看==ok==

> POST /api/teacher/examScore/get

#### 请求参数

##### Body

| 属性      | 类型 | 说明     | 备注 | 是否必填 |
| --------- | ---- | -------- | ---- | -------- |
| courseId  | int  | 课程编号 |      | 否       |
| teacherId | int  | 教师编号 |      | 是       |

#### 返回参数

##### data

| 属性   | 类型   | 说明     | 备注 |
| ------ | ------ | -------- | ---- |
| undo   | Object | 未完成项 |      |
| finish | Object | 已完成项 |      |

##### undo

| 属性          | 类型   | 说明     | 备注 |
| ------------- | ------ | -------- | ---- |
| proportion    | double | 考试占比 |      |
| examinationId | int    | 考试编号 |      |
| classNumber   | int    | 学生班级 |      |
| studentId     | int    | 学号     |      |
| score         | double | 学分数   |      |
| courseName    | string | 课程名称 |      |
| teacherId     | string | 教师编号 |      |
| grade         | string | 等级     |      |
| studentName   | string | 学生姓名 |      |
| property      | string | 课程性质 |      |
| startTime     | string | 开课时间 |      |
| endTime       | string | 结课时间 |      |
| time          | int    | 课程学时 |      |
| courseId      | int    | 课程编号 |      |
| studyMode     | string | 修读方式 |      |

##### finish

| 属性                 | 类型   | 说明             | 备注 |
| -------------------- | ------ | ---------------- | ---- |
| proportion           | double | 考试占比         |      |
| className            | string | 班级名称         |      |
| courseScore          | double | 课程学分数       |      |
| examinationId        | int    | 考试编号         |      |
| studentId            | int    | 学生编号         |      |
| score                | double | 考试分数         |      |
| courseName           | string | 课程名称         |      |
| studentClassId       | int    | 学生班级编号     |      |
| specialtyName        | string | 专业名称         |      |
| teacherId            | int    | 教师编号         |      |
| studentExaminationId | int    | 学生考试编号     |      |
| grade                | string | 等级             |      |
| studentName          | string | 学生姓名         |      |
| property             | string | 课程性质         |      |
| calculatedScore      | double | 计算之后的平时分 |      |
| studentSpecialtyId   | int    | 学生专业编号     |      |
| courseId             | int    | 课程编号         |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/get",
    "data": {
        "undo": [
            {
                "proportion": 0.80,
                "examinationId": 4,
                "classNumber": 1,
                "studentId": "201921098380",
                "score": 4.5,
                "courseName": "数据库",
                "teacherId": "3725001",
                "grade": "B",
                "studentName": "欧阳华清",
                "property": "专业必修课",
                "startTime": "第1周",
                "endTime": "第16周",
                "time": 48,
                "courseId": 1,
                "studyMode": "初修"
            }
        ],
        "finish": [
            {
                "proportion": 0.80,
                "className": "1904班",
                "courseScore": 4.5,
                "examinationId": 4,
                "studentId": "201921098412",
                "score": 42.0,
                "courseName": "数据库",
                "studentClassId": 4,
                "specialtyName": "软件工程",
                "teacherId": "3725001",
                "studentExaminationId": 29,
                "grade": "B",
                "studentName": "蒋涛",
                "property": "专业必修课",
                "calculatedScore": 33.600,
                "studentSpecialtyId": 1,
                "courseId": 1
            }
        ]
    },
    "time": "2022-01-16 15:42:58"
}
```




### 考试成绩增加（excel：下载模板文件）==ok==

> POST /api/teacher/examScore/download/excel

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data

null

#### 成功示例

 [ExaminationScore_1_3725001_20220116160804.xls](.\ExaminationScore_1_3725001_20220116160804.xls) 



### 考试成绩增加（excel：上传文件）==ok==

> POST /api/teacher/examScore/upload/excel

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| file      | 文件   | 文件     |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data
| 属性        | 类型 | 说明     | 备注 |
| ----------- | ---- | -------- | ---- |
| List<item\> | list | 分数集合 |      |

##### item

| 属性      | 类型   | 说明 | 备注 |
| --------- | ------ | ---- | ---- |
| score     | double | 分数 |      |
| studentId | string | 学号 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/upload/excel",
    "data": [
        {
            "score": 90.0,
            "studentId": "201921098412"
        },
        {
            "score": 95.0,
            "studentId": "201921098380"
        }
    ],
    "time": "2022-01-16 16:12:55"
}
```




### 考试成绩增加（sql：上传文件）==ok==

> POST /api/teacher/examScore/upload/sql

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| file      | 文件   | 文件     |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data
> 返回数据结构和上传excel返回结果相同

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/upload/sql",
    "data": [
        {
            "score": 100.0,
            "studentId": "201921098412"
        },
        {
            "score": 100.0,
            "studentId": "201921098380"
        }
    ],
    "time": "2022-01-16 16:18:53"
}
```




### 考试成绩增加（json提交）==ok==

> POST /api/teacher/examScore/add

#### 请求参数

##### Body
| 属性      | 类型        | 说明     | 备注 | 是否必填 |
| --------- | ----------- | -------- | ---- | -------- |
| items     | List<item\> | 考试成绩 |      | 是       |
| courseId  | int         | 课程编号 |      | 是       |
| teacherId | string      | 教师编号 |      | 是       |

#### 返回参数

##### data
> 返回数据结构和上传excel返回结果相同

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/add",
    "data": [
        {
            "score": 77.5,
            "studentId": "201921098412"
        },
        {
            "score": 77.5,
            "studentId": "201921098380"
        }
    ],
    "time": "2022-01-16 16:21:39"
}
```




### 考试成绩删除==ok==

> POST /api/teacher/examScore/delete

#### 请求参数

##### Body
| 属性                      | 类型       | 说明                       | 备注 | 是否必填 |
| ------------------------- | ---------- | -------------------------- | ---- | -------- |
| studentExaminationScoreId | List<int\> | 待删除所有学生考试成绩编号 |      | 是       |
| courseId                  | int        | 课程编号                   |      | 是       |
| teacherId                 | string     | 教师编号                   |      | 是       |

#### 返回参数

##### data
| 属性    | 类型       | 说明             | 备注 |
| ------- | ---------- | ---------------- | ---- |
| suucess | List<int\> | 所有删除成功编号 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/delete",
    "data": {
        "success": [
            29
        ],
        "status": "[29] 删除成功"
    },
    "time": "2022-01-16 16:25:21"
}
```




### 考试成绩修改==ok==

> POST /api/teacher/examScore/update

#### 请求参数

##### Body
| 属性                      | 类型   | 说明             | 备注 | 是否必填 |
| ------------------------- | ------ | ---------------- | ---- | -------- |
| studentExaminationScoreId | int    | 学生考试成绩编号 |      | 是       |
| courseId                  | int    | 课程编号         |      | 是       |
| teacherId                 | string | 教师编号         |      | 是       |
| newScore                  | int    | 修改后的成绩     |      | 是       |

#### 返回参数

##### data
| 属性                      | 类型   | 说明             | 备注 |
| ------------------------- | ------ | ---------------- | ---- |
| studentExaminationScoreId | int    | 学生考试分数编号 |      |
| newScore                  | double | 修改后的分数     |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/examScore/update",
    "data": {
        "studentExaminationScoreId": 35,
        "newScore": 76.0
    },
    "time": "2022-01-16 16:27:20"
}
```




### 班级不及格人数及信息==ok==

> POST /api/teacher/fail

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data
| 属性           | 类型 | 说明       | 备注 |
| -------------- | ---- | ---------- | ---- |
| List<Student\> | list | 不及格学生 |      |

##### Student

| 属性                    | 类型   | 说明         | 备注 |
| ----------------------- | ------ | ------------ | ---- |
| year                    | string | 学年         |      |
| className               | string | 班级名称     |      |
| courseScore             | double | 课程学分     |      |
| ordinaryScoreProportion | double | 平时成绩占比 |      |
| ordinaryScore           | double | 平时成绩     |      |
| examinationId           | int    | 考试编号     |      |
| studentId               | string | 学号         |      |
| score                   | double | 最终成绩     |      |
| courseName              | string | 课程名称     |      |
| studentClassId          | int    | 学生班级编号 |      |
| teacherId               | string | 教师编号     |      |
| specialtyName           | string | 专业名称     |      |
| studentExaminationId    | int    | 学生考试编号 |      |
| examinationScore        | double | 考试分数     |      |
| grade                   | string | 等级         |      |
| studentName             | string | 学生姓名     |      |
| property                | string | 课程性质     |      |
| term                    | string | 学期         |      |
| studentSpecialtyId      | int    | 学生专业编号 |      |
| studyMode               | string | 修读方式     |      |
| courseId                | int    | 课程编号     |      |
| examinationProportion   | double | 考试占比     |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/fail",
    "data": [
        {
            "year": "2021-2022",
            "className": "1901班",
            "courseScore": 4.5,
            "ordinaryScoreProportion": 0.20,
            "ordinaryScore": 100.00,
            "examinationId": 4,
            "studentId": "201921098380",
            "score": 53.600,
            "courseName": "数据库",
            "studentClassId": 5,
            "teacherId": "3725001",
            "specialtyName": "计算机科学与技术",
            "studentExaminationId": 35,
            "examinationScore": 42.0,
            "grade": "B",
            "studentName": "欧阳华清",
            "property": "专业必修课",
            "term": "第一学期",
            "studentSpecialtyId": 2,
            "studyMode": "初修",
            "courseId": 1,
            "examinationProportion": 0.80
        }
    ],
    "time": "2022-01-16 16:35:11"
}
```




### 班级成绩分布==ok==

> POST /api/teacher/distribute

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data
| 属性           | 类型 | 说明       | 备注                                       |
| -------------- | ---- | ---------- | ------------------------------------------ |
| List<Student\> | list | 不及格学生 | Student为班级不及格人数及信息接口的Student |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/distribute",
    "data": [
        {
            "year": "2021-2022",
            "className": "1904班",
            "courseScore": 4.5,
            "ordinaryScoreProportion": 0.20,
            "ordinaryScore": 88.00,
            "examinationId": 4,
            "studentId": "201921098412",
            "score": 63.200,
            "courseName": "数据库",
            "studentClassId": 4,
            "teacherId": "3725001",
            "specialtyName": "软件工程",
            "studentExaminationId": 36,
            "examinationScore": 57.0,
            "grade": "B",
            "studentName": "蒋涛",
            "property": "专业必修课",
            "term": "第一学期",
            "studentSpecialtyId": 1,
            "studyMode": "初修",
            "courseId": 1,
            "examinationProportion": 0.80
        },
        {
            "year": "2021-2022",
            "className": "1901班",
            "courseScore": 4.5,
            "ordinaryScoreProportion": 0.20,
            "ordinaryScore": 100.00,
            "examinationId": 4,
            "studentId": "201921098380",
            "score": 53.600,
            "courseName": "数据库",
            "studentClassId": 5,
            "teacherId": "3725001",
            "specialtyName": "计算机科学与技术",
            "studentExaminationId": 35,
            "examinationScore": 42.0,
            "grade": "B",
            "studentName": "欧阳华清",
            "property": "专业必修课",
            "term": "第一学期",
            "studentSpecialtyId": 2,
            "studyMode": "初修",
            "courseId": 1,
            "examinationProportion": 0.80
        }
    ],
    "time": "2022-01-16 16:37:02"
}
```




### 最低分、最高分、平均分==ok==

> POST /api/teacher/extreme

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注 | 是否必填 |
| --------- | ------ | -------- | ---- | -------- |
| courseId  | int    | 课程编号 |      | 是       |
| teacherId | string | 教师编号 |      | 是       |

#### 返回参数

##### data
| 属性        | 类型           | 说明                   | 备注                                       |
| ----------- | -------------- | ---------------------- | ------------------------------------------ |
| sortedScore | List<Student\> | 排序好的学生成绩       | Student为班级不及格人数及信息接口的Student |
| extreme     | Object         | 最低分、最高分、平均分 |                                            |

##### extreme

| 属性 | 类型   | 说明   | 备注 |
| ---- | ------ | ------ | ---- |
| min  | double | 最低分 |      |
| max  | double | 最高分 |      |
| avg  | double | 平均分 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/extreme",
    "data": {
        "sortedScore": [
            {
                "year": "2021-2022",
                "className": "1904班",
                "courseScore": 4.5,
                "ordinaryScoreProportion": 0.20,
                "ordinaryScore": 88.00,
                "examinationId": 4,
                "studentId": "201921098412",
                "score": 63.200,
                "courseName": "数据库",
                "studentClassId": 4,
                "teacherId": "3725001",
                "specialtyName": "软件工程",
                "studentExaminationId": 36,
                "examinationScore": 57.0,
                "grade": "B",
                "studentName": "蒋涛",
                "property": "专业必修课",
                "term": "第一学期",
                "studentSpecialtyId": 1,
                "studyMode": "初修",
                "courseId": 1,
                "examinationProportion": 0.80
            },
            {
                "year": "2021-2022",
                "className": "1901班",
                "courseScore": 4.5,
                "ordinaryScoreProportion": 0.20,
                "ordinaryScore": 100.00,
                "examinationId": 4,
                "studentId": "201921098380",
                "score": 53.600,
                "courseName": "数据库",
                "studentClassId": 5,
                "teacherId": "3725001",
                "specialtyName": "计算机科学与技术",
                "studentExaminationId": 35,
                "examinationScore": 42.0,
                "grade": "B",
                "studentName": "欧阳华清",
                "property": "专业必修课",
                "term": "第一学期",
                "studentSpecialtyId": 2,
                "studyMode": "初修",
                "courseId": 1,
                "examinationProportion": 0.80
            }
        ],
        "extreme": {
            "avg": 58.40,
            "min": 53.60,
            "max": 63.20
        }
    },
    "time": "2022-01-16 16:37:30"
}
```




### 查看未录完的信息==ok==

> POST /api/teacher/unfinished

#### 请求参数

##### Body
| 属性      | 类型   | 说明     | 备注                   | 是否必填 |
| --------- | ------ | -------- | ---------------------- | -------- |
| courseId  | int    | 课程编号 | 默认-1，为全部课程信息 | 否       |
| teacherId | string | 教师编号 |                        | 是       |

#### 返回参数

##### data
| 属性              | 类型                     | 说明                   | 备注 |
| ----------------- | ------------------------ | ---------------------- | ---- |
| ordinaryScoreItem | List\<ordinaryScoreItem> | 未完成登记的平时成绩项 |      |
| examination       | List\<examination>       | 未完成登记的考试       |      |
| ordinaryScore     | List\<ordinaryScore>     | 未完成登记的平时成绩   |      |

##### ordinaryScoreItem

| 属性               | 类型   | 说明         | 备注             |
| ------------------ | ------ | ------------ | ---------------- |
| score              | double | 学分数       |                  |
| ordinaryScoreId    | int    | 平时成绩编号 |                  |
| property           | string | 课程性质     |                  |
| name               | string | 课程名称     |                  |
| ordinaryProportion | double | 平时成绩占比 |                  |
| startTime          | string | 开课时间     |                  |
| endTime            | string | 结课时间     |                  |
| time               | int    | 学时数       |                  |
| courseId           | int    | 课程编号     |                  |
| classNumber        | int    | 班级号       | 全校唯一班级编号 |

##### examination

| 属性          | 类型   | 说明     | 备注 |
| ------------- | ------ | -------- | ---- |
| proportion    | double | 考试占比 |      |
| examinationId | int    | 考试编号 |      |
| classNumber   | int    | 学生班级 |      |
| studentId     | int    | 学号     |      |
| score         | double | 学分数   |      |
| courseName    | string | 课程名称 |      |
| teacherId     | string | 教师编号 |      |
| grade         | string | 等级     |      |
| studentName   | string | 学生姓名 |      |
| property      | string | 课程性质 |      |
| startTime     | string | 开课时间 |      |
| endTime       | string | 结课时间 |      |
| time          | int    | 课程学时 |      |
| courseId      | int    | 课程编号 |      |
| studyMode     | string | 修读方式 |      |

#####  ordinaryScore

| 属性                        | 类型    | 说明         | 备注         |
| --------------------------- | ------- | ------------ | ------------ |
| ordinaryScoreItemProportion | double  | 考核占比     | 平时分项占比 |
| ordinaryScoreItemName       | string  | 考核名称     |              |
| ordinaryScoreProportion     | double  | 平时分占比   |              |
| examinationId               | int     | 考试编号     |              |
| classNumber                 | int     | 班级编号     |              |
| studentId                   | int     | 学生编号     |              |
| score                       | double  | 学分数       |              |
| courseName                  | striing | 课程名称     |              |
| teacherId                   | int     | 教师编号     |              |
| studentName                 | string  | 学生姓名     |              |
| property                    | string  | 课程性质     |              |
| ordianryScoreItemId         | int     | 平时分项编号 |              |
| startTime                   | string  | 开课时间     |              |
| endTime                     | string  | 结课时间     |              |
| courseId                    | int     | 课程编号     |              |
| studyMode                   | string  | 修读方式     |              |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/teacher/unfinished",
    "data": {
        "ordinaryScoreItem": [
            {
                "score": 1.0,
                "ordinaryScoreId": 2,
                "property": "专业必修课",
                "name": "软件工程导论",
                "ordinaryProportion": 0.40,
                "startTime": "第6周",
                "endTime": "第10周",
                "time": 24,
                "courseId": 2,
                "classNumber": 1
            }
        ],
        "examination": [
            {
                "proportion": 0.80,
                "examinationId": 4,
                "classNumber": 1,
                "studentId": "201921098412",
                "score": 4.5,
                "courseName": "数据库",
                "teacherId": "3725001",
                "grade": "B",
                "studentName": "蒋涛",
                "property": "专业必修课",
                "startTime": "第1周",
                "endTime": "第16周",
                "time": 48,
                "courseId": 1,
                "studyMode": "初修"
            },
            {
                "proportion": 0.60,
                "examinationId": 3,
                "classNumber": 1,
                "studentId": "201921098380",
                "score": 1.0,
                "courseName": "软件工程导论",
                "teacherId": "3725001",
                "grade": "A",
                "studentName": "欧阳华清",
                "property": "专业必修课",
                "startTime": "第6周",
                "endTime": "第10周",
                "time": 24,
                "courseId": 2,
                "studyMode": "初修"
            }
        ],
        "ordinaryScore": [
            {
                "ordinaryScoreItemProportion": 1.00,
                "ordinaryScoreItemName": "实验3",
                "ordinaryScoreProportion": 0.20,
                "examinationId": 4,
                "classNumber": 1,
                "studentId": "201921098412",
                "score": 4.5,
                "courseName": "数据库",
                "teacherId": "3725001",
                "studentName": "蒋涛",
                "property": "专业必修课",
                "ordianryScoreItemId": 48,
                "startTime": "第1周",
                "endTime": "第16周",
                "courseId": 1,
                "studyMode": "初修"
            }
        ]
    },
    "time": "2022-01-16 16:47:30"
}
```

