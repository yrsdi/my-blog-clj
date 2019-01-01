FROM openjdk:8-alpine

COPY target/uberjar/my-blog-clj.jar /my-blog-clj/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/my-blog-clj/app.jar"]
