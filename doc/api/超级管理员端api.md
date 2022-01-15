### 查看学校所有专业==ok==

> POST   /api/school/dept

#### 请求参数

##### Body

null

#### 返回参数

##### data

|    属性     | 类型 |   说明   | 备注 |
| :---------: | :--: | :------: | :--: |
| List\<dept> | list | 专业列表 |      |

##### dept

|   属性   |  类型  |   说明   | 备注 |
| :------: | :----: | :------: | :--: |
| deptName | string | 专业名称 |      |
|  deptId  |  int   | 专业编号 |      |

#### 成功示例

```
{
    "code": 1020,
    "message": "处理成功",
    "request": "POST /api/school/dept",
    "data": [
        {
            "deptName": "计算机科学学院",
            "deptId": 1
        },
        {
            "deptName": "数学与统计学院",
            "deptId": 2
        }
    ],
    "time": "2022-01-15 23:12:34"
}
```

