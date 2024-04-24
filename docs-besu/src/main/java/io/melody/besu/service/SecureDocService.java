package io.melody.besu.service;

import com.baeldung.helloworld.stubs.HelloWorldRequest;
import com.baeldung.helloworld.stubs.HelloWorldResponse;
import com.baeldung.helloworld.stubs.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class SecureDocService extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {
  private HelloWorldResponse sayHello(HelloWorldRequest request) {

    return HelloWorldResponse
        .newBuilder()
        .setGreeting("Hello "
            + Optional
            .of(request.getName())
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .orElse("World")
            + "!"
        )
        .build();
  }
  @Override
  public StreamObserver<com.baeldung.helloworld.stubs.HelloWorldRequest> sayHello(
      StreamObserver<com.baeldung.helloworld.stubs.HelloWorldResponse> responseObserver) {
    return StreamObserverUtility.proxyStream(
        responseObserver,
        this::sayHello
    );
  }
}
