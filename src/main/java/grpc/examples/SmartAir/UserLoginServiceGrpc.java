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
 * Login service provided by server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: AirPolutionSystemLogin.proto")
public final class UserLoginServiceGrpc {

  private UserLoginServiceGrpc() {}

  public static final String SERVICE_NAME = "SmartAir.UserLoginService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.UserLoginRequest,
      grpc.examples.SmartAir.UserLoginResponse> getUserLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserLogin",
      requestType = grpc.examples.SmartAir.UserLoginRequest.class,
      responseType = grpc.examples.SmartAir.UserLoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.UserLoginRequest,
      grpc.examples.SmartAir.UserLoginResponse> getUserLoginMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.UserLoginRequest, grpc.examples.SmartAir.UserLoginResponse> getUserLoginMethod;
    if ((getUserLoginMethod = UserLoginServiceGrpc.getUserLoginMethod) == null) {
      synchronized (UserLoginServiceGrpc.class) {
        if ((getUserLoginMethod = UserLoginServiceGrpc.getUserLoginMethod) == null) {
          UserLoginServiceGrpc.getUserLoginMethod = getUserLoginMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.UserLoginRequest, grpc.examples.SmartAir.UserLoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.UserLoginService", "UserLogin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.UserLoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.UserLoginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserLoginServiceMethodDescriptorSupplier("UserLogin"))
                  .build();
          }
        }
     }
     return getUserLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.SmartAir.UserLogoutRequest,
      grpc.examples.SmartAir.UserLogoutResponse> getUserLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UserLogout",
      requestType = grpc.examples.SmartAir.UserLogoutRequest.class,
      responseType = grpc.examples.SmartAir.UserLogoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.SmartAir.UserLogoutRequest,
      grpc.examples.SmartAir.UserLogoutResponse> getUserLogoutMethod() {
    io.grpc.MethodDescriptor<grpc.examples.SmartAir.UserLogoutRequest, grpc.examples.SmartAir.UserLogoutResponse> getUserLogoutMethod;
    if ((getUserLogoutMethod = UserLoginServiceGrpc.getUserLogoutMethod) == null) {
      synchronized (UserLoginServiceGrpc.class) {
        if ((getUserLogoutMethod = UserLoginServiceGrpc.getUserLogoutMethod) == null) {
          UserLoginServiceGrpc.getUserLogoutMethod = getUserLogoutMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.SmartAir.UserLogoutRequest, grpc.examples.SmartAir.UserLogoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SmartAir.UserLoginService", "UserLogout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.UserLogoutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.SmartAir.UserLogoutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserLoginServiceMethodDescriptorSupplier("UserLogout"))
                  .build();
          }
        }
     }
     return getUserLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserLoginServiceStub newStub(io.grpc.Channel channel) {
    return new UserLoginServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserLoginServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserLoginServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserLoginServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserLoginServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Login service provided by server.
   * </pre>
   */
  public static abstract class UserLoginServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a request for a user to login providing username and password, reply is login success/failure
     * </pre>
     */
    public void userLogin(grpc.examples.SmartAir.UserLoginRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.UserLoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserLoginMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sends a request for a user to logout providing just username, reply is logout success/failure
     * </pre>
     */
    public void userLogout(grpc.examples.SmartAir.UserLogoutRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.UserLogoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUserLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUserLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.SmartAir.UserLoginRequest,
                grpc.examples.SmartAir.UserLoginResponse>(
                  this, METHODID_USER_LOGIN)))
          .addMethod(
            getUserLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.SmartAir.UserLogoutRequest,
                grpc.examples.SmartAir.UserLogoutResponse>(
                  this, METHODID_USER_LOGOUT)))
          .build();
    }
  }

  /**
   * <pre>
   * Login service provided by server.
   * </pre>
   */
  public static final class UserLoginServiceStub extends io.grpc.stub.AbstractStub<UserLoginServiceStub> {
    private UserLoginServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserLoginServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserLoginServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserLoginServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a request for a user to login providing username and password, reply is login success/failure
     * </pre>
     */
    public void userLogin(grpc.examples.SmartAir.UserLoginRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.UserLoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sends a request for a user to logout providing just username, reply is logout success/failure
     * </pre>
     */
    public void userLogout(grpc.examples.SmartAir.UserLogoutRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.SmartAir.UserLogoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUserLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Login service provided by server.
   * </pre>
   */
  public static final class UserLoginServiceBlockingStub extends io.grpc.stub.AbstractStub<UserLoginServiceBlockingStub> {
    private UserLoginServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserLoginServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserLoginServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserLoginServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a request for a user to login providing username and password, reply is login success/failure
     * </pre>
     */
    public grpc.examples.SmartAir.UserLoginResponse userLogin(grpc.examples.SmartAir.UserLoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserLoginMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sends a request for a user to logout providing just username, reply is logout success/failure
     * </pre>
     */
    public grpc.examples.SmartAir.UserLogoutResponse userLogout(grpc.examples.SmartAir.UserLogoutRequest request) {
      return blockingUnaryCall(
          getChannel(), getUserLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Login service provided by server.
   * </pre>
   */
  public static final class UserLoginServiceFutureStub extends io.grpc.stub.AbstractStub<UserLoginServiceFutureStub> {
    private UserLoginServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserLoginServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserLoginServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserLoginServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a request for a user to login providing username and password, reply is login success/failure
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.SmartAir.UserLoginResponse> userLogin(
        grpc.examples.SmartAir.UserLoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserLoginMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sends a request for a user to logout providing just username, reply is logout success/failure
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.SmartAir.UserLogoutResponse> userLogout(
        grpc.examples.SmartAir.UserLogoutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUserLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_USER_LOGIN = 0;
  private static final int METHODID_USER_LOGOUT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserLoginServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserLoginServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_USER_LOGIN:
          serviceImpl.userLogin((grpc.examples.SmartAir.UserLoginRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.UserLoginResponse>) responseObserver);
          break;
        case METHODID_USER_LOGOUT:
          serviceImpl.userLogout((grpc.examples.SmartAir.UserLogoutRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.SmartAir.UserLogoutResponse>) responseObserver);
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

  private static abstract class UserLoginServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserLoginServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.examples.SmartAir.SmartAirLoginImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserLoginService");
    }
  }

  private static final class UserLoginServiceFileDescriptorSupplier
      extends UserLoginServiceBaseDescriptorSupplier {
    UserLoginServiceFileDescriptorSupplier() {}
  }

  private static final class UserLoginServiceMethodDescriptorSupplier
      extends UserLoginServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserLoginServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserLoginServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserLoginServiceFileDescriptorSupplier())
              .addMethod(getUserLoginMethod())
              .addMethod(getUserLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
