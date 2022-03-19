package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class UserLoginClient {

    // instantiate the stub class
    private static UserLoginServiceGrpc.UserLoginServiceBlockingStub blockingStub;

    // InterruptedException needed for channel.awaitTermination method
    public static void main(String[] args) throws InterruptedException {
        // Creating channel for connection
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50555).usePlaintext().build();

        blockingStub = UserLoginServiceGrpc.newBlockingStub(channel);

        //Create Request messages for use within the main method
        UserLoginRequest loginRequest = UserLoginRequest.newBuilder().setName("Colin").setPass("DistSystem").build();
        UserLogoutRequest logoutRequest = UserLogoutRequest.newBuilder().setName("Colin").build();

        //Call the login RPC from within main
        UserLoginResponse response = blockingStub.userLogin(loginRequest);
        System.out.println("Response from Server: " + response);

        //Call the logout RPC from within main
        //LogoutResponse responseOut = blockingStub.logout(logoutRequest);
        //System.out.println("Response from Server: " + responseOut);

        //Call the login RPC from login() method
        //login();

        //Call the logout RPC from logout() method
        //logout();

        // no new tasks will be accepted, starts orderly shutdown
        channel.shutdown();
        // waits for all shutdown tasks to complete or the timeout, whichever is first
        channel.awaitTermination(2, TimeUnit.SECONDS);
    }

    //Login
    public static void userLogin() {
        System.out.println("Inside Login in Client: ");

        UserLoginRequest loginRequest = UserLoginRequest.newBuilder().setName("Colin").setPass("DistSystem").build();

        UserLoginResponse response = blockingStub.userLogin(loginRequest);

        System.out.println("Response from Server: " + response);
    }

    //Logout
    public static void userLogout() {
        System.out.println("Inside Logout in Client: ");

        UserLogoutRequest logoutRequest = UserLogoutRequest.newBuilder().setName("Colin").build();

        UserLogoutResponse response = blockingStub.userLogout(logoutRequest);

        System.out.println("Response from Server: " + response);
    }
}

