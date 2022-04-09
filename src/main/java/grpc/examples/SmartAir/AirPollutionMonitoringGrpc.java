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
 * Service provided by the monitoring server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: AirPolutionMonitoring.proto")
public final class AirPollutionMonitoringGrpc {

  private AirPollutionMonitoringGrpc() {}

  public static final String SERVICE_NAME = "SmartAir.AirPollutionMonitoring";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest,
      grpc.examples.SmartAir.AirQualityReply> getRoomAirQualityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RoomAirQuality",
      requestType = grpc.examples.SmartAir.AirQualityRequest.class,
      responseType = grpc.examples.SmartAir.AirQualityReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest,
      grpc.examples.SmartAir.AirQualityReply> getRoomAirQualityMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest, grpc.examples.SmartAir.AirQualityReply> getRoomAirQualityMethod;
    if ((getRoomAirQualityMethod = AirPollutionMonitoringGrpc.getRoomAirQualityMethod) == null) {
      synchronized (AirPollutionMonitoringGrpc.class) {
        if ((getRoomAirQualityMethod = AirPollutionMonitoringGrpc.getRoomAirQualityMethod) == null) {
          AirPollutionMonitoringGrpc.getRoomAirQualityMethod = getRoomAirQualityMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.AirQualityRequest, grpc.examples.SmartAir.AirQualityReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.AirPollutionMonitoring", "RoomAirQuality"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AirQualityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AirQualityReply.getDefaultInstance()))
                  .setSchemaDescriptor(new AirPollutionMonitoringMethodDescriptorSupplier("RoomAirQuality"))
                  .build();
          }
        }
     }
     return getRoomAirQualityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.AllAirQualityRequest,
      grpc.examples.SmartAir.AirQualityReply> getAllRoomAirQualityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllRoomAirQuality",
      requestType = grpc.examples.SmartAir.AllAirQualityRequest.class,
      responseType = grpc.examples.SmartAir.AirQualityReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.AllAirQualityRequest,
      grpc.examples.SmartAir.AirQualityReply> getAllRoomAirQualityMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.AllAirQualityRequest, grpc.examples.SmartAir.AirQualityReply> getAllRoomAirQualityMethod;
    if ((getAllRoomAirQualityMethod = AirPollutionMonitoringGrpc.getAllRoomAirQualityMethod) == null) {
      synchronized (AirPollutionMonitoringGrpc.class) {
        if ((getAllRoomAirQualityMethod = AirPollutionMonitoringGrpc.getAllRoomAirQualityMethod) == null) {
          AirPollutionMonitoringGrpc.getAllRoomAirQualityMethod = getAllRoomAirQualityMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.AllAirQualityRequest, grpc.examples.SmartAir.AirQualityReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.AirPollutionMonitoring", "AllRoomAirQuality"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AllAirQualityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AirQualityReply.getDefaultInstance()))
                  .setSchemaDescriptor(new AirPollutionMonitoringMethodDescriptorSupplier("AllRoomAirQuality"))
                  .build();
          }
        }
     }
     return getAllRoomAirQualityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest,
      grpc.examples.SmartAir.AirQualityReply> getRoomsAirQualityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RoomsAirQuality",
      requestType = grpc.examples.SmartAir.AirQualityRequest.class,
      responseType = grpc.examples.SmartAir.AirQualityReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest,
      grpc.examples.SmartAir.AirQualityReply> getRoomsAirQualityMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest, grpc.examples.SmartAir.AirQualityReply> getRoomsAirQualityMethod;
    if ((getRoomsAirQualityMethod = AirPollutionMonitoringGrpc.getRoomsAirQualityMethod) == null) {
      synchronized (AirPollutionMonitoringGrpc.class) {
        if ((getRoomsAirQualityMethod = AirPollutionMonitoringGrpc.getRoomsAirQualityMethod) == null) {
          AirPollutionMonitoringGrpc.getRoomsAirQualityMethod = getRoomsAirQualityMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.AirQualityRequest, grpc.examples.SmartAir.AirQualityReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.AirPollutionMonitoring", "RoomsAirQuality"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AirQualityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AirQualityReply.getDefaultInstance()))
                  .setSchemaDescriptor(new AirPollutionMonitoringMethodDescriptorSupplier("RoomsAirQuality"))
                  .build();
          }
        }
     }
     return getRoomsAirQualityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest,
      grpc.examples.SmartAir.AveAirQualityReply> getAveRoomAirQualityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AveRoomAirQuality",
      requestType = grpc.examples.SmartAir.AirQualityRequest.class,
      responseType = grpc.examples.SmartAir.AveAirQualityReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest,
      grpc.examples.SmartAir.AveAirQualityReply> getAveRoomAirQualityMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.AirQualityRequest, grpc.examples.SmartAir.AveAirQualityReply> getAveRoomAirQualityMethod;
    if ((getAveRoomAirQualityMethod = AirPollutionMonitoringGrpc.getAveRoomAirQualityMethod) == null) {
      synchronized (AirPollutionMonitoringGrpc.class) {
        if ((getAveRoomAirQualityMethod = AirPollutionMonitoringGrpc.getAveRoomAirQualityMethod) == null) {
          AirPollutionMonitoringGrpc.getAveRoomAirQualityMethod = getAveRoomAirQualityMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.AirQualityRequest, grpc.examples.SmartAir.AveAirQualityReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.AirPollutionMonitoring", "AveRoomAirQuality"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AirQualityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.AveAirQualityReply.getDefaultInstance()))
                  .setSchemaDescriptor(new AirPollutionMonitoringMethodDescriptorSupplier("AveRoomAirQuality"))
                  .build();
          }
        }
     }
     return getAveRoomAirQualityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AirPollutionMonitoringStub newStub(io.grpc.Channel channel) {
    return new AirPollutionMonitoringStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AirPollutionMonitoringBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AirPollutionMonitoringBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AirPollutionMonitoringFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AirPollutionMonitoringFutureStub(channel);
  }

