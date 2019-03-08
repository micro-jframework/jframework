
##### 基于对spring boot的二次封装，目的是减少重复代码，提高开发效率, 收集可复用的技术

1. 实现jpa对Integer类型的时间戳的created_at, updated_at和deleted_at字段的自动维护


##### 配置文件示例: src/main/resources/application.properties

```properties
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1/jframework?useUnicode=yes&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.open-in-view=false
logging.level.root=INFO
logging.config=classpath:logback-spring.xml

spring.mail.host=smtp.qq.com
spring.mail.port=587
spring.mail.username=username
spring.mail.password=password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true


logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.jpa.properties.hibernate.format_sql=true
```

##### docker 构建步骤
```shell
mvn package
docker build -t jframework .
docker run -it --rm -p 8780:8080 jframework
```

访问127.0.0.1:8780即可看到输出效果

##### 工具类

$.date.currentSecond() 获取当前unix时间戳，单位为秒

MapperUtil::to(对象1, SomeClass.class) 创建SomeClass类的对象，并把对象1的属性复制到SomeClass的对象上, 在stream流式类型转换时非常有用
```java
orderList
    .stream()
    .map(order -> $.mapper.to(order, OrderDto.class))
    .collect(Collectors.toList());
```

##### 验证器

StringLength 验证器，验证参数字符串的长度范围

##### 控制台日志显示全包名

logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.400logger{390}):%line{cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}

##### 参考资料

1. https://github.com/kmtong/logback-redis-appender
2. https://github.com/looly/hutool
3. https://github.com/gudaoxuri/dew-common
4. https://github.com/gudaoxuri/dew
5. https://github.com/xdd-organ/xdd-nasa
6. https://github.com/indrabasak/swagger-deepdive
