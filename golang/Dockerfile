FROM golang:1.12.2-alpine3.9 as builder 
WORKDIR / 
RUN echo http://mirror.yandex.ru/mirrors/alpine/v3.5/main > /etc/apk/repositories; \
    echo http://mirror.yandex.ru/mirrors/alpine/v3.5/community >> /etc/apk/repositories
RUN apk update \
 && apk add --no-cache \
ENV GO111MODULE=on 
COPY . / 
RUN go mod download 
RUN go get github.com/pilu/fresh 
RUN go build 
EXPOSE 28081 
CMD ["fresh", "-c", "fresh.conf"] 
