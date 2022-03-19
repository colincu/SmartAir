package grpc.examples.SmartAir;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class AirGUI implements ActionListener {
    // create logger object for logging
    private static final Logger logger = Logger.getLogger(AirGUI.class.getName());

    private boolean loggedIn = false;
    // GUI elements for login screen
    private static JLabel loginHeader;
    private static JLabel failure;
    private static JLabel speedChangeSuccess;
    private static JTextField userText;
    private static JPasswordField passText;
    private static JButton loginButton;
    private static JButton airPurificationSystemButton;
    private static JButton airPollutionMonitoringButton;
    private static JButton purificationChangeSpeed;
    private static JButton purificationBackButton;
    private static JComboBox changeSpeed;
    private static String selectedSpeed;
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
            PollutionMonitoringPage();
            logger.info("Successfully loaded the air pollution monitoring UI page");
        }
        else if (e.getSource() == airPurificationSystemButton){
            //remove old panel
            frame.remove(panel);
            PurificationSystemPage();
            logger.info("Successfully loaded the air purification system UI page");

        }
        else if (e.getSource() == purificationChangeSpeed){
            selectedSpeed = (String) changeSpeed.getSelectedItem();
            logger.info("Air purification system changed to: " + selectedSpeed);
            speedChangeSuccess.setText("Speed successfully changed to: " + selectedSpeed);
        }
        else if (e.getSource() == purificationBackButton){
            //remove old panel
            frame.remove(panel);
            //load new panel
            SelectPage();
            logger.info("Successfully loaded the select system UI page");
        }
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
}

