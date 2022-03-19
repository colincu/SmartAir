package grpc.examples.SmartAir;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

public class AirPurificationSystemServer extends AirPurificationSystemGrpc.AirPurificationSystemImplBase {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirPurificationSystemServer.class.getName());

    // set initial state of purification system to off
    // real world would see this state pulled from the actual hardware
    private String systemState = "off";

    public static void main(String[] args) throws IOException {


        // log that the server has started
        logger.info("The air purification system grpc server is running...");

        //
        AirPurificationSystemServer systemServer = new AirPurificationSystemServer();

        // Local tcp port that the service will listen for connections on
        int port = 50557;

        // Try/Except block for exception handling if service fails to start listening
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(systemServer)
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
                systemState = speed;
                reply.setResponseCode(1).setSpeed(speed);
                logger.info("The air purification system has been set to: " + systemState);
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
}
