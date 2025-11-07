FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/conditional2-1.0-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]