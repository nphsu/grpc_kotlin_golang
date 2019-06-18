FROM golang:1.12-alpine3.9 as builder 
WORKDIR /src
RUN echo http://mirror.yandex.ru/mirrors/alpine/v3.9/main > /etc/apk/repositories; \
    echo http://mirror.yandex.ru/mirrors/alpine/v3.9/community >> /etc/apk/repositories
RUN apk add --no-cache make git gcc musl-dev linux-headers curl automake autoconf libtool build-base zlib-dev
ENV GO111MODULE=on 
COPY . /src 
# RUN git clone https://github.com/google/protobuf.git && \
#     cd protobuf && \
#     ./autogen.sh && \
#     ./configure && \
#     make && \
#     make install && \
#     ldconfig && \
#     make clean && \
#     cd .. && \
#     rm -r protobuf

# Get the source from GitHub
RUN go get google.golang.org/grpc
# Install protoc-gen-go
RUN go get github.com/golang/protobuf/protoc-gen-go

RUN go mod download 
RUN go get github.com/pilu/fresh 
RUN go build
# RUN protoc -I ../proto ../proto/hello.proto --go_out=plugins=grpc:grpc
EXPOSE 28081 
CMD ["fresh", "-c", "fresh.conf"] 