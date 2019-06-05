FROM golang:1.12.5-alpine3.9 as builder 
WORKDIR /src
RUN echo http://mirror.yandex.ru/mirrors/alpine/v3.9/main > /etc/apk/repositories; \
    echo http://mirror.yandex.ru/mirrors/alpine/v3.9/community >> /etc/apk/repositories
RUN apk add --no-cache make git gcc musl-dev linux-headers
ENV GO111MODULE=on 
COPY . /src 
RUN go mod download 
RUN go get github.com/pilu/fresh 
RUN go build
EXPOSE 28081 
CMD ["fresh", "-c", "fresh.conf"] 