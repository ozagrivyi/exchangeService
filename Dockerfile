FROM openjdk:12-jdk-alpine
EXPOSE 8080
EXPOSE 5105
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5105"]