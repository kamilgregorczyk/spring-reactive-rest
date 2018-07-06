FROM openjdk:8-jre-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/product/service.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "-Dspring.profiles.active=production", "/usr/share/product/service.jar"]