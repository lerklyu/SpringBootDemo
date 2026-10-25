FROM eclipse-temurin:26-jdk
EXPOSE 8081
ADD target/springBootDemo-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]