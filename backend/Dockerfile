FROM amazoncorretto:17-alpine as builder
LABEL authors="jaechan"

RUN mkdir app
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew :spotlessApply
RUN ./gradlew bootJar
RUN mv ./build/libs/*-SNAPSHOT.jar ./

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app /app
CMD ["java","-jar", "*-SNAPSHOT.jar"]
