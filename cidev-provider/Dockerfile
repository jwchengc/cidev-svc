FROM 10.24.17.208/library/java:8.0
RUN mkdir /app
ADD target/cidev-provider.jar /app/cidev-provider.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "cidev-provider.jar"]
