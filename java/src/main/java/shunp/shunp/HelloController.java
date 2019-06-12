package shunp.shunp;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.annotation.PostConstruct;

@RestController
public class HelloController {

    private GreeterGrpc.GreeterBlockingStub blockingStub;

    @Value("${grpcserver:localhost}")
    private String host;

    @Value("${grpcserver.port:28081")
    private Integer port;

    @PostConstruct
    private void init() {
        String ip = this.findIpAddr().orElseThrow(RuntimeException::new);
        ManagedChannel managedChannel = ManagedChannelBuilder
               .forAddress(ip, port).usePlaintext().build();
        blockingStub = GreeterGrpc.newBlockingStub(managedChannel);
    }

    @GetMapping("/")
    public HttpStatus hello() {
        HelloRequest request = HelloRequest.newBuilder().setName("Shunp").build();
        HelloReply reply = blockingStub.sayHello(request);
        System.out.println(reply);
        return HttpStatus.OK;
    }

    @GetMapping("/shunp")
    public String shunp() {
        System.out.println(host);
        return "shunp";
    }

    private Optional<String> findIpAddr() {
        try {
            java.net.InetAddress inetAddress = java.net.InetAddress.getByName(host);
            return Optional.of(inetAddress.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
