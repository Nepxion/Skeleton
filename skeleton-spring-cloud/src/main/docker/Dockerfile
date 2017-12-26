FROM java

ENV JAR_NAME=skeleton-spring-cloud-1.0.0.jar
ENV EXPOSE_PORT=2222

ADD /${JAR_NAME} //

EXPOSE ${EXPOSE_PORT}

CMD ["./run.sh"]

ENTRYPOINT java -jar /${JAR_NAME}