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
import java.util.logging.Logger;

public class AirPurificationSystemServer extends AirPurificationSystemGrpc.AirPurificationSystemImplBase {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirPurificationSystemServer.class.getName());

    public static void main(String[] args) throws IOException {


        // log that the server has started
        logger.info("The air purification system grpc server is running...");

        //create instance of AirPurificationSystemServer
        AirPurificationSystemServer systemServer = new AirPurificationSystemServer();

        //register service using properties
        Properties properties = systemServer.getProperties();
        systemServer.registerService(properties);

        // Local tcp port that the service will listen for connections on
        int port = Integer.parseInt(properties.getProperty("service_port"));

        // Try/Except block for exception handling if service fails to start listening
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(systemServer)
                    .build()
                    .start();

            // log the port the server is listening on
            logger.info("Server listening for connections on port:" + server.getPort());
            server.awaitTermination();

        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Override changeSpeed method from AirPurificationSystemGrpc.AirPurificationSystemImplBase
    @Override
    public void changeSpeed(ChangeSpeedRequest request, StreamObserver<ChangeSpeedReply> responseObserver){
        logger.info("Changing operational status of air purification system..");
        String speed = request.getSpeed();

        //create reply object
        ChangeSpeedReply.Builder reply = ChangeSpeedReply.newBuilder();

        switch (speed) {
            case "off":
            case "low":
            case "med":
            case "high":
                // user request to update speed of the air purification system
                // set initial state of purification system to off
                // real world would see this state pulled from the actual hardware and updates sent to hardware
                reply.setResponseCode(1).setSpeed(speed);
                logger.info("The air purification system has been set to: " + speed);
                break;
            default:
                // set response code to 99 if invalid input - error handling can be done based on this response code
                reply.setResponseCode(99);
                logger.info("Please provide a valid input");
        }
        responseObserver.onNext(reply.build());
        // finished writing responses
        responseObserver.onCompleted();
    }

    private  void registerService(Properties prop) {

        try {
            // Create the JmDNS instance using local host IP address
            JmDNS jmdnsSystemServer = JmDNS.create(InetAddress.getLocalHost());
            InetAddress jmsAddr = jmdnsSystemServer.getInterface();

            System.out.println("Registration jmdns instance created: " + jmdnsSystemServer.getName());
            System.out.println("Registration jmdns instance ip address: " + jmsAddr);

            // these variables are defined in the *.properties file under resources
            String service_type = prop.getProperty("service_type") ;
            String service_name = prop.getProperty("service_name")  ;
            int service_port = Integer.parseInt( prop.getProperty("service_port") );
            String service_description = prop.getProperty("service_description")  ;

            // Register the login service
            ServiceInfo systemServiceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description);
            jmdnsSystemServer.registerService(systemServiceInfo);

            // Code to validate the service is being registered
            Thread.sleep(25000);
            ServiceInfo info = jmdnsSystemServer.getServiceInfo(service_type, service_name, 5000);
            System.out.println("Details of the service that was registered " + info);



            // Wait a bit
            Thread.sleep(1000);

            // Unregister all services
            //jmdns.unregisterAllServices();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // catch block
            e.printStackTrace();
        }

    }

    private Properties getProperties() {

        Properties prop = null;

        try (InputStream input = new FileInputStream("src/main/resources/SmartAirSystem.properties")) {

            prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println("System Service properties ...");
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
