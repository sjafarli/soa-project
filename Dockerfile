FROM openjdk:8

RUN mkdir /home/invoiceapp
#groupadd -r appuser -g 433 && \
#useradd -u 431 -r -g appuser -d /home/app -s /sbin/nologin -c "Docker app user" appuser && \
#chown -R appuser:appuser /home/app
#
#USER appuser
WORKDIR /home/invoiceapp
EXPOSE 8082

COPY build/libs/*.jar /home/invoiceapp/app.jar

ENTRYPOINT exec java -jar app.jar