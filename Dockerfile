FROM eclipse-temurin:17-jre

RUN mkdir /app

WORKDIR /app

ADD ./api/target/lottery-drawing-results-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "lottery-drawing-results-api-1.0.0-SNAPSHOT.jar"]
