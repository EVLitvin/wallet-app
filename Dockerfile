FROM openjdk:17-jdk-alpine
COPY target/wallet-app-1.0.0.jar wallet-app.jar
ENTRYPOINT ["java", "-jar", "/wallet-app.jar"]