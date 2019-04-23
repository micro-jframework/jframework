
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=neatlife_jframework&metric=alert_status)](https://sonarcloud.io/dashboard?id=neatlife_jframework)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.neatlife/jframework.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.neatlife%22%20AND%20a:%22jframework%22)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

##### 基于对spring boot的二次封装，目的是减少重复代码，提高开发效率, 收集可复用的技术

1. 实现jpa对Long类型的时间戳的created_at, updated_at和deleted_at字段的自动维护
2. 日志集成elk
3. 日期和json工具类
4. 集成redis
5. 集成数据库操作jpa
6. 集成swagger文档
7. 控制器发生错误，全局拦截
8. 支持docker部署
9. 支持k8s部署
10. 支持json序列化时自定义小数位数
11. 接口版本控制
12. 保留精度的数学计算工具类
13. http请求工具类
14. redis和redis分布式锁工具类
15. json响应工具类
16. 支持Jenkinsfile构建
17. 二维码生成工具类
18. redis阻塞消息队列
17. id生成工具类

##### Maven中引入Jar包

```xml
<dependency>
    <groupId>com.github.neatlife</groupId>
    <artifactId>jframework</artifactId>
    <version>0.0.11</version>
</dependency>
```

##### 配置文件示例: src/main/resources/application.properties

```properties
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1/jframework?useUnicode=yes&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
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

# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=localhost
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制） 默认 8
spring.redis.lettuce.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1
spring.redis.lettuce.pool.max-wait=-1
# 连接池中的最大空闲连接 默认 8
spring.redis.lettuce.pool.max-idle=8
# 连接池中的最小空闲连接 默认 0
spring.redis.lettuce.pool.min-idle=0

# 防止邮件重发
jframework.mail.enable-no-repeat=false
jframework.mail.repeat-interval=600
# 钉钉机器人地址
jframework.notification.ding-talk-url=https://oapi.dingtalk.com/robot/send?access_token=钉钉机器人token
jframework.notification.ding-talk-to=钉钉收件人手机号
```

##### docker 构建步骤
```shell
# 本地调试
mvn clean package
docker build -t jframework .
docker run -it --rm -p 8780:8080 jframework

# 推送阿里云
docker build -t jframework .
docker tag jframework:latest registry.cn-hangzhou.aliyuncs.com/suxiaolin/jframework:latest
docker push registry.cn-hangzhou.aliyuncs.com/suxiaolin/jframework:latest
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

##### json序列化时小数指定小数位数
```
目前支持对Double和BigDecimal两种类型进行自定义小数位数
Double: 在字段上加上DoubleSpecify(2)注解即可，2是需要保留的小数位数
BigDecimal: 在字段上加上BigDecimalSpecify(2)
```

##### 验证器

StringLength 验证器，验证参数字符串的长度范围

##### 控制台日志显示全包名

logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.400logger{390}):%line{cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}

##### logstash 配置
```conf
input {
    redis {
        data_type => "list"
        key => "logstash"
        host => "127.0.0.1"
        port => 6379
        threads => 5
        codec => "json"
    }
}
filter {

}
output {

    elasticsearch {
        hosts => ["elasticsearch:9200"]
        index => "logstash-%{type}-%{+YYYY.MM.dd}"
        document_type => "%{type}"
        workers => 1
        template_overwrite => true
    }
    stdout{}
}
```

##### 参考资料

1. https://github.com/kmtong/logback-redis-appender
2. https://github.com/looly/hutool
3. https://github.com/gudaoxuri/dew-common
4. https://github.com/gudaoxuri/dew
5. https://github.com/xdd-organ/xdd-nasa
6. https://github.com/indrabasak/swagger-deepdive
7. https://github.com/ityouknow/spring-boot-examples
8. https://github.com/WellJay/spring-data-redis-tools
