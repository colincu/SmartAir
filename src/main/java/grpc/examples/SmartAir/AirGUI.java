package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class AirGUI implements ActionListener {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirGUI.class.getName());

    private boolean loggedIn = false;
    // GUI elements for login screen
    private static JLabel loginHeader;
    private static JLabel roomAirQualityHeader;
    private static JLabel allRoomAirQualityHeader;
    private static JLabel roomsAirQualityHeader;
    private static JLabel aveRoomAirQualityHeader;
    private static JLabel failure;
    private static JLabel speedChangeSuccess;
    private static JLabel selectRoomAirQualitySuccess;
    private static JLabel selectAllRoomAirQualitySuccess1;
    private static JLabel selectAllRoomAirQualitySuccess2;
    private static JLabel selectAllRoomAirQualitySuccess3;
    private static JLabel selectAllRoomAirQualitySuccess4;
    private static JTextField userText;
    private static JPasswordField passText;
    private static JButton loginButton;
    private static JButton airPurificationSystemButton;
    private static JButton airPollutionMonitoringButton;
    private static JButton purificationChangeSpeed;
    private static JButton buttonSelectRoomAirQuality;
    private static JButton buttonSelectAllRoomAirQuality;
    private static JButton buttonSelectRoomsAirQuality;
    private static JButton buttonSelectAveRoomAirQuality;
    private static JButton purificationBackButton;
    private static JButton monitoringRoomAirQuality;
    private static JButton monitoringAllRoomAirQuality;
    private static JButton monitoringRoomsAirQuality;
    private static JButton monitoringAveRoomAirQuality;
    private static JButton monitoringBackButton;
    private static JButton roomAirQualityBackButton;
    private static JButton allRoomAirQualityBackButton;
    private static JButton roomsAirQualityBackButton;
    private static JButton aveRoomAirQualityBackButton;
    private static JComboBox changeSpeed;
    private static JComboBox selectRoomAirQualityRoom;
    private static JComboBox selectAllRoomAirQualityRoom;
    private static String selectedSpeed;
    private static String selectedRoom;
    private static String selectedAllRoom;
    //needed to move these out of main method so we can modify them from outside main method
    private static JPanel panel;
    private static JFrame frame;

    public static void main(String[] args) {

        //display login screen
        LoginScreen();


    }

    // Air pollution monitoring page
    public static void PollutionMonitoringPage() {
        // configure panel for GUI
        panel = new JPanel();
        panel.setLayout(null);

        // add panel to frame
        frame.add(panel);
        // make frame visible
        frame.setVisible(true);
        // configure label for header, user,  pass and login button
        loginHeader = new JLabel("Air Pollution Monitoring System");
        loginHeader.setBounds(20, 20, 300, 40);
        panel.add(loginHeader);

        JLabel roomAirQuality = new JLabel("Enter room number for air quality reading..");
        roomAirQuality.setBounds(20, 100, 300, 25);
        panel.add(roomAirQuality);

        JTextField roomAirQualityText = new JTextField(20);
        roomAirQualityText.setBounds(340, 100, 40, 25);
        panel.add(roomAirQualityText);

        JButton roomAirQualityButton = new JButton("Get Room Air Quality");
        roomAirQualityButton.setBounds(40, 180, 180, 25);
        roomAirQualityButton.addActionListener(new AirGUI());
        panel.add(roomAirQualityButton);
    }


    //after login page this page will offer a selection of the monitoring Ui actions
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


    //logic performed when particular buttons clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        //if login button pressed...
        if (e.getSource() == loginButton) {
            // get username entered in textbox
            String user = userText.getText();
            String pass = passText.getText();
            System.out.println(user + " " + pass);

            // Creating channel for connection
            ManagedChannel loginChannel = ManagedChannelBuilder.forAddress("localhost", 50555).usePlaintext().build();
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
                loggedIn = true;
                //remove old panel
                frame.remove(panel);
                //load new panel
                SelectPage();
                logger.info("Successfully loaded the select system UI page");
            } else {
                failure.setText("Login details incorrect, please try again...");
            }
        }
        else if (e.getSource() == airPollutionMonitoringButton){
            //remove old panel
            frame.remove(panel);
            SelectMonitoringOption();
            logger.info("Successfully loaded the air pollution monitoring selection UI page");
        }
        else if (e.getSource() == airPurificationSystemButton){
            //remove old panel
            frame.remove(panel);
            PurificationSystemPage();
            logger.info("Successfully loaded the air purification system UI page");

        }
        else if (e.getSource() == purificationChangeSpeed){
            selectedSpeed = (String) changeSpeed.getSelectedItem();
            //Create a channel for the connection
            ManagedChannel changeSpeedChannel = ManagedChannelBuilder.forAddress("localhost", 50557).usePlaintext().build();
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

        }
        else if ((e.getSource() == purificationBackButton ) ||
                (e.getSource() == monitoringBackButton) ||
                (e.getSource() == roomAirQualityBackButton) ||
                (e.getSource()) == allRoomAirQualityBackButton) {
            //remove old panel
            frame.remove(panel);
            //load new panel
            SelectPage();
            logger.info("Successfully loaded the select system UI page");
        }
        else if ((e.getSource() == monitoringRoomAirQuality )){
            //remove old panel
            frame.remove(panel);
            //load new panel
            RoomAirQualityPage();
            logger.info("Successfully loaded the Room Air Quality page");
        }
        else if ((e.getSource() == monitoringAllRoomAirQuality )){
            //remove old panel
            frame.remove(panel);
            //load new panel
            AllRoomAirQualityPage();
            logger.info("Successfully loaded the Room Air Quality page");
        }
        else if (e.getSource() == buttonSelectRoomAirQuality){
            //get the selected room from the combo box in the UI
            selectedRoom = (String) selectRoomAirQualityRoom.getSelectedItem();
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

        }
        else if (e.getSource() == buttonSelectAllRoomAirQuality){
            //get the selected room from the combo box in the UI
            selectedAllRoom = (String) selectAllRoomAirQualityRoom.getSelectedItem();
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
                while (replies.hasNext()){
                    AirQualityReply reply = replies.next();
                    logger.info("The air quality of the selected room is: " + reply.getQuality());
                    if (counter == 0) {
                        selectAllRoomAirQualitySuccess1.setText("The air quality of the selected room is: " + reply.getQuality());
                    }
                    else if (counter ==1){
                        selectAllRoomAirQualitySuccess2.setText("The air quality of the selected room is: " + reply.getQuality());
                    }
                    else if (counter ==2){
                        selectAllRoomAirQualitySuccess3.setText("The air quality of the selected room is: " + reply.getQuality());
                    }
                    else {
                        selectAllRoomAirQualitySuccess4.setText("The air quality of the selected room is: " + reply.getQuality());
                    }
                    counter = counter +1;
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
        String options[] = {"off", "low", "med", "high"};
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
        roomAirQualityHeader = new JLabel("Room Air Quality - monitor page");
        roomAirQualityHeader.setBounds(60, 20, 300, 40);
        panel.add(roomAirQualityHeader);

        JLabel roomAirQualitySelectRoom = new JLabel("Select room " );
        roomAirQualitySelectRoom.setBounds(20, 60, 300, 25);
        panel.add(roomAirQualitySelectRoom);

        //list of options to select one of teh 4 room
        String roomOptions[] = {"1", "2", "3", "4"};
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
        allRoomAirQualityHeader = new JLabel("All Room's Air Quality - monitor page");
        allRoomAirQualityHeader.setBounds(60, 20, 300, 40);
        panel.add(allRoomAirQualityHeader);

        JLabel allRoomAirQualitySelectRoom = new JLabel("Select room " );
        allRoomAirQualitySelectRoom.setBounds(20, 60, 300, 25);
        panel.add(allRoomAirQualitySelectRoom);

        //list of options to select one of teh 4 room
        String allRoomOptions[] = {"All"};
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
        buttonSelectAllRoomAirQuality.setBounds(40, 180, 140, 25);
        buttonSelectAllRoomAirQuality.addActionListener(new AirGUI());
        panel.add(buttonSelectAllRoomAirQuality);

        allRoomAirQualityBackButton= new JButton("Back to menu");
        allRoomAirQualityBackButton.setBounds(240, 180, 140, 25);
        allRoomAirQualityBackButton.addActionListener(new AirGUI());
        panel.add(allRoomAirQualityBackButton);

    }
}

