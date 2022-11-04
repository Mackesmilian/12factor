FROM openjdk:17-jdk-alpine
COPY 12factor-0.0.1-SNAPSHOT.jar 12factor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","12factor-0.0.1-SNAPSHOT.jar"]