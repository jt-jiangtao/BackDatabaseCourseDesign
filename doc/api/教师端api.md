### 登陆==ok==

> 同学生端



### 所有所带班级==ok==

> POST /api/teacher/course

#### 请求参数

##### Body

| 属性  |  类型  |      说明       |        备注        | 是否必填 |
| :---: | :----: | :-------------: | :----------------: | :------: |
| token | string | token，验证身份 |                    |    是    |
| year  | string |      学年       |     2021-2022      |    否    |
| term  | string |      学期       | 第一学期、第二学期 |    否    |

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



### 平时成绩与期末考试比例查看==ok==

> POST  /api/teacher/proportion/get

#### 请求参数

##### Body

| 属性     | 类型 | 说明     | 备注 | 是否必填 |
| -------- | ---- | -------- | ---- | -------- |
| token    |      |          |      | 是       |
| courseId | int  | 课程编号 |      | 是       |

#### 返回结果

##### data

| 属性             | 类型   | 说明         | 备注 |
| ---------------- | ------ | ------------ | ---- |
| normalProportion | double | 平时成绩占比 |      |
| examProportion   | double | 考试成绩占比 |      |
| id               | int    | 课程编号     |      |



### 平时成绩与期末开始比例修改==ok==

> POST  /api/teacher/proportion/update

#### 请求参数

##### Body

| 属性             | 类型   | 说明         | 备注 | 是否必填 |
| ---------------- | ------ | ------------ | ---- | -------- |
| token            |        |              |      |          |
| courseId         | int    | 课程编号     |      |          |
| normalProportion | double | 平时成绩占比 |      |          |
| examProportion   | double | 考试成绩占比 |      |          |

#### 返回结果

##### data


| 属性             | 类型   | 说明         | 备注 |
| ---------------- | ------ | ------------ | ---- |
| normalProportion | double | 平时成绩占比 |      |
| examProportion   | double | 考试成绩占比 |      |
| id               | int    | 课程编号     |      |



### 平时成绩项修改==ok==

> POST /api/teacher/normalScore/item/update

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩项查看==ok==

> POST /api/teacher/normalScore/item/get

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩查看==ok==

> POST /api/teacher/normalScore/get

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩增加（excel：下载模板文件）==ok==

> POST /api/teacher/normalScore/download/excel

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩增加（excel：上传文件）

> POST /api/teacher/normalScore/upload/excel

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩增加（sql：上传文件）

> POST /api/teacher/normalScore/upload/sql

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩增加（json提交）

> POST /api/teacher/normalScore/add

#### 请求参数

##### Body

#### 返回参数

##### data





### 平时成绩删除

>  POST /api/teacher/normalScore/delete

#### 请求参数

##### Body

#### 返回参数

##### data



### 平时成绩修改

> POST /api/teacher/normalScore/update

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩查看

> POST /api/teacher/normalScore/add/excel

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩增加（excel：下载模板文件）

> POST /api/teacher/examScore/download/excel

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩增加（excel：上传文件）

> POST /api/teacher/examScore/upload/excel

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩增加（sql：上传文件）

> POST /api/teacher/examScore/upload/sql

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩增加（json提交）

> POST /api/teacher/examScore/add

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩删除

> POST /api/teacher/examScore/delete

#### 请求参数

##### Body

#### 返回参数

##### data



### 考试成绩修改

> POST /api/teacher/examScore/update

#### 请求参数

##### Body

#### 返回参数

##### data



### 班级成绩分布

> POST /api/teacher/distribute

#### 请求参数

##### Body

#### 返回参数

##### data



### 班级不及格人数及信息

> POST /api/teacher/fail

#### 请求参数

##### Body

#### 返回参数

##### data



### 最低分、最高分、平均分

> POST /api/teacher/extreme

#### 请求参数

##### Body

#### 返回参数

##### data



### 查看未录完的信息

> POST /api/teacher/unfinished

#### 请求参数

##### Body

#### 返回参数

##### data

