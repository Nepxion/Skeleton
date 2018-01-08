FROM openjdk:8-jre-alpine
ADD target/*.jar /app.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "/skeleton-service-1.0.0.jar"]