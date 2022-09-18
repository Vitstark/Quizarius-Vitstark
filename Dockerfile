FROM amazoncorretto:18

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw package

CMD ["java","-jar","./target/quizarius-0.0.1-SNAPSHOT.jar"]


