FROM amazoncorretto:17-alpine
LABEL authors="jaechan"

RUN mkdir app
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew :spotlessApply
RUN ./gradlew clean build
RUN mv ./build/libs/*-SNAPSHOT.jar ./
CMD ["java", "-jar", "*SNAPSHOT.jar"]