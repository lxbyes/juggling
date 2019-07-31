package me.leckie.juggling.grpc.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Logger;
import me.leckie.juggling.grpc.hellword.GreeterGrpc;
import me.leckie.juggling.grpc.hellword.HelloReply;
import me.leckie.juggling.grpc.hellword.HelloRequest;

/**
 * @author Leckie
 * @version HelloWorldServer.java, v0.1 2019/7/30 10:38
 */
public class HelloWorldServer {

  private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

  private Server server;

  private void start() throws IOException {
    int port = 50051;
    server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        HelloWorldServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
      HelloReply helloReply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
      responseObserver.onNext(helloReply);
      responseObserver.onCompleted();
    }
  }

  public static void main(String[] args) throws InterruptedException, IOException {
    final HelloWorldServer server = new HelloWorldServer();
    server.start();
    server.blockUntilShutdown();
  }
}
