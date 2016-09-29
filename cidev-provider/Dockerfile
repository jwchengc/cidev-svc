FROM registry.isspaas.com/java:openjdk-8-jre
RUN mkdir /app
ADD target/cidev-provider.jar /app/cidev-provider.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "cidev-provider.jar"]