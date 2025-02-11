FROM maven:3.9-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
COPY --from=build /target/homora-0.0.1-SNAPSHOT.jar homora.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","homora.jar" ]