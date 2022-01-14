# DatabaseCourseDesign

DatabaseCourseDesign

- 管理员端为了提供给上层权限用户调用
  - 在controller底层未权限验证
  - 上层接口必须提供系部id给系部管理员接口
- 关于权限验证
  - 学生端和教师端权限controller层已验证
  - 专业管理员端和学校管理员接口必须在controller前验证