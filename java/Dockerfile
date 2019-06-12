FROM openjdk:jdk
EXPOSE 8080
COPY . /src 
WORKDIR /src
RUN ls -a 
RUN ./gradlew clean
RUN ./gradlew generateProto --info
RUN ./gradlew build --info