package grpc.examples.SmartAir;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

public class AirPollutionMonitoringClient {
    private static Logger logger = Logger.getLogger(AirPollutionMonitoringClient.class.getName());

    // instantiate the stub classes
    private static AirPollutionMonitoringGrpc.AirPollutionMonitoringBlockingStub blockingStub;
    private static AirPollutionMonitoringGrpc.AirPollutionMonitoringStub nonBlockingStub;

    public static void main(String[] args) throws Exception {
        // build the channel for communications
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50556)
                .usePlaintext()
                .build();
        //generate stubs
        blockingStub = AirPollutionMonitoringGrpc.newBlockingStub(channel);
        nonBlockingStub = AirPollutionMonitoringGrpc.newStub(channel);

        //room air quality
        // RoomAirQuality();

        //all rooms air quality
        //AllRoomAirQuality();

        //selected rooms air quality
        //RoomsAirQuality();

        //get ave room air quality
        AveRoomAirQuality();

        channel.shutdown();

    }

    //Unary rpc
    public static void RoomAirQuality(){
        // set room in request to room number 2
        AirQualityRequest request = AirQualityRequest.newBuilder().setRoom("2").build();

        // set reply to be air quality measure for room 2
        AirQualityReply reply = blockingStub.roomAirQuality(request);

        System.out.println("The air quality is at level: " + reply.getQuality());

    }

    // server side streaming rpc
    public static void AllRoomAirQuality(){
        AllAirQualityRequest request = AllAirQualityRequest
                .newBuilder()
                .setAllRoom("all")
                .build();
        Iterator<AirQualityReply> replies;
        try {
            replies = blockingStub.allRoomAirQuality(request);
            // loop through replies
            while (replies.hasNext()) {
                AirQualityReply reply = replies.next();
                System.out.println("The air quality is at level: " + reply.getQuality());
            }
        } catch (StatusRuntimeException e){
            logger.info("RPC failed:");
        }
    }

    // bidirectional streaming rpc
    public static void RoomsAirQuality() {
        StreamObserver<AirQualityReply> responseObserver = new StreamObserver<AirQualityReply>() {
            @Override
            public void onNext(AirQualityReply value) {
                System.out.println("Air quality for selected room is " + value.getQuality());
            }

            @Override
            public void onError(Throwable t) {
                //
            }

            @Override
            public void onCompleted() {
                logger.info("Server has finished processing streams.");
            }
        };

        StreamObserver<AirQualityRequest> requestObserver =
                nonBlockingStub.roomsAirQuality(responseObserver);
        try {
            // Sleep for some time before sending the next one.
            Thread.sleep(new Random().nextInt(900) + 400);
            requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("2").build());
            // Sleep for some time before sending the next one.
            Thread.sleep(new Random().nextInt(900) + 400);
            requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("1").build());
            // Sleep for a bit before sending the next one.
            Thread.sleep(new Random().nextInt(900) + 400);
            requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("4").build());
            // Sleep for a bit before sending the next one.
            Thread.sleep(new Random().nextInt(900) + 400);

            logger.info("Sending messages...");

            // end of requests
            requestObserver.onCompleted();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //client side streaming rpc
    public static void AveRoomAirQuality(){
        StreamObserver<AveAirQualityReply> responseObserver = new StreamObserver<AveAirQualityReply>() {
            @Override
            public void onNext(AveAirQualityReply value) {
                System.out.println("The average air quality of the rooms you provided is: " + value.getQuality());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                logger.info("Completed average room air quality calculations.");
            }
        };

        StreamObserver<AirQualityRequest> requestObserver
                = nonBlockingStub.aveRoomAirQuality(responseObserver);
        try {
            // Sleep for some time before sending the next one.
            Thread.sleep(new Random().nextInt(1000) + 2000);
            requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("2").build());
            // Sleep for some time before sending the next one.
            Thread.sleep(new Random().nextInt(1000) + 2000);
            requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("3").build());
            // Sleep for some time before sending the next one.
            Thread.sleep(new Random().nextInt(1000) + 2000);
            requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("4").build());
            // Sleep for some time before sending the next one.
            Thread.sleep(new Random().nextInt(1000) + 2000);

            logger.info("Sending messages...");

            // end of requests
            requestObserver.onCompleted();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
