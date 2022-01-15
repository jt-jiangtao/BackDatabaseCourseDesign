### 文档
> 线上部署api地址
> https://api.jiangtao.website

[统一消息返回](./统一消息返回.md)



[权限相关api和事项](./权限相关api.md)

[学生端api](./学生端api.md)

[教师端api](./教师端api.md)

[系部管理员端api](./系部管理员端api.md)

[超级管理员端api](./超级管理员端api.md)



[错误码和对应消息提示](./错误码和对应消息提示.md)



### 线上部署接口



### 文档开发标识

- 当接口开发完成并部署后将在接口上标识   ==ok==

### 后端部署

后端服务部署
ssh database@8.136.37.208 -p 22
密码: xxxxxx

jps 查看后台运行情况
kill 3932 杀进程，解处端口占用

可以使用xftp上传打好的jar包

(不能看见日志，关闭窗口不退出)
nohup java -jar BackDatabaseCourseDesign-0.0.1-SNAPSHOT.jar >> 20220110.log &
exit
OR ---------
(能看见日志，关闭窗口退出)
java -jar BackDatabaseCourseDesign-0.0.1-SNAPSHOT.jar