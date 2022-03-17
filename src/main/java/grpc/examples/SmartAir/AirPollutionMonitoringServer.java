package grpc.examples.SmartAir;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Logger;

public class AirPollutionMonitoringServer extends AirPollutionMonitoringGrpc.AirPollutionMonitoringImplBase{

    private static final Logger logger = Logger.getLogger(AirPollutionMonitoringServer.class.getName());

    // set array of room air quality measures
    int[] arr = {74, 70, 68, 72};

    public static void main(String[] args) throws IOException {
        // Print to screen to see the server has started
        System.out.println("The gRPC air pollution monitoring server has started");
        AirPollutionMonitoringServer monitoringServer = new AirPollutionMonitoringServer();

        // Local tcp port that the service will listen for connections on
        int port = 50556;

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

        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Override userLogin method from AirPollutionMonitoringGrpc.AirPollutionMonitoringImplBase
    @Override
    public void roomAirQuality(AirQualityRequest request, StreamObserver<AirQualityReply> responseObserver) {
        System.out.println("Attempting to request room air quality....");
        String room = request.getRoom();

        // Create response object
        AirQualityReply.Builder response = AirQualityReply.newBuilder();
        System.out.println("Requesting air quality for room: " + room);

        if(room.equals("1")) {
            // if room 1 requested
            System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[0]));
        }
        else if(room.equals("2")) {
            // if room 2 requested
            System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[1]));
        }
        else if(room.equals("3")) {
            // if room 3 requested
            System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[2]));
        }
        else if(room.equals("4")) {
            // if room 4 requested
            System.out.println("The air quality of room " + room + " is: " + response.setQuality(arr[3]));
        }
        else{
            // no valid room requested
            response.setQuality(0);
            System.out.println("Please select a valid room");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void allRoomAirQuality(AllAirQualityRequest request, StreamObserver<AirQualityReply> responseObserver) {
        System.out.println("Received request for air quality of all rooms");
        String allRoom = request.getAllRoom();

        // manual set number of rooms ( in future we could have an array or dict of room:air_quality values to loop through
        int num_rooms = 4;
        // loop through rooms

        for (int room = 1; room < num_rooms + 1; room++) {
            System.out.println("The air quality of room " + room + " is: " + AirQualityReply.newBuilder().setQuality(arr[room-1]));
            // Create response object
            responseObserver.onNext(AirQualityReply.newBuilder().setQuality(arr[room-1]).build());
        }

        responseObserver.onCompleted();
    }

}

