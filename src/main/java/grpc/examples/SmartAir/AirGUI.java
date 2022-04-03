package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class AirGUI implements ActionListener {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirGUI.class.getName());
    // GUI elements for login screen
    private static JLabel loginHeader, failure, speedChangeSuccess, selectRoomAirQualitySuccess,
            selectAllRoomAirQualitySuccess1, selectAllRoomAirQualitySuccess2, selectAllRoomAirQualitySuccess3,
            selectAllRoomAirQualitySuccess4, selectRoomsAirQualitySuccess1, selectRoomsAirQualitySuccess2,
            selectRoomsAirQualitySuccess3, selectRoomsAirQualitySuccess4, selectAveRoomAirQualitySuccess;
    private static JTextField userText;
    private static JPasswordField passText;
    private static JButton loginButton, logoutButton, airPurificationSystemButton, airPollutionMonitoringButton,
            purificationChangeSpeed, buttonSelectRoomAirQuality, buttonSelectAllRoomAirQuality,
            buttonSelectRoomsAirQuality, buttonSelectAveRoomAirQuality, purificationBackButton,
            monitoringRoomAirQuality, monitoringAllRoomAirQuality, monitoringRoomsAirQuality,
            monitoringAveRoomAirQuality, monitoringBackButton, roomAirQualityBackButton,
            allRoomAirQualityBackButton, roomsAirQualityBackButton, aveRoomAirQualityBackButton;
    private static JComboBox changeSpeed, selectRoomAirQualityRoom, selectAllRoomAirQualityRoom;
    private static JCheckBox room1, room2, room3, room4;
    //needed to move these out of main method so we can modify them from outside main method
    private static JPanel panel;
    private static JFrame frame;
    //service
    private static ServiceInfo loginServiceInfo, monitoringServiceInfo, systemServiceInfo;
    private static String user;

    //start the application
    public static void main(String[] args) {
        //discover login service
        String login_server_type = "_userLogin._tcp.local.";
        discoverLoginService(login_server_type);
        //discover monitoring service
        String monit_server_type = "_monitoring._tcp.local.";
        discoverMonitService(monit_server_type);
        //discover system service
        String system_server_type = "_system._tcp.local.";
        discoverSystemService(system_server_type);

        //display login screen
        LoginScreen();
    }

    //logic performed when particular buttons clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        //if login button pressed...
        if (e.getSource() == loginButton) {
            //login server vars
            String host = loginServiceInfo.getHostAddresses()[0];
            int port = loginServiceInfo.getPort();
            // get username entered in textbox
            user = userText.getText();
            String pass = passText.getText();
            System.out.println(user + " " + pass);

            // Creating channel for connection
            ManagedChannel loginChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
            logger.info("Channel created with jmDNS discovered server details");
            logger.info("Successfully set up the communication channel.");
            UserLoginServiceGrpc.UserLoginServiceBlockingStub blockingStub;
            blockingStub = UserLoginServiceGrpc.newBlockingStub(loginChannel);
            //call user login client and pass in collected parameters
            UserLoginResponse response = UserLoginClient.userLogin(user, pass, blockingStub);
            int responseCode = response.getResponseCode();
            //shut connection
            try {
                UserLoginClient.shut(loginChannel);
                logger.info("Successfully shut down the communication channel");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //response code 1 from login server is successful login, code 99 is unsuccessful login
            if (responseCode == 1) {
                logger.info("Successfully logged in..");
                //remove old panel
                frame.remove(panel);
                //load new panel
                SelectPage();
                logger.info("Successfully loaded the select system UI page");
            } else {
                failure.setText("Login details incorrect, please try again...");
            }
        } else if (e.getSource() == logoutButton) {
            //login server vars
            String host = loginServiceInfo.getHostAddresses()[0];
            int port = loginServiceInfo.getPort();

            // Creating channel for connection
            ManagedChannel logoutChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
            logger.info("Channel created with jmDNS discovered server details");
            logger.info("Successfully set up the communication channel.");
            UserLoginServiceGrpc.UserLoginServiceBlockingStub blockingStub;
            blockingStub = UserLoginServiceGrpc.newBlockingStub(logoutChannel);
            //call user login client and pass in collected parameters
            UserLogoutRequest logoutRequest = UserLogoutRequest.newBuilder().setName(user).build();
            UserLogoutResponse response = blockingStub.userLogout(logoutRequest);
            int responseCode = response.getResponseCode();
            //shut connection
            try {
                UserLoginClient.shut(logoutChannel);
                logger.info("Successfully shut down the communication channel");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //response code 1 from login server is successful logout, code 99 is unsuccessful loout
            if (responseCode == 1) {
                logger.info("Successfully logged out..");
                //remove old panel
                frame.remove(panel);
                //load new panel
                LoginScreen();
                logger.info("Successfully loaded the login UI page");
            } else {
                failure.setText("Failed to logout...");
            }
        } else if (e.getSource() == airPollutionMonitoringButton) {
            //remove old panel
            frame.remove(panel);
            SelectMonitoringOption();
            logger.info("Successfully loaded the air pollution monitoring selection UI page");
        } else if (e.getSource() == airPurificationSystemButton) {
            //remove old panel
            frame.remove(panel);
            PurificationSystemPage();
            logger.info("Successfully loaded the air purification system UI page");

        } else if (e.getSource() == purificationChangeSpeed) {
            String selectedSpeed = (String) changeSpeed.getSelectedItem();
            //system server variables
            String host = systemServiceInfo.getHostAddresses()[0];
            int port = systemServiceInfo.getPort();
            //Create a channel for the connection using variables discovered about the system server
            ManagedChannel changeSpeedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
            logger.info("Successfully set up the communication channel.");
            AirPurificationSystemGrpc.AirPurificationSystemBlockingStub blockingStub;
            blockingStub = AirPurificationSystemGrpc.newBlockingStub(changeSpeedChannel);
            //set request
            ChangeSpeedRequest request = ChangeSpeedRequest.newBuilder().setSpeed(selectedSpeed).build();
            // set reply
            ChangeSpeedReply reply;
            //error handling
            try {
                reply = blockingStub
                        // setting a deadline 3 seconds from now for this to complete
                        .withDeadlineAfter(3, TimeUnit.SECONDS)
                        .changeSpeed(request);
                logger.info("The air purification system has been set to : " + reply.getSpeed());
            } catch (StatusRuntimeException err) {
                logger.log(Level.WARNING, "Call to change speed failed: {0}", err.getStatus());
                return;
            }
            logger.info("Air purification system changed to: " + reply.getSpeed());
            speedChangeSuccess.setText("Speed successfully changed to: " + selectedSpeed);
            //rpc termination
            // no new tasks will be accepted, starts orderly shutdown
            changeSpeedChannel.shutdown();
            // waits for all shutdown tasks to complete or the timeout, whichever is first
            try {
                changeSpeedChannel.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            logger.info("Change speed communication channel successfully shutdown");

        } else if ((e.getSource() == purificationBackButton) ||
                (e.getSource() == monitoringBackButton) ||
                (e.getSource() == roomAirQualityBackButton) ||
                (e.getSource() == allRoomAirQualityBackButton) ||
                (e.getSource() == roomsAirQualityBackButton) ||
                (e.getSource() == aveRoomAirQualityBackButton)) {
            //remove old panel
            frame.remove(panel);
            //load new panel
            SelectPage();
            logger.info("Successfully loaded the select system UI page");
        } else if ((e.getSource() == monitoringRoomAirQuality)) {
            //remove old panel
            frame.remove(panel);
            //load new panel
            RoomAirQualityPage();
            logger.info("Successfully loaded the Room Air Quality page");
        } else if ((e.getSource() == monitoringAllRoomAirQuality)) {
            //remove old panel
            frame.remove(panel);
            //load new panel
            AllRoomAirQualityPage();
            logger.info("Successfully loaded the All Room Air Quality page");
        } else if ((e.getSource() == monitoringRoomsAirQuality)) {
            //remove old panel
            frame.remove(panel);
            //load new panel
            RoomsAirQualityPage();
            logger.info("Successfully loaded the Rooms Air Quality page");
        } else if ((e.getSource() == monitoringAveRoomAirQuality)) {
            //remove old panel
            frame.remove(panel);
            //load new panel
            AveRoomAirQualityPage();
            logger.info("Successfully loaded the Average Rooms Air Quality page");
        } else if (e.getSource() == buttonSelectRoomAirQuality) {
            //get the selected room from the combo box in the UI
            String selectedRoom = (String) selectRoomAirQualityRoom.getSelectedItem();
            //Create a channel for the connection
            ManagedChannel selectedRoomChannel = ManagedChannelBuilder.forAddress("localhost", 50556).usePlaintext().build();
            logger.info("Successfully set up the communication channel.");
            AirPollutionMonitoringGrpc.AirPollutionMonitoringBlockingStub blockingStub;
            blockingStub = AirPollutionMonitoringGrpc.newBlockingStub(selectedRoomChannel);
            //set request
            AirQualityRequest request = AirQualityRequest.newBuilder().setRoom(selectedRoom).build();
            // set reply
            AirQualityReply reply;
            //error handling
            try {
                reply = blockingStub
                        // setting a deadline 3 seconds from now for this to complete
                        .withDeadlineAfter(3, TimeUnit.SECONDS)
                        .roomAirQuality(request);
                logger.info("The air quality of the selected room is: " + reply.getQuality());
            } catch (StatusRuntimeException err) {
                logger.log(Level.WARNING, "Call to change speed failed: {0}", err.getStatus());
                return;
            }
            logger.info("The air quality of the selected room is: " + reply.getQuality());
            selectRoomAirQualitySuccess.setText("The air quality of the selected room is: " + reply.getQuality());
            //rpc termination
            // no new tasks will be accepted, starts orderly shutdown
            selectedRoomChannel.shutdown();
            // waits for all shutdown tasks to complete or the timeout, whichever is first
            try {
                selectedRoomChannel.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            logger.info("Select room air quality communication channel successfully shutdown");

        } else if (e.getSource() == buttonSelectAllRoomAirQuality) {
            //get the selected room from the combo box in the UI
            String selectedAllRoom = (String) selectAllRoomAirQualityRoom.getSelectedItem();
            //Create a channel for the connection
            ManagedChannel selectedAllRoomChannel = ManagedChannelBuilder.forAddress("localhost", 50556).usePlaintext().build();
            logger.info("Successfully set up the communication channel.");
            AirPollutionMonitoringGrpc.AirPollutionMonitoringBlockingStub blockingStub;
            blockingStub = AirPollutionMonitoringGrpc.newBlockingStub(selectedAllRoomChannel);
            //set request
            AllAirQualityRequest request = AllAirQualityRequest.newBuilder().setAllRoom(selectedAllRoom).build();
            // set reply
            Iterator<AirQualityReply> replies;
            //error handling
            try {
                replies = blockingStub
                        // setting a deadline 3 seconds from now for this to complete
                        .withDeadlineAfter(3, TimeUnit.SECONDS)
                        .allRoomAirQuality(request);
                int counter = 0;
                while (replies.hasNext()) {
                    AirQualityReply reply = replies.next();
                    logger.info("The air quality of the selected room is: " + reply.getQuality());
                    if (counter == 0) {
                        selectAllRoomAirQualitySuccess1.setText("The air quality of the room 1 is: " + reply.getQuality());
                    } else if (counter == 1) {
                        selectAllRoomAirQualitySuccess2.setText("The air quality of the room 2 is: " + reply.getQuality());
                    } else if (counter == 2) {
                        selectAllRoomAirQualitySuccess3.setText("The air quality of the room 3 is: " + reply.getQuality());
                    } else {
                        selectAllRoomAirQualitySuccess4.setText("The air quality of the room 4 is: " + reply.getQuality());
                    }
                    counter = counter + 1;
                }
            } catch (StatusRuntimeException err) {
                logger.log(Level.WARNING, "Call to change speed failed: {0}", err.getStatus());
                return;
            }
            //logger.info("The air quality of the selected room is: " + reply.getQuality());
            //selectAllRoomAirQualitySuccess.setText("The air quality of the selected room is: " + reply.getQuality());
            //rpc termination
            // no new tasks will be accepted, starts orderly shutdown
            selectedAllRoomChannel.shutdown();
            // waits for all shutdown tasks to complete or the timeout, whichever is first
            try {
                selectedAllRoomChannel.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            logger.info("Select room air quality communication channel successfully shutdown");

        }
        //show selected rooms air quality
        else if (e.getSource() == buttonSelectRoomsAirQuality) {
            //Create a channel for the connection
            ManagedChannel selectedRoomsChannel = ManagedChannelBuilder.forAddress("localhost", 50556).usePlaintext().build();
            logger.info("Successfully set up the communication channel.");
            AirPollutionMonitoringGrpc.AirPollutionMonitoringStub nonBlockingStub;
            nonBlockingStub = AirPollutionMonitoringGrpc.newStub(selectedRoomsChannel);
            //reply stream observer
            StreamObserver<AirQualityReply> responseObserver = new StreamObserver<AirQualityReply>() {
                @Override
                public void onNext(AirQualityReply value) {
                    //System.out.println("Air quality for selected room is " + value.getQuality());
                    if (room1.isSelected()) {
                        selectRoomsAirQualitySuccess1.setText("The air quality of the room 1 is: " + value.getQuality());
                    }
                    if (room2.isSelected()) {
                        selectRoomsAirQualitySuccess2.setText("The air quality of the room 2 is: " + value.getQuality());
                    }
                    if (room3.isSelected()) {
                        selectRoomsAirQualitySuccess3.setText("The air quality of the room 3 is: " + value.getQuality());
                    }
                    if (room4.isSelected()) {
                        selectRoomsAirQualitySuccess4.setText("The air quality of the room 4 is: " + value.getQuality());
                    }
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
            //set request
            StreamObserver<AirQualityRequest> requestObserver =
                    nonBlockingStub.roomsAirQuality(responseObserver);
            try {
                // Sleep for some time before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room1.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("1").build());
                }
                // Sleep for some time before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room2.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("2").build());
                }
                // Sleep for a bit before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room3.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("3").build());
                }
                // Sleep for a bit before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room4.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("4").build());
                }

                logger.info("Sending messages...");

                // end of requests
                requestObserver.onCompleted();

            } catch (RuntimeException | InterruptedException e3) {
                e3.printStackTrace();
            }
            //rpc termination
            // no new tasks will be accepted, starts orderly shutdown
            selectedRoomsChannel.shutdown();
            // waits for all shutdown tasks to complete or the timeout, whichever is first
            try {
                selectedRoomsChannel.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            logger.info("Select room air quality communication channel successfully shutdown");

        }
        //show selected rooms average air quality
        else if (e.getSource() == buttonSelectAveRoomAirQuality) {
            //Create a channel for the connection
            ManagedChannel selectedAveRoomChannel = ManagedChannelBuilder.forAddress("localhost", 50556).usePlaintext().build();
            logger.info("Successfully set up the communication channel.");
            AirPollutionMonitoringGrpc.AirPollutionMonitoringStub nonBlockingStub;
            nonBlockingStub = AirPollutionMonitoringGrpc.newStub(selectedAveRoomChannel);
            //reply stream observer
            StreamObserver<AveAirQualityReply> responseObserver = new StreamObserver<AveAirQualityReply>() {
                @Override
                public void onNext(AveAirQualityReply value) {
                    //System.out.println("Air quality for selected room is " + value.getQuality());
                    selectAveRoomAirQualitySuccess.setText("The average air quality is: " + value.getQuality());

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
            //set request
            StreamObserver<AirQualityRequest> requestObserver =
                    nonBlockingStub.aveRoomAirQuality(responseObserver);
            try {
                // Sleep for some time before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room1.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("1").build());
                }
                // Sleep for some time before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room2.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("2").build());
                }
                // Sleep for a bit before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room3.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("3").build());
                }
                // Sleep for a bit before sending the next one.
                Thread.sleep(new Random().nextInt(900) + 400);
                if (room4.isSelected()) {
                    requestObserver.onNext(AirQualityRequest.newBuilder().setRoom("4").build());
                }

                logger.info("Sending messages...");

                // end of requests
                requestObserver.onCompleted();

            } catch (RuntimeException | InterruptedException e3) {
                e3.printStackTrace();
            }
            //rpc termination
            // no new tasks will be accepted, starts orderly shutdown
            selectedAveRoomChannel.shutdown();
            // waits for all shutdown tasks to complete or the timeout, whichever is first
            try {
                selectedAveRoomChannel.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            logger.info("Select room air quality communication channel successfully shutdown");

        }
    }

    //this page is displayed after selecting the "Air Pollution Monitoring" button
    public static void SelectMonitoringOption() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        loginHeader = new JLabel("Air Pollution System - control page");
        loginHeader.setBounds(60, 20, 300, 40);
        panel.add(loginHeader);

        monitoringRoomAirQuality = new JButton("RoomAirQuality");
        monitoringRoomAirQuality.setBounds(40, 80, 180, 25);
        monitoringRoomAirQuality.addActionListener(new AirGUI());
        panel.add(monitoringRoomAirQuality);

        monitoringAllRoomAirQuality = new JButton("AllRoomAirQuality");
        monitoringAllRoomAirQuality.setBounds(240, 80, 180, 25);
        monitoringAllRoomAirQuality.addActionListener(new AirGUI());
        panel.add(monitoringAllRoomAirQuality);

        monitoringRoomsAirQuality = new JButton("RoomsAirQuality");
        monitoringRoomsAirQuality.setBounds(40, 120, 180, 25);
        monitoringRoomsAirQuality.addActionListener(new AirGUI());
        panel.add(monitoringRoomsAirQuality);

        monitoringAveRoomAirQuality = new JButton("AveRoomAirQuality");
        monitoringAveRoomAirQuality.setBounds(240, 120, 180, 25);
        monitoringAveRoomAirQuality.addActionListener(new AirGUI());
        panel.add(monitoringAveRoomAirQuality);

        monitoringBackButton= new JButton("Back to menu");
        monitoringBackButton.setBounds(40, 160, 140, 25);
        monitoringBackButton.addActionListener(new AirGUI());
        panel.add(monitoringBackButton);
    }

    //UI page to display the options to change the air purification system speed
    public static void PurificationSystemPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        loginHeader = new JLabel("Air Purification System - control page");
        loginHeader.setBounds(60, 20, 300, 40);
        panel.add(loginHeader);

        JLabel currentStatus = new JLabel("Select speed " );
        currentStatus.setBounds(20, 60, 300, 25);
        panel.add(currentStatus);

        //list of options to set the speed to
        String[] options = {"off", "low", "med", "high"};
        //drop down menu to select speed
        changeSpeed = new JComboBox(options);
        changeSpeed.setBounds(140, 60, 140, 25);
        panel.add(changeSpeed);

        //empty message that can be filled once we successfully modify the speed
        speedChangeSuccess = new JLabel("");
        speedChangeSuccess.setBounds(20, 100, 300, 25);
        panel.add(speedChangeSuccess);
        speedChangeSuccess.setText("");

        purificationChangeSpeed = new JButton("Set Speed");
        purificationChangeSpeed.setBounds(40, 140, 140, 25);
        purificationChangeSpeed.addActionListener(new AirGUI());
        panel.add(purificationChangeSpeed);

        purificationBackButton= new JButton("Back to menu");
        purificationBackButton.setBounds(240, 140, 140, 25);
        purificationBackButton.addActionListener(new AirGUI());
        panel.add(purificationBackButton);

    }

    //after login page this page will offer a selection of the monitoring Ui or the UI to modify the air purification system
    public static void SelectPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        loginHeader = new JLabel("Air Pollution System - control page");
        loginHeader.setBounds(60, 20, 300, 40);
        panel.add(loginHeader);

        airPurificationSystemButton = new JButton("Air Purification System");
        airPurificationSystemButton.setBounds(40, 80, 180, 25);
        airPurificationSystemButton.addActionListener(new AirGUI());
        panel.add(airPurificationSystemButton);

        airPollutionMonitoringButton = new JButton("Air Pollution Monitoring");
        airPollutionMonitoringButton.setBounds(240, 80, 180, 25);
        airPollutionMonitoringButton.addActionListener(new AirGUI());
        panel.add(airPollutionMonitoringButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(180, 120, 180, 25);
        logoutButton.addActionListener(new AirGUI());
        panel.add(logoutButton);
    }

    //Initial login screen
    public static void LoginScreen() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // configure frame for GUI - outer window
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        loginHeader = new JLabel("Air Pollution System - login page");
        loginHeader.setBounds(20, 20, 300, 40);
        panel.add(loginHeader);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(20, 60, 80, 25);
        panel.add(userLabel);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(20, 100, 80, 25);
        panel.add(passLabel);

        loginButton = new JButton("Login");
        loginButton.setBounds(40, 140, 80, 25);
        loginButton.addActionListener(new AirGUI());
        panel.add(loginButton);

        failure = new JLabel("");
        failure.setBounds(20, 180, 300, 25);
        panel.add(failure);
        failure.setText("");

        // configure text fields
        userText = new JTextField(20);
        userText.setBounds(120, 60, 165, 25);
        panel.add(userText);

        passText = new JPasswordField(20);
        passText.setBounds(120, 100, 165, 25);
        panel.add(passText);

    }

    //UI page to display the options to change select Room Air quality
    public static void RoomAirQualityPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        JLabel roomAirQualityHeader = new JLabel("Room Air Quality - monitor page");
        roomAirQualityHeader.setBounds(60, 20, 300, 40);
        panel.add(roomAirQualityHeader);

        JLabel roomAirQualitySelectRoom = new JLabel("Select room " );
        roomAirQualitySelectRoom.setBounds(20, 60, 300, 25);
        panel.add(roomAirQualitySelectRoom);

        //list of options to select one of teh 4 room
        String[] roomOptions = {"1", "2", "3", "4"};
        //drop down menu to select speed
        selectRoomAirQualityRoom = new JComboBox(roomOptions);
        selectRoomAirQualityRoom.setBounds(140, 60, 140, 25);
        panel.add(selectRoomAirQualityRoom);


        //empty message that can be filled once we successfully a room to request air quality from
        selectRoomAirQualitySuccess = new JLabel("");
        selectRoomAirQualitySuccess.setBounds(20, 100, 300, 25);
        panel.add(selectRoomAirQualitySuccess);
        selectRoomAirQualitySuccess.setText("");

        buttonSelectRoomAirQuality = new JButton("Select Room");
        buttonSelectRoomAirQuality.setBounds(40, 140, 140, 25);
        buttonSelectRoomAirQuality.addActionListener(new AirGUI());
        panel.add(buttonSelectRoomAirQuality);

        roomAirQualityBackButton= new JButton("Back to menu");
        roomAirQualityBackButton.setBounds(240, 140, 140, 25);
        roomAirQualityBackButton.addActionListener(new AirGUI());
        panel.add(roomAirQualityBackButton);

    }

    //UI page to display the options to change select Room Air quality
    public static void AllRoomAirQualityPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        JLabel allRoomAirQualityHeader = new JLabel("All Room's Air Quality - monitor page");
        allRoomAirQualityHeader.setBounds(60, 20, 300, 40);
        panel.add(allRoomAirQualityHeader);

        JLabel allRoomAirQualitySelectRoom = new JLabel("Select room " );
        allRoomAirQualitySelectRoom.setBounds(20, 60, 300, 25);
        panel.add(allRoomAirQualitySelectRoom);

        //list of options to select one of teh 4 room
        String[] allRoomOptions = {"All"};
        //drop down menu to select speed
        selectAllRoomAirQualityRoom = new JComboBox(allRoomOptions);
        selectAllRoomAirQualityRoom.setBounds(140, 60, 140, 25);
        panel.add(selectAllRoomAirQualityRoom);


        //empty message that can be filled once we successfully a room to request air quality from
        selectAllRoomAirQualitySuccess1 = new JLabel("");
        selectAllRoomAirQualitySuccess1.setBounds(20, 100, 300, 25);
        panel.add(selectAllRoomAirQualitySuccess1);
        selectAllRoomAirQualitySuccess1.setText("");

        selectAllRoomAirQualitySuccess2 = new JLabel("");
        selectAllRoomAirQualitySuccess2.setBounds(20, 120, 300, 25);
        panel.add(selectAllRoomAirQualitySuccess2);
        selectAllRoomAirQualitySuccess2.setText("");

        selectAllRoomAirQualitySuccess3 = new JLabel("");
        selectAllRoomAirQualitySuccess3.setBounds(20, 140, 300, 25);
        panel.add(selectAllRoomAirQualitySuccess3);
        selectAllRoomAirQualitySuccess3.setText("");

        selectAllRoomAirQualitySuccess4 = new JLabel("");
        selectAllRoomAirQualitySuccess4.setBounds(20, 160, 300, 25);
        panel.add(selectAllRoomAirQualitySuccess4);
        selectAllRoomAirQualitySuccess4.setText("");

        buttonSelectAllRoomAirQuality = new JButton("Show all Room's Air Quality");
        buttonSelectAllRoomAirQuality.setBounds(40, 180, 240, 25);
        buttonSelectAllRoomAirQuality.addActionListener(new AirGUI());
        panel.add(buttonSelectAllRoomAirQuality);

        allRoomAirQualityBackButton= new JButton("Back to menu");
        allRoomAirQualityBackButton.setBounds(340, 180, 140, 25);
        allRoomAirQualityBackButton.addActionListener(new AirGUI());
        panel.add(allRoomAirQualityBackButton);

    }
    //UI page to display the options select certain room and have their air quality returned
    public static void RoomsAirQualityPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        JLabel roomsAirQualityHeader = new JLabel("Select Specific Room's Air Quality - monitor page");
        roomsAirQualityHeader.setBounds(60, 20, 400, 40);
        panel.add(roomsAirQualityHeader);

        JLabel roomsAirQualitySelectRoom = new JLabel("Select the room's you wish to query: " );
        roomsAirQualitySelectRoom.setBounds(20, 60, 300, 25);
        panel.add(roomsAirQualitySelectRoom);

        room1 = new JCheckBox("Room 1");
        room1.setBounds(240, 60, 140, 25);
        panel.add(room1);
        room2 = new JCheckBox("Room 2");
        room2.setBounds(240, 80, 140, 25);
        panel.add(room2);
        room3 = new JCheckBox("Room 3");
        room3.setBounds(240, 100, 140, 25);
        panel.add(room3);
        room4 = new JCheckBox("Room 4");
        room4.setBounds(240, 120, 140, 25);
        panel.add(room4);

        //empty message that can be filled once we successfully a room to request air quality from
        selectRoomsAirQualitySuccess1 = new JLabel("");
        selectRoomsAirQualitySuccess1.setBounds(20, 140, 300, 25);
        panel.add(selectRoomsAirQualitySuccess1);
        selectRoomsAirQualitySuccess1.setText("");

        selectRoomsAirQualitySuccess2 = new JLabel("");
        selectRoomsAirQualitySuccess2.setBounds(20, 160, 300, 25);
        panel.add(selectRoomsAirQualitySuccess2);
        selectRoomsAirQualitySuccess2.setText("");

        selectRoomsAirQualitySuccess3 = new JLabel("");
        selectRoomsAirQualitySuccess3.setBounds(20, 180, 300, 25);
        panel.add(selectRoomsAirQualitySuccess3);
        selectRoomsAirQualitySuccess3.setText("");

        selectRoomsAirQualitySuccess4 = new JLabel("");
        selectRoomsAirQualitySuccess4.setBounds(20, 200, 300, 25);
        panel.add(selectRoomsAirQualitySuccess4);
        selectRoomsAirQualitySuccess4.setText("");

        buttonSelectRoomsAirQuality = new JButton("Show selected Room's Air Quality");
        buttonSelectRoomsAirQuality.setBounds(40, 240, 240, 25);
        buttonSelectRoomsAirQuality.addActionListener(new AirGUI());
        panel.add(buttonSelectRoomsAirQuality);

        roomsAirQualityBackButton= new JButton("Back to menu");
        roomsAirQualityBackButton.setBounds(340, 240, 140, 25);
        roomsAirQualityBackButton.addActionListener(new AirGUI());
        panel.add(roomsAirQualityBackButton);

    }
    //UI page to display the options select certain room and have their ave air quality returned
    public static void AveRoomAirQualityPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        JLabel aveRoomAirQualityHeader = new JLabel("Average Room's Air Quality - monitor page");
        aveRoomAirQualityHeader.setBounds(60, 20, 400, 40);
        panel.add(aveRoomAirQualityHeader);

        JLabel aveRoomAirQualitySelectRoom = new JLabel("Select the room's to get average: " );
        aveRoomAirQualitySelectRoom.setBounds(20, 60, 300, 25);
        panel.add(aveRoomAirQualitySelectRoom);

        room1 = new JCheckBox("Room 1");
        room1.setBounds(240, 60, 140, 25);
        panel.add(room1);
        room2 = new JCheckBox("Room 2");
        room2.setBounds(240, 80, 140, 25);
        panel.add(room2);
        room3 = new JCheckBox("Room 3");
        room3.setBounds(240, 100, 140, 25);
        panel.add(room3);
        room4 = new JCheckBox("Room 4");
        room4.setBounds(240, 120, 140, 25);
        panel.add(room4);

        //empty message that can be filled once we successfully a room to request air quality from
        selectAveRoomAirQualitySuccess = new JLabel("");
        selectAveRoomAirQualitySuccess.setBounds(20, 140, 300, 25);
        panel.add(selectAveRoomAirQualitySuccess);
        selectAveRoomAirQualitySuccess.setText("");


        buttonSelectAveRoomAirQuality = new JButton("Show Average Air Quality");
        buttonSelectAveRoomAirQuality.setBounds(40, 240, 240, 25);
        buttonSelectAveRoomAirQuality.addActionListener(new AirGUI());
        panel.add(buttonSelectAveRoomAirQuality);

        aveRoomAirQualityBackButton= new JButton("Back to menu");
        aveRoomAirQualityBackButton.setBounds(340, 240, 140, 25);
        aveRoomAirQualityBackButton.addActionListener(new AirGUI());
        panel.add(aveRoomAirQualityBackButton);

    }
    /**
     * jmDNS discovery service methods
     */
    private static void discoverLoginService(String service_type){
        try {
            //create jmdns instance
            JmDNS jmdnsDiscoverLogin = JmDNS.create(InetAddress.getLocalHost());
            //add listener
            jmdnsDiscoverLogin.addServiceListener(service_type, new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    System.out.println("Login service added " + event.getInfo());
                }
                @Override
                public void serviceRemoved(ServiceEvent event){
                    System.out.println("Login server removed: " + event.getInfo());
                }
                @Override
                public void serviceResolved(ServiceEvent event) {
                    System.out.println("Login server resolved: " + event.getInfo());
                    loginServiceInfo = event.getInfo();
                }
            });

            //Wait for a while
            Thread.sleep(2000);
            //close
            jmdnsDiscoverLogin.close();

        } catch (IOException e4){
            System.out.println(e4.getMessage());
        } catch (InterruptedException e4){
            e4.printStackTrace();
        }
    }
    private static void discoverMonitService(String service_type){
        try {
            //create jmdns instance
            JmDNS jmdnsDiscoverMonit = JmDNS.create(InetAddress.getLocalHost());
            //add listener
            jmdnsDiscoverMonit.addServiceListener(service_type, new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    System.out.println("Monitoring service added " + event.getInfo());
                }
                @Override
                public void serviceRemoved(ServiceEvent event){
                    System.out.println("Monitoring server removed: " + event.getInfo());
                }
                @Override
                public void serviceResolved(ServiceEvent event) {
                    System.out.println("Monitoring server resolved: " + event.getInfo());
                    monitoringServiceInfo = event.getInfo();
                }
            });

            //Wait for a while
            Thread.sleep(2000);
            //close
            jmdnsDiscoverMonit.close();

        } catch (IOException e4){
            System.out.println(e4.getMessage());
        } catch (InterruptedException e4){
            e4.printStackTrace();
        }
    }

    private static void discoverSystemService(String service_type){
        try {
            //create jmdns instance
            JmDNS jmdnsDiscoverSystem = JmDNS.create(InetAddress.getLocalHost());
            //add listener
            jmdnsDiscoverSystem.addServiceListener(service_type, new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    System.out.println("System service added " + event.getInfo());
                }
                @Override
                public void serviceRemoved(ServiceEvent event){
                    System.out.println("System server removed: " + event.getInfo());
                }
                @Override
                public void serviceResolved(ServiceEvent event) {
                    System.out.println("System server resolved: " + event.getInfo());
                    systemServiceInfo = event.getInfo();
                }
            });

            //Wait for a while
            Thread.sleep(2000);
            //close
            jmdnsDiscoverSystem.close();

        } catch (IOException e4){
            System.out.println(e4.getMessage());
        } catch (InterruptedException e4){
            e4.printStackTrace();
        }
    }
}

