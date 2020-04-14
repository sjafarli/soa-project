FROM openjdk:8

RUN mkdir /home/app
#groupadd -r appuser -g 433 && \
#useradd -u 431 -r -g appuser -d /home/app -s /sbin/nologin -c "Docker app user" appuser && \
#chown -R appuser:appuser /home/app
#
#USER appuser
WORKDIR /home/app
EXPOSE 10222

COPY build/libs/*.jar /home/app/app.jar

ENTRYPOINT exec java -jar app.jar