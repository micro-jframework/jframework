FROM openjdk:8-jdk-alpine
COPY target/jframework.jar /app.jar
ENTRYPOINT ["java", "-Dfile.encoding=utf-8", "-Xms128m", "-Xmx256m", "-Dapp.id=100001", "-Dapollo.cluster=dev", "-Dapollo.configService=http://192.168.10.160:8880", "-Dapollo.bootstrap.enabled=true", "-jar", "/app.jar"]