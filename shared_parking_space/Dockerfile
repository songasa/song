FROM openjdk:8-jdk-alpine
EXPOSE 8001
VOLUME /tmp
COPY ./target/shared_parking_space.jar shared_parking_space.jar
ENTRYPOINT ["java","-jar","/shared_parking_space.jar", "&"]