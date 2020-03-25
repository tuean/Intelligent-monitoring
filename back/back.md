#### src目录说明

- annotation： 注解
- aop：aspect切面，主要用于登录时token换取用户信息、鉴定当前用户是否合法访问当前资源
- config：系统静态变量，系统设置
- dao：数据库查询语句接口
- entity.db：数据库实体对象
- enums：系统所用到的枚举量
- exception：主要用来对异常进行封装，及由原来的返回从HttpStatus=500改为HttpStatus=200 内容变为{code: !0} 语义更加清晰
- Filter: 过滤器  跨域处理 + 请求日志记录
- helper：一些中间变量
- model：程序中使用到的一些实体对象
- service：具体功能接口及实现
- task：定时任务
- utils：工具类
- web：http接口定义



#### resources目录说明
- generator：使用mybatis-generator-maven-plugin进行mybatis相关文件的生成，所需要用到的相关配置文件
- mapper：mybatis的mapper文件，存放的是对应的sql
- application.properties: springboot配置文件 
- Logback-spring.xml：log配置文件



#### 项目说明

所有的请求基于web文件夹定义的接口进入，通过aop文件夹下的切面进行代理，作统一的登录、权限判断，根据前端请求中携带的 x-auth-token 请求头进行token的判断，获取用户的role角色字段，与对应方法上的AuthLevel注解中的值进行判断，以此鉴定是否当前用户是否拥有该功能的权限。