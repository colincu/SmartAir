package grpc.examples.SmartAir;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

public class UserLoginServer extends UserLoginServiceGrpc.UserLoginServiceImplBase {

    public static void main(String[] args) throws IOException {
        // Print to screen to see the server has started
        System.out.println("The gRPC login server has started");
        UserLoginServer loginserver = new UserLoginServer();

        //create instance of properties that loads in what we have defined in SmartAirLogin.properties
        Properties properties = loginserver.getProperties();

        //register our server based on the properties defined
        loginserver.registerService(properties);

        // Local tcp port that the service will listen for connections on
        int port = Integer.valueOf(properties.getProperty("service_port"));

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

    private  void registerService(Properties prop) {

        try {
            // Create the JmDNS instance using local host IP address
            JmDNS jmdnsLoginServer = JmDNS.create(InetAddress.getLocalHost());
            InetAddress jmsAddr = jmdnsLoginServer.getInterface();

            System.out.println("Registration jmdns instance created: " + jmdnsLoginServer.getName());
            System.out.println("Registration jmdns instance ip address: " + jmsAddr);

            // these variables are defined in the *.properties file under resources
            String service_type = prop.getProperty("service_type") ;
            String service_name = prop.getProperty("service_name")  ;
            int service_port = Integer.valueOf( prop.getProperty("service_port") );
            String service_description = prop.getProperty("service_description")  ;

            // Register the login service
            ServiceInfo loginServiceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description);
            jmdnsLoginServer.registerService(loginServiceInfo);

            // Code to validate the service is being registered
            Thread.sleep(25000);
            ServiceInfo info = jmdnsLoginServer.getServiceInfo(service_type, service_name, 5000);
            System.out.println("Details of the service that was registered " + info);

            // Wait a bit
            Thread.sleep(1000);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // catch block
            e.printStackTrace();
        }

    }

    private Properties getProperties() {

        Properties prop = null;

        try (InputStream input = new FileInputStream("src/main/resources/SmartAirLogin.properties")) {

            prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println("Login Service properties ...");
            System.out.println("\t service_type: " + prop.getProperty("service_type"));
            System.out.println("\t service_name: " +prop.getProperty("service_name"));
            System.out.println("\t service_description: " +prop.getProperty("service_description"));
            System.out.println("\t service_port: " +prop.getProperty("service_port"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop;
    }

}


