FROM maven AS build

WORKDIR /app/orangeGRS

COPY . .

RUN mvn clean package

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/orangeGRS/target/orangeGRS-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
