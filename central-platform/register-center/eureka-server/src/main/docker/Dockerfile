FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD eureka-server.jar eureka-server.jar
RUN sh -c 'touch /eureka-server.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka-server.jar" ]