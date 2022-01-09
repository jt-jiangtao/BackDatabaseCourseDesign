DROP TABLE IF EXISTS user;
CREATE TABLE user(
    number VARCHAR(255) NOT NULL   COMMENT '学号/工号;学号/工号' ,
    password VARCHAR(255) NOT NULL   COMMENT '密码;登录密码' ,
    identity VARCHAR(255) NOT NULL   COMMENT '身份;身份（教师或学生）' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;账号是否有效' ,
    PRIMARY KEY (number)
)  COMMENT = '用户表（常用数据）（其它详细数据被分表）';

DROP TABLE IF EXISTS role;
CREATE TABLE role(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    roleName VARCHAR(255) NOT NULL   COMMENT '角色名称;角色名称' ,
    staus VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;角色是否可用' ,
    PRIMARY KEY (id)
)  COMMENT = '角色表';

DROP TABLE IF EXISTS userRole;
CREATE TABLE userRole(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    userId INT NOT NULL   COMMENT '用户编号;用户编号' ,
    roleId INT NOT NULL   COMMENT '角色编号;角色编号' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;该条数据是否可用' ,
    PRIMARY KEY (id)
)  COMMENT = '用户角色表';

DROP TABLE IF EXISTS access;
CREATE TABLE access(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    accessName VARCHAR(255) NOT NULL   COMMENT '权限名称;权限名称（根据权限名称来确定是否可以访问某一资源）' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;是否可用' ,
    PRIMARY KEY (id)
)  COMMENT = '权限';

DROP TABLE IF EXISTS roleAccess;
CREATE TABLE roleAccess(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    roleId INT NOT NULL   COMMENT '角色编号;角色编号' ,
    accessId INT NOT NULL   COMMENT '权限编号;权限编号' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;是否可用' ,
    PRIMARY KEY (id)
)  COMMENT = '角色权限表';

DROP TABLE IF EXISTS studentInfo;
CREATE TABLE studentInfo(
    number INT NOT NULL   COMMENT '学号;学号' ,
    classId INT NOT NULL   COMMENT '班级编号;班级编号' ,
    name VARCHAR(255) NOT NULL   COMMENT '姓名;姓名' ,
    sex VARCHAR(255) NOT NULL   COMMENT '性别;性别' ,
    birthday DATETIME NOT NULL   COMMENT '出生日期;出生日期' ,
    entryTime DATE NOT NULL   COMMENT '入学时间;入学时间' ,
    leaveTime DATE NOT NULL   COMMENT '毕业时间;毕业时间' ,
    homeLocation VARCHAR(255) NOT NULL   COMMENT '家庭住址;家庭住址' ,
    identityNumber VARCHAR(18) NOT NULL   COMMENT '身份证号;身份证号' ,
    PRIMARY KEY (number)
)  COMMENT = '学生信息表';

DROP TABLE IF EXISTS teacherInfo;
CREATE TABLE teacherInfo(
    number INT NOT NULL   COMMENT '工号;工号' ,
    taskGroupId INT NOT NULL   COMMENT '课题组编号;课题组编号' ,
    name VARCHAR(255) NOT NULL   COMMENT '姓名;姓名' ,
    sex VARCHAR(255) NOT NULL   COMMENT '性别;性别' ,
    birthday DATETIME NOT NULL   COMMENT '出生日期;出生日期' ,
    entryTime DATE NOT NULL   COMMENT '入职时间;入学时间' ,
    leaveTime DATE NOT NULL   COMMENT '离职时间;毕业时间' ,
    homeLocation VARCHAR(255) NOT NULL   COMMENT '家庭住址;家庭住址' ,
    identityNumber VARCHAR(18) NOT NULL   COMMENT '身份证号;身份证号' ,
    educationBackgroud VARCHAR(255) NOT NULL   COMMENT '学历;学历' ,
    professionalTitle VARCHAR(255) NOT NULL   COMMENT '职称;职称' ,
    PRIMARY KEY (number)
)  COMMENT = '教师信息表';

