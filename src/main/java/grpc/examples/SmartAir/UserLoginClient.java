package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class UserLoginClient {

    // instantiate the stub class
    public static UserLoginServiceGrpc.UserLoginServiceBlockingStub blockingStub;

    public static void createChannel(){
        // Creating channel for connection
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50555).usePlaintext().build();
        blockingStub = UserLoginServiceGrpc.newBlockingStub(channel);
    }

    // InterruptedException needed for channel.awaitTermination method
    public static void main(String[] args) {

        createChannel();
        //Create Request messages for use within the main method
        //UserLoginRequest loginRequest = UserLoginRequest.newBuilder().setName("Colin").setPass("DistSystem").build();
        //UserLogoutRequest logoutRequest = UserLogoutRequest.newBuilder().setName("Colin").build();

        //Call the login RPC from within main
        //UserLoginResponse response = blockingStub.userLogin(loginRequest);
        //System.out.println("Response from Server: " + response);

        //Call the logout RPC from within main
        //LogoutResponse responseOut = blockingStub.logout(logoutRequest);
        //System.out.println("Response from Server: " + responseOut);

        //Call the login RPC from userLogin() method
        userLogin("Colin", "DistSystem", blockingStub);

        //Call the logout RPC from userLogout() method
        //userLogout();

        //shut(channel);


    }

    public static void shut(ManagedChannel channel) throws InterruptedException {
        // no new tasks will be accepted, starts orderly shutdown
        channel.shutdown();
        // waits for all shutdown tasks to complete or the timeout, whichever is first
        channel.awaitTermination(2, TimeUnit.SECONDS);
    }
    //Login method that return the response
    public static UserLoginResponse userLogin(String user, String pass, UserLoginServiceGrpc.UserLoginServiceBlockingStub stub) {
        System.out.println("Inside Login in Client: ");

        UserLoginRequest loginRequest = UserLoginRequest.newBuilder().setName(user).setPass(pass).build();

        UserLoginResponse response = stub.userLogin(loginRequest);

        System.out.println("Response from Server: " + response);

        return response;
    }

    //Logout
    public static UserLogoutResponse userLogout() {
        System.out.println("Inside Logout in Client: ");

        UserLogoutRequest logoutRequest = UserLogoutRequest.newBuilder().setName("Colin").build();

        UserLogoutResponse response = blockingStub.userLogout(logoutRequest);

        System.out.println("Response from Server: " + response);

        return response;
    }
}

