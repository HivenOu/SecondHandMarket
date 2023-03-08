#FROM openjdk:8-alpine
FROM java:8u111-alpine

#处理时间
RUN apk update \
    && apk add tzdata \
   && cp -r -f /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
# 中文乱码问题
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
# 中国时区问题
ENV TZ  Asia/Shanghai
COPY target/app.jar /work/app.jar
WORKDIR /work

CMD ["java","-jar","app.jar"]