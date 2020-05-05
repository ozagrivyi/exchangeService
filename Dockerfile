FROM openjdk:12-jdk-alpine
EXPOSE 8080
EXPOSE 5005
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar","-Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"]