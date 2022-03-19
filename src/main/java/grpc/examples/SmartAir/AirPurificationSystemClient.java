package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirPurificationSystemClient {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirPurificationSystemClient.class.getName());

    // instantiate the stub
    private static AirPurificationSystemGrpc.AirPurificationSystemBlockingStub blockingStub;

    // InterruptedException needed for channel.awaitTermination method
    public static void main(String[] args) throws InterruptedException {
        // create the channel for the connectivity
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50557).usePlaintext().build();

        blockingStub = AirPurificationSystemGrpc.newBlockingStub(channel);

        // change the speed to med
        ChangeSpeed();

        //rpc termination
        // no new tasks will be accepted, starts orderly shutdown
        channel.shutdown();
        // waits for all shutdown tasks to complete or the timeout, whichever is first
        channel.awaitTermination(2, TimeUnit.SECONDS);
    }

    public static void ChangeSpeed(){
        //change speed to med
        ChangeSpeedRequest request = ChangeSpeedRequest.newBuilder().setSpeed("med").build();

        //set reply
        ChangeSpeedReply reply;

        //error handling
        try {
            reply = blockingStub
                    // setting a deadline 3 seconds from now for this to complete
                    .withDeadlineAfter(3, TimeUnit.SECONDS)
                    .changeSpeed(request);
            logger.info("The air purification system has been set to : " + reply.getSpeed());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "Call to change speed failed: {0}", e.getStatus());
            return;
        }
    }
}
