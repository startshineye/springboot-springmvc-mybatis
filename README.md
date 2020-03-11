SPRING BOOT + SPRING MVC + SPRING CORE + MYBATIS + DRUID整合案例  

框架整合的思路:
（1）系统启动的时候，首先会去扫描DruidDBConfig类，这就可以将外部的druid连接池配置加载进来，同时初始化出来一个druid连接池的DataSource bean

（2）mybatis-spring-boot-starter接着开始干活儿，发现了一个DataSource bean，就会将其注入SqlSessionFactory，再创建SqlSessionTemplate，接着扫描Mapper接口，将SqlSessionTemplate注入每个Mapper，然后将Mapper放入spring容器中来管理

（3）spring boot的@ComponantScan注解开始干活儿，自动扫描所有的bean，依次初始化实例以及注入依赖，UserServiceImpl（将UserMapper注入其中），UserCongtroller（将UserServiceImpl注入其中）

（4）此时浏览器发送请求，会先由controlller处理，接着调用service，再调用mapper。mapper底层会基于druid连接池访问到数据库，进行数据操作

至此，最常规的几个框架整合和使用，就完成了
