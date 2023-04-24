FROM maven:3.8.1-jdk-11 as builds
WORKDIR /app
COPY / /app
RUN mvn -e clean install -DskipTests

FROM maven:3.8.1-jdk-11
WORKDIR /app
COPY --from=builds /app/target/*.jar /app/cms.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cms.jar"]