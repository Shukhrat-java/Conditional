FROM eclipse-temurin:8-jre-alpine
EXPOSE 8081
COPY target/conditional2-1.0-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java", "-jar", "/myapp.jar"]