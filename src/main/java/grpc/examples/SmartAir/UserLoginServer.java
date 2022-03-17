package grpc.examples.SmartAir;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;

public class UserLoginServer extends UserLoginServiceGrpc.UserLoginServiceImplBase {

    public static void main(String[] args) throws IOException {
        // Print to screen to see the server has started
        System.out.println("The gRPC login server has started");
        UserLoginServer loginserver = new UserLoginServer();

        // Local tcp port that the service will listen for connections on
        int port = 50555;

        // Try/Except block for exception handling if service fails to start listening
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(loginserver)
                    .build()
                    .start();

            // Print to screen the port the server is listening on
            System.out.println("Server listening for connections on port:" + server.getPort());
            server.awaitTermination();

        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Override userLogin method from UserLoginServiceGrpc.UserLoginServiceImplBase
    @Override
    public void userLogin(UserLoginRequest request, StreamObserver<UserLoginResponse> responseObserver) {
        System.out.println("User attempting to login....");
        String username = request.getName();
        String password = request.getPass();

        UserLoginResponse.Builder response = UserLoginResponse.newBuilder();
        System.out.println("username = " + username + ", password = " + password);

        if(username.equals("Colin") && password.equals("DistSystem")) {
            // login success response returned
            response.setResponseCode(1).setResponseMessage(username + " login successful!");
        }
        else {
            // login failed response returned
            response.setResponseCode(99).setResponseMessage(username + " login failed!");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }



@Override
    public void userLogout(UserLogoutRequest request, StreamObserver<UserLogoutResponse> responseObserver) {
        System.out.println("User attempting to logout....");
        String username = request.getName();

        UserLogoutResponse.Builder response = UserLogoutResponse.newBuilder();
        System.out.println( username + " logging out.");

        if(username.equals("Colin")) {
            // logout successful response returned
            response.setResponseCode(1).setResponseMessage(username + " successfully logged out.");
        }
        else {
            // logout failed response returned
            response.setResponseCode(99).setResponseMessage(username + " failed to logout.");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}


