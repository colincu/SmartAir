package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class AirPurificationSystemClient {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirPurificationSystemClient.class.getName());

    // instantiate the stub
    private static AirPurificationSystemGrpc.AirPurificationSystemBlockingStub blockingStub;

    public static void main(String[] args){
        // create the channel for the connectivity
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50557).usePlaintext().build();

        blockingStub = AirPurificationSystemGrpc.newBlockingStub(channel);

        // change the speed to med
        ChangeSpeed();
    }

    public static void ChangeSpeed(){
        //change speed to med
        ChangeSpeedRequest request = ChangeSpeedRequest.newBuilder().setSpeed("med").build();

        //set reply
        ChangeSpeedReply reply = blockingStub.changeSpeed(request);
        logger.info("The air purification system has been set to : " + reply.getSpeed());
    }
}
