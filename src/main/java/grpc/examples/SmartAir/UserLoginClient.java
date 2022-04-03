package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class UserLoginClient {
    //service discovery
    private static class SampleListener implements ServiceListener {
        @Override
        public void serviceAdded(ServiceEvent event) {
            System.out.println("Service added: " + event.getInfo());
        }

        @Override
        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed: " + event.getInfo());
        }

        @Override
        public void serviceResolved(ServiceEvent event) {
            System.out.println("Service resolved: " + event.getInfo());
            ServiceInfo myLoginServiceInfo;
            myLoginServiceInfo = event.getInfo();
            String service_host = myLoginServiceInfo.getHostAddresses()[0];
            int service_port = myLoginServiceInfo.getPort();
        }
    }


    // instantiate the stub class
    public static UserLoginServiceGrpc.UserLoginServiceBlockingStub blockingStub;

    public static void createChannel() throws IOException, InterruptedException {
        //create the jmDNS instance
        JmDNS jmdns = JmDNS.create();
        //add listener
        jmdns.addServiceListener("_userLogin._tcp.local.", new SampleListener());
        // wait some time
        Thread.sleep(30000);

        // Creating channel for connection
        String service_host = "localhost";
        int service_port = 50555;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(service_host, service_port).usePlaintext().build();
        blockingStub = UserLoginServiceGrpc.newBlockingStub(channel);
    }

    // InterruptedException needed for channel.awaitTermination method
    public static void main(String[] args) throws IOException, InterruptedException {

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
    public static UserLogoutResponse userLogout(String user) {
        System.out.println("Inside Logout in Client: ");

        UserLogoutRequest logoutRequest = UserLogoutRequest.newBuilder().setName(user).build();

        UserLogoutResponse response = blockingStub.userLogout(logoutRequest);

        System.out.println("Response from Server: " + response);

        return response;
    }
}

