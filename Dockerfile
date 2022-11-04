FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/12factor-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} 12factor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/12factor-0.0.1-SNAPSHOT.jar"]