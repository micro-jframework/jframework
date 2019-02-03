
##### 基于对spring boot的二次封装，目的是减少重复代码，提高开发效率

1. 实现jpa对Integer类型的时间戳的created_at, updated_at和deleted_at字段的自动维护


##### 配置文件示例: src/main/resources/application.properties

```properties
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1/jframework?useUnicode=yes&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```

##### docker 构建步骤
```shell
mvn package
docker build -t jframework .
docker run -it --rm -p 8780:8080 jframework
```

访问127.0.0.1:8780即可看到输出效果

##### 工具类

DateUtil::currentSecond() 获取当前unix时间戳，单位为秒

MapperUtil::to(对象1, SomeClass.class) 创建SomeClass类的对象，并把对象1的属性复制到SomeClass的对象上, 在stream流式类型转换时非常有用

##### 验证器

StringLength 验证器，验证参数字符串的长度范围

##### 参考资料

1. https://github.com/kmtong/logback-redis-appender
2. https://github.com/looly/hutool
