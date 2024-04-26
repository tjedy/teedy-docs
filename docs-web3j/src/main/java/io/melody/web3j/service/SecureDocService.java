package io.melody.web3j.service;

import io.grpc.stub.StreamObserver;
import io.melody.web3j.stubs.SecureDocRequest;
import io.melody.web3j.stubs.SecureDocResponse;
import io.melody.web3j.stubs.SecureDocServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class SecureDocService extends SecureDocServiceGrpc.SecureDocServiceImplBase {
  private SecureDocResponse processFile(SecureDocRequest request) {

    return SecureDocResponse
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
  public StreamObserver<SecureDocRequest> processFile(StreamObserver<SecureDocResponse> responseObserver) {
    return StreamObserverUtility.proxyStream(
        responseObserver,
        this::processFile
    );
  }
}
