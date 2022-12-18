FROM maven:3.5-jdk-8 AS build

#Git
RUN mkdir /password-manager-core
RUN git clone https://github.com/matthewkevinhardy/password-manager-core.git /password-manager-core

#Build
RUN mvn -f /password-manager-core clean install -DskipTests
  
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM gcr.io/distroless/java  
COPY --from=build /usr/src/app/target/password-manager-service-0.0.1-SNAPSHOT.jar /usr/app/password-manager-service-0.0.1-SNAPSHOT.jar  
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/password-manager-service-0.0.1-SNAPSHOT.jar"]  