  /**
   * <pre>
   * Service provided by the monitoring server.
   * </pre>
   */
  public static abstract class AirPollutionMonitoringImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * unary
     * </pre>
     */
    public void roomAirQuality(grpc.examples.SmartAir.AirQualityRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply> responseObserver) {
      asyncUnimplementedUnaryCall(getRoomAirQualityMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sends a request for air quality levels for all rooms, reply is a stream of air quality levels for all rooms
     * </pre>
     */
    public void allRoomAirQuality(grpc.examples.SmartAir.AllAirQualityRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply> responseObserver) {
      asyncUnimplementedUnaryCall(getAllRoomAirQualityMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sends a request for air quality levels for certain rooms, reply is a stream of air quality levels for the rooms requested
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityRequest> roomsAirQuality(
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getRoomsAirQualityMethod(), responseObserver);
    }

    /**
     * <pre>
     *Sends a request for the average air quality of certain rooms
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityRequest> aveRoomAirQuality(
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AveAirQualityReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getAveRoomAirQualityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRoomAirQualityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.SmartAir.AirQualityRequest,
                grpc.examples.SmartAir.AirQualityReply>(
                  this, METHODID_ROOM_AIR_QUALITY)))
          .addMethod(
            getAllRoomAirQualityMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.examples.SmartAir.AllAirQualityRequest,
                grpc.examples.SmartAir.AirQualityReply>(
                  this, METHODID_ALL_ROOM_AIR_QUALITY)))
          .addMethod(
            getRoomsAirQualityMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.examples.SmartAir.AirQualityRequest,
                grpc.examples.SmartAir.AirQualityReply>(
                  this, METHODID_ROOMS_AIR_QUALITY)))
          .addMethod(
            getAveRoomAirQualityMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.examples.SmartAir.AirQualityRequest,
                grpc.examples.SmartAir.AveAirQualityReply>(
                  this, METHODID_AVE_ROOM_AIR_QUALITY)))
          .build();
    }
  }

  /**
   * <pre>
   * Service provided by the monitoring server.
   * </pre>
   */
  public static final class AirPollutionMonitoringStub extends io.grpc.stub.AbstractStub<AirPollutionMonitoringStub> {
    private AirPollutionMonitoringStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirPollutionMonitoringStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirPollutionMonitoringStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirPollutionMonitoringStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary
     * </pre>
     */
    public void roomAirQuality(grpc.examples.SmartAir.AirQualityRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRoomAirQualityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sends a request for air quality levels for all rooms, reply is a stream of air quality levels for all rooms
     * </pre>
     */
    public void allRoomAirQuality(grpc.examples.SmartAir.AllAirQualityRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getAllRoomAirQualityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sends a request for air quality levels for certain rooms, reply is a stream of air quality levels for the rooms requested
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityRequest> roomsAirQuality(
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getRoomsAirQualityMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *Sends a request for the average air quality of certain rooms
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityRequest> aveRoomAirQuality(
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AveAirQualityReply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getAveRoomAirQualityMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * Service provided by the monitoring server.
   * </pre>
   */
  public static final class AirPollutionMonitoringBlockingStub extends io.grpc.stub.AbstractStub<AirPollutionMonitoringBlockingStub> {
    private AirPollutionMonitoringBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirPollutionMonitoringBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirPollutionMonitoringBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirPollutionMonitoringBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary
     * </pre>
     */
    public grpc.examples.SmartAir.AirQualityReply roomAirQuality(grpc.examples.SmartAir.AirQualityRequest request) {
      return blockingUnaryCall(
          getChannel(), getRoomAirQualityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sends a request for air quality levels for all rooms, reply is a stream of air quality levels for all rooms
     * </pre>
     */
    public java.util.Iterator<grpc.examples.SmartAir.AirQualityReply> allRoomAirQuality(
        grpc.examples.SmartAir.AllAirQualityRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getAllRoomAirQualityMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service provided by the monitoring server.
   * </pre>
   */
  public static final class AirPollutionMonitoringFutureStub extends io.grpc.stub.AbstractStub<AirPollutionMonitoringFutureStub> {
    private AirPollutionMonitoringFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirPollutionMonitoringFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AirPollutionMonitoringFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirPollutionMonitoringFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.SmartAir.AirQualityReply> roomAirQuality(
        grpc.examples.SmartAir.AirQualityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRoomAirQualityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ROOM_AIR_QUALITY = 0;
  private static final int METHODID_ALL_ROOM_AIR_QUALITY = 1;
  private static final int METHODID_ROOMS_AIR_QUALITY = 2;
  private static final int METHODID_AVE_ROOM_AIR_QUALITY = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AirPollutionMonitoringImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AirPollutionMonitoringImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ROOM_AIR_QUALITY:
          serviceImpl.roomAirQuality((grpc.examples.SmartAir.AirQualityRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply>) responseObserver);
          break;
        case METHODID_ALL_ROOM_AIR_QUALITY:
          serviceImpl.allRoomAirQuality((grpc.examples.SmartAir.AllAirQualityRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply>) responseObserver);
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
        case METHODID_ROOMS_AIR_QUALITY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.roomsAirQuality(
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AirQualityReply>) responseObserver);
        case METHODID_AVE_ROOM_AIR_QUALITY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.aveRoomAirQuality(
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.AveAirQualityReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AirPollutionMonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AirPollutionMonitoringBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.examples.SmartAir.SmartAirServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AirPollutionMonitoring");
    }
  }

  private static final class AirPollutionMonitoringFileDescriptorSupplier
      extends AirPollutionMonitoringBaseDescriptorSupplier {
    AirPollutionMonitoringFileDescriptorSupplier() {}
  }

  private static final class AirPollutionMonitoringMethodDescriptorSupplier
      extends AirPollutionMonitoringBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AirPollutionMonitoringMethodDescriptorSupplier(String methodName) {
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
      synchronized (AirPollutionMonitoringGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AirPollutionMonitoringFileDescriptorSupplier())
              .addMethod(getRoomAirQualityMethod())
              .addMethod(getAllRoomAirQualityMethod())
              .addMethod(getRoomsAirQualityMethod())
              .addMethod(getAveRoomAirQualityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
