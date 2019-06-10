package shunp.shunp;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

//@GRpcService
public class HelloService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> resOb) {
        HelloReply rep = HelloReply.newBuilder().setMessage("Hello" + req.getName()).build();
        resOb.onNext(rep);
        resOb.onCompleted();
    }
}
