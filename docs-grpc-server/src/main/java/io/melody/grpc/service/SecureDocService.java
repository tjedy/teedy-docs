package io.melody.grpc.service;

import io.grpc.stub.StreamObserver;
import io.melody.grpc.stubs.SecureDocRequest;
import io.melody.grpc.stubs.SecureDocResponse;
import io.melody.grpc.stubs.SecureDocServiceGrpc;
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
