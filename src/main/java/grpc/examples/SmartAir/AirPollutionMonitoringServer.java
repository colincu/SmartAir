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

public class AirPollutionMonitoringServer extends AirPollutionMonitoringGrpc.AirPollutionMonitoringImplBase {

    private static final Logger logger = Logger.getLogger(AirPollutionMonitoringServer.class.getName());

    // set array of room air quality measures
    int[] arr = {74, 70, 68, 72};

    public static void main(String[] args) throws IOException {
        // Print to screen to see the server has started
        System.out.println("The gRPC air pollution monitoring server has started");
        AirPollutionMonitoringServer monitoringServer = new AirPollutionMonitoringServer();
        //service registration with jmdns
        Properties prop = monitoringServer.getProperties();
        monitoringServer.registerService(prop);

        // Local tcp port that the service will listen for connections on
        int port = Integer.valueOf(prop.getProperty("service_port"));

        // Try/Except block for exception handling if service fails to start listening
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(monitoringServer)
                    .build()
                    .start();

            // Print to screen the port the server is listening on
            System.out.println("Server listening for connections on port:" + server.getPort());
            logger.info("Server listening for connections on port:" + server.getPort());

            server.awaitTermination();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Override roomAirQuality method from AirPollutionMonitoringGrpc.AirPollutionMonitoringImplBase
    // this grpc method is Unary
    @Override
    public void roomAirQuality(AirQualityRequest request, StreamObserver<AirQualityReply> responseObserver) {
        System.out.println("Attempting to request room air quality....");
        String room = request.getRoom();

        // Create response object
        AirQualityReply.Builder response = AirQualityReply.newBuilder();
        System.out.println("Requesting air quality for room: " + room);

        switch (room) {
            case "1":
                // if room 1 requested
                System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[0]));
                break;
            case "2":
                // if room 2 requested
                System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[1]));
                break;
            case "3":
                // if room 3 requested
                System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[2]));
                break;
            case "4":
                // if room 4 requested
                System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[3]));
                break;
            default:
                // no valid room requested
                response.setQuality(0);
                System.out.println("Please select a valid room");
                break;
        }

        responseObserver.onNext(response.build());
        // finished writing responses
        responseObserver.onCompleted();
    }

    // this grpc method is server streaming
    @Override
    public void allRoomAirQuality(AllAirQualityRequest request, StreamObserver<AirQualityReply> responseObserver) {
        System.out.println("Received request for air quality of all rooms");

        // manual set number of rooms ( in future we could have an array or dict of room:air_quality values to loop through
        int num_rooms = 4;

        // loop through rooms and write in turn to the response observer
        for (int room = 1; room < num_rooms + 1; room++) {
            // print to terminal for us to validate during dev
            //System.out.println("The air quality of room " + room + " is: " + AirQualityReply.newBuilder().setQuality(arr[room-1]));
            // write to response observer
            //responseObserver.onNext(AirQualityReply.newBuilder().setQuality(arr[room-1]).build());
            AirQualityReply reply = AirQualityReply.newBuilder()
                    .setQuality(arr[room - 1])
                    .build();
            responseObserver.onNext(reply);
        }

        // finished writing responses
        responseObserver.onCompleted();
    }

    // this grpc method is client and server streaming - bidirectional
    @Override
    public StreamObserver<AirQualityRequest> roomsAirQuality(StreamObserver<AirQualityReply> responseObserver) {
        return new StreamObserver<AirQualityRequest>() {

            @Override
            public void onNext(AirQualityRequest request) {
                String room = request.getRoom();

                // Create response object
                AirQualityReply.Builder response = AirQualityReply.newBuilder();
                System.out.println("Requesting air quality for room: " + room);

                switch (room) {
                    case "1":
                        // if room 1 requested
                        System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[0]));
                        break;
                    case "2":
                        // if room 2 requested
                        System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[1]));
                        break;
                    case "3":
                        // if room 3 requested
                        System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[2]));
                        break;
                    case "4":
                        // if room 4 requested
                        System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[3]));
                        break;
                    default:
                        // no valid room requested
                        response.setQuality(0);
                        System.out.println("Please select a valid room");
                        break;
                }

                responseObserver.onNext(response.build());
            }

            @Override
            public void onError(Throwable t) {
                //
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();

            }

        };
    }

    // this grpc method is client side streaming
    @Override
    public StreamObserver<AirQualityRequest> aveRoomAirQuality(StreamObserver<AveAirQualityReply> responseObserver) {
        return new StreamObserver<AirQualityRequest>() {
            //declare variables to calculate average
            int count;
            int total;
            int ave;

            // method to execute each time a stream is submitted
            @Override
            public void onNext(AirQualityRequest request) {
                // increment to count each time this is executed, we can calculate the average
                count++;
                String room = request.getRoom();
                int quality;
                // set quality based on the room number selected in request message
                switch (room) {
                    case "1":
                        quality = arr[0];
                        break;
                    case "2":
                        quality = arr[1];
                        break;
                    case "3":
                        quality = arr[2];
                        break;
                    case "4":
                        quality = arr[3];
                        break;
                    default:
                        quality = 0;
                        break;
                }
                // keep track of total so we can calculate the average
                total += quality;
            }

            @Override
            public void onError (Throwable t){
                //
            }

            //return the average once client streaming is complete
            @Override
            public void onCompleted () {
                ave = total/count;
                AveAirQualityReply res = AveAirQualityReply.newBuilder().setQuality(ave).build();
                System.out.println("The average air quality for the rooms provided is: " + ave);
                responseObserver.onNext(res);
                responseObserver.onCompleted();
            }
        };
    }

    private  void registerService(Properties prop) {

        try {
            // Create the JmDNS instance using local host IP address
            JmDNS jmdnsMonitServer = JmDNS.create(InetAddress.getLocalHost());
            InetAddress jmsAddr = jmdnsMonitServer.getInterface();

            System.out.println("Registration jmdns instance created: " + jmdnsMonitServer.getName());
            System.out.println("Registration jmdns instance ip address: " + jmsAddr);

            // these variables are defined in the *.properties file under resources
            String service_type = prop.getProperty("service_type") ;
            String service_name = prop.getProperty("service_name")  ;
            int service_port = Integer.valueOf( prop.getProperty("service_port") );
            String service_description = prop.getProperty("service_description")  ;

            // Register the login service
            ServiceInfo loginMonitInfo = ServiceInfo.create(service_type, service_name, service_port, service_description);
            jmdnsMonitServer.registerService(loginMonitInfo);

            // Code to validate the service is being registered
            Thread.sleep(25000);
            ServiceInfo info = jmdnsMonitServer.getServiceInfo(service_type, service_name, 5000);
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

        try (InputStream input = new FileInputStream("src/main/resources/SmartAirMonitoring.properties")) {

            prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println("Login Service properies ...");
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

