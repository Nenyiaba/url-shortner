FROM openjdk:8-jdk-alpine
ADD target/url-shortner-0.0.1-SNAPSHOT.jar url-shortner-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","url-shortner-0.0.1-SNAPSHOT.jar"]

