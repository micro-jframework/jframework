FROM openjdk:8-jdk-alpine
COPY target/jframework.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "-Dfile.encoding=utf-8"]