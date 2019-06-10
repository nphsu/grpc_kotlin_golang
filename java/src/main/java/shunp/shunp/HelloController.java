package shunp.shunp;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.Greeter;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class HelloController {

    private GreeterGrpc.GreeterBlockingStub blockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 30000).usePlaintext().build();

        blockingStub = GreeterGrpc.newBlockingStub(managedChannel);
    }

    @GetMapping("/")
    public HttpStatus hello() {
        HelloRequest request = HelloRequest.newBuilder().setName("Shunp").build();
        HelloReply reply = blockingStub.sayHello(request);
        System.out.println(reply);
        return HttpStatus.OK;
    }
}
