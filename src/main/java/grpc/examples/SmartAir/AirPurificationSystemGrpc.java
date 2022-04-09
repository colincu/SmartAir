package grpc.examples.SmartAir;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Air purification system service provided by server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: AirPurificationSystem.proto")
public final class AirPurificationSystemGrpc {

  private AirPurificationSystemGrpc() {}

  public static final String SERVICE_NAME = "SmartAir.AirPurificationSystem";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.ChangeSpeedRequest,
      grpc.examples.SmartAir.ChangeSpeedReply> getChangeSpeedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangeSpeed",
      requestType = grpc.examples.SmartAir.ChangeSpeedRequest.class,
      responseType = grpc.examples.SmartAir.ChangeSpeedReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.ChangeSpeedRequest,
      grpc.examples.SmartAir.ChangeSpeedReply> getChangeSpeedMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.ChangeSpeedRequest, grpc.examples.SmartAir.ChangeSpeedReply> getChangeSpeedMethod;
    if ((getChangeSpeedMethod = AirPurificationSystemGrpc.getChangeSpeedMethod) == null) {
      synchronized (AirPurificationSystemGrpc.class) {
        if ((getChangeSpeedMethod = AirPurificationSystemGrpc.getChangeSpeedMethod) == null) {
          AirPurificationSystemGrpc.getChangeSpeedMethod = getChangeSpeedMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.ChangeSpeedRequest, grpc.examples.SmartAir.ChangeSpeedReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.AirPurificationSystem", "ChangeSpeed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.ChangeSpeedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.ChangeSpeedReply.getDefaultInstance()))
                  .setSchemaDescriptor(new AirPurificationSystemMethodDescriptorSupplier("ChangeSpeed"))
                  .build();
          }
        }
     }
     return getChangeSpeedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AirPurificationSystemStub newStub(io.grpc.Channel channel) {
    return new AirPurificationSystemStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AirPurificationSystemBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AirPurificationSystemBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AirPurificationSystemFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AirPurificationSystemFutureStub(channel);
  }

  /**
   * <pre>
   * Air purification system service provided by server.
   * </pre>
   */
  public static abstract class AirPurificationSystemImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a request to increase/decrease speed of the system
     * </pre>
     */
    public void changeSpeed(grpc.examples.SmartAir.ChangeSpeedRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.ChangeSpeedReply> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeSpeedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getChangeSpeedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.SmartAir.ChangeSpeedRequest,
                grpc.examples.SmartAir.ChangeSpeedReply>(
                  this, METHODID_CHANGE_SPEED)))
          .build();
    }
  }

  /**
   * <pre>
   * Air purification system service provided by server.
   * </pre>
   */
  public static final class AirPurificationSystemStub extends io.grpc.stub.AbstractStub<AirPurificationSystemStub> {
    private AirPurificationSystemStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirPurificationSystemStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirPurificationSystemStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirPurificationSystemStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a request to increase/decrease speed of the system
     * </pre>
     */
    public void changeSpeed(grpc.examples.SmartAir.ChangeSpeedRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.ChangeSpeedReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeSpeedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Air purification system service provided by server.
   * </pre>
   */
  public static final class AirPurificationSystemBlockingStub extends io.grpc.stub.AbstractStub<AirPurificationSystemBlockingStub> {
    private AirPurificationSystemBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirPurificationSystemBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirPurificationSystemBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirPurificationSystemBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a request to increase/decrease speed of the system
     * </pre>
     */
    public grpc.examples.SmartAir.ChangeSpeedReply changeSpeed(grpc.examples.SmartAir.ChangeSpeedRequest request) {
      return blockingUnaryCall(
          getChannel(), getChangeSpeedMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Air purification system service provided by server.
   * </pre>
   */
  public static final class AirPurificationSystemFutureStub extends io.grpc.stub.AbstractStub<AirPurificationSystemFutureStub> {
    private AirPurificationSystemFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirPurificationSystemFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirPurificationSystemFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirPurificationSystemFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a request to increase/decrease speed of the system
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.SmartAir.ChangeSpeedReply> changeSpeed(
        grpc.examples.SmartAir.ChangeSpeedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeSpeedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHANGE_SPEED = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AirPurificationSystemImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AirPurificationSystemImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHANGE_SPEED:
          serviceImpl.changeSpeed((grpc.examples.SmartAir.ChangeSpeedRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.ChangeSpeedReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AirPurificationSystemBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AirPurificationSystemBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.examples.SmartAir.SmartAirSystemImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AirPurificationSystem");
    }
  }

  private static final class AirPurificationSystemFileDescriptorSupplier
      extends AirPurificationSystemBaseDescriptorSupplier {
    AirPurificationSystemFileDescriptorSupplier() {}
  }

  private static final class AirPurificationSystemMethodDescriptorSupplier
      extends AirPurificationSystemBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AirPurificationSystemMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AirPurificationSystemGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AirPurificationSystemFileDescriptorSupplier())
              .addMethod(getChangeSpeedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
