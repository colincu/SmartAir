package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
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
        AllRoomAirQuality();



    }

    //Unary rpc
    public static void RoomAirQuality(){
        // set room in request to room number 2
        AirQualityRequest request = AirQualityRequest.newBuilder().setRoom("2").build();

        // set reply to be air quality measure for room 2
        AirQualityReply reply = blockingStub.roomAirQuality(request);

        System.out.println("The air quality is at level: " + reply.getQuality());

    }

    public static void AllRoomAirQuality(){
        AllAirQualityRequest request = AllAirQualityRequest
                .newBuilder()
                .setAllRoom("all")
                .build();
        Iterator<AirQualityReply> replies;
        try {
            replies = blockingStub.allRoomAirQuality(request);
            for(int i =1; replies.hasNext(); i++){
                AirQualityReply reply = replies.next();
                System.out.println("The air quality is at level: " + reply.getQuality());
            }
        } catch (StatusRuntimeException e){
            logger.info("RPC failed:");
        }
    }



}
