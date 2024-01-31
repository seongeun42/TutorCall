FROM amazoncorretto:17

LABEL authors="jaechan"

#RUN yum install wget -y
#RUN yum install unzip -y
#ENV GRADLE_VERSION=7.3.3
#
#RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp \
#&& unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip \
#&& rm /tmp/gradle-${GRADLE_VERSION}-bin.zip
#ENV GRADLE_HOME=/opt/gradle/gradle-${GRADLE_VERSION}
#ENV PATH=$PATH:$GRADLE_HOME/bin


RUN mkdir app
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ls -al
RUN java -version
RUN ./gradlew spotlessApply
RUN ./gradlew bootJar
RUN mv ./build/libs/*-SNAPSHOT.jar ./
CMD ["java", "-jar", "*SNAPSHOT.jar"]