DROP TABLE IF EXISTS department;
CREATE TABLE department(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    deptName VARCHAR(255) NOT NULL   COMMENT '系名称;系名称' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '系部表';

DROP TABLE IF EXISTS specialty;
CREATE TABLE specialty(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    specialtyName INT NOT NULL   COMMENT '专业名称;专业名称' ,
    deptId INT NOT NULL   COMMENT '所属系部;所属系部' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '专业表';

DROP TABLE IF EXISTS taskGroup;
CREATE TABLE taskGroup(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    groupName VARCHAR(255) NOT NULL   COMMENT '课题组名称;课题组名称' ,
    specialtyId INT NOT NULL   COMMENT '专业编号;专业编号' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '课题组表';

DROP TABLE IF EXISTS class;
CREATE TABLE class(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    number INT NOT NULL   COMMENT '班级号;班级号' ,
    specialtyId INT NOT NULL   COMMENT '专业编号;专业编号' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '班级表';

DROP TABLE IF EXISTS course;
CREATE TABLE course(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '课程编号;课程编号' ,
    teacherId VARCHAR(255) NOT NULL   COMMENT '教师编号;教师编号' ,
    startTime VARCHAR(90) NOT NULL   COMMENT '开课时间;开课时间' ,
    endTime VARCHAR(90) NOT NULL   COMMENT '结课时间;结课时间' ,
    score DECIMAL(2,1) NOT NULL   COMMENT '学分;学分' ,
    time INT NOT NULL   COMMENT '学时;学时' ,
    introduce VARCHAR(255)    COMMENT '简介;简介' ,
    property VARCHAR(255) NOT NULL   COMMENT '性质;性质' ,
    ordinaryScoreId INT    COMMENT '平时成绩编号;平时成绩编号' ,
    examinationId INT    COMMENT '考试编号;考试编号' ,
    arrangeExamination VARCHAR(1)   DEFAULT 0 COMMENT '是否编排考试;是否编排考试' ,
    yearTermId INT NOT NULL   COMMENT '学年学期编号;学年学期编号' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '课程表';

DROP TABLE IF EXISTS studentCourse;
CREATE TABLE studentCourse(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    studentId INT NOT NULL   COMMENT '学生编号;学生编号' ,
    courseId INT NOT NULL   COMMENT '课程编号;课程编号' ,
    studyMode VARCHAR(255) NOT NULL   COMMENT '修读方式;修读方式' ,
    score INT NOT NULL   COMMENT '分数;分数' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '学生课程表（学生选课表）';

DROP TABLE IF EXISTS examination;
CREATE TABLE examination(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    courseId INT NOT NULL   COMMENT '课程编号;课程编号' ,
    proportion DECIMAL(3,2) NOT NULL   COMMENT '占比;占比' ,
    grade VARCHAR(1) NOT NULL   COMMENT '等级;等级' ,
    maxScore INT NOT NULL  DEFAULT 100 COMMENT '分值;分值' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '考试表';

DROP TABLE IF EXISTS studentExamination;
CREATE TABLE studentExamination(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    studentId INT NOT NULL   COMMENT '学生编号;学生编号' ,
    examinationId INT NOT NULL   COMMENT '考试编号;考试编号' ,
    score DECIMAL(3,1) NOT NULL   COMMENT '考试成绩;考试成绩' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '学生考试表（学生成绩表）';

DROP TABLE IF EXISTS ordinaryScore;
CREATE TABLE ordinaryScore(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    courseId INT NOT NULL   COMMENT '课程编号;课程编号' ,
    proportion DECIMAL(3,2) NOT NULL   COMMENT '占比;占比' ,
    PRIMARY KEY (id)
)  COMMENT = '平时成绩表';

DROP TABLE IF EXISTS studentOrdinaryScore;
CREATE TABLE studentOrdinaryScore(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    studentId INT NOT NULL   COMMENT '学生编号;学生编号' ,
    ordinaryScoreId INT NOT NULL   COMMENT '平时成绩编号;平时成绩编号' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;有效' ,
    PRIMARY KEY (id)
)  COMMENT = '学生平时成绩表';

DROP TABLE IF EXISTS studentOrdinaryScoreItem;
CREATE TABLE studentOrdinaryScoreItem(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    itemName VARCHAR(255) NOT NULL   COMMENT '单项名称;单项名称' ,
    score VARCHAR(255) NOT NULL   COMMENT '分数;分数' ,
    proportion DECIMAL(3,2) NOT NULL   COMMENT '占比;占比' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;状态' ,
    PRIMARY KEY (id)
)  COMMENT = '学生平时成绩项表';

DROP TABLE IF EXISTS yearTerm;
CREATE TABLE yearTerm(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    year VARCHAR(255) NOT NULL   COMMENT '学年;学年' ,
    term VARCHAR(255) NOT NULL   COMMENT '学期;学期' ,
    PRIMARY KEY (id)
)  COMMENT = '学年学期表';

DROP TABLE IF EXISTS inputTime;
CREATE TABLE inputTime(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '编号;编号' ,
    yearTermId INT NOT NULL   COMMENT '学年学期编号;学年学期编号' ,
    startTime DATETIME NOT NULL   COMMENT '录入开始时间;录入开始时间' ,
    endTime DATETIME NOT NULL   COMMENT '录入结束;录入结束' ,
    status VARCHAR(1) NOT NULL  DEFAULT 1 COMMENT '状态;状态' ,
    PRIMARY KEY (id)
)  COMMENT = '录入时间表';

