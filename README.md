# grpc_kotlin_golang

If we want to use gRPC, we need three steps. 

Firstly, we have to define the interface on the proto file. A sample is below.

Secondly, we need to generate the gRPC server and interfaces from our .proto service definition. If we use in Go, we have to generate a .pb.go file using this command.

```sh
protoc -I helloworld/ helloworld/helloworld.proto --go_out=plugins=grpc:helloworld
```

If we use in Java/Kotlin, we have to generate using gradle plugins.

## ProtocolBuffer

```proto
syntax = "proto3";

option java_multiple_files = true;
option java_package = "io.grpc.examples.helloworld";
option java_outer_classname = "HelloWorldProto";

package helloworld;

// The greeting service definition.
service Greeter {
  // Sends a greeting
  rpc SayHello (HelloRequest) returns (HelloReply) {}

  rpc SayHelloAgain (HelloRequest) returns (HelloReply) {}
}

// The request message containing the user's name.
message HelloRequest {
  string name = 1;
}

// The response message containing the greetings
message HelloReply {
  string message = 1;
}

```

## Go

```go:server
import (
  "google.golang.org/grpc"
  pb "google.golang.org/grpc/examples/helloworld/helloworld"
)
func (s *server) SayHello(ctx context.Context, in *pb.HelloRequest) (*pb.HelloReply, error) {
  log.Printf("Received: %v", in.Name)
  return &pb.HelloReply{Message: "Hello " + in.Name}, nil
}
func main() {
  s := grpc.NewServer()
  pb.RegisterGreeterServer(s, &server{})
}
```

```go:client
import (
  ...
  pb "google.golang.org/grpc/examples/helloworld/helloworld"
)
func main() {
  ...
  c := pb.NewGreeterClient(conn)
  ...
  r, err := c.SayHello(ctx, &pb.HelloRequest{Name: name})
	if err != nil {
		log.Fatalf("could not greet: %v", err)
	}
	log.Printf("Greeting: %s", r.Message)
  ...
}
```

## Kotlin(SpringBoot)

