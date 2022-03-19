package grpc.examples.SmartAir;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirGUI implements ActionListener {
    // GUI elements for login screen
    private static JLabel loginHeader;
    private static JLabel userLabel;
    private static JLabel passLabel;
    private static JButton button;
    private static JLabel success;
    private static JTextField userText;
    private static JPasswordField passText;
    //needed to move these out of main method so we can modify them from outside main method
    private static JPanel panel;
    private static JFrame frame;

    public static void main(String[] args) {


        //display login screen
        LoginScreen();


    }

    public static void LoginScreen(){
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
        loginHeader = new JLabel("Air Pollution System - login");
        loginHeader.setBounds(20, 20, 200, 40);
        panel.add(loginHeader);

        userLabel = new JLabel("Username");
        userLabel.setBounds(20, 60, 80, 25);
        panel.add(userLabel);

        passLabel = new JLabel("Password");
        passLabel.setBounds(20, 100, 80, 25);
        panel.add(passLabel);

        button = new JButton("Login");
        button.setBounds(40, 140, 80, 25);
        button.addActionListener(new AirGUI());
        panel.add(button);

        success =  new JLabel("");
        success.setBounds(20, 180, 300, 25);
        panel.add(success);
        success.setText("");

        // configure text fields
        userText = new JTextField(20);
        userText.setBounds(120, 60, 165, 25);
        panel.add(userText);

        passText = new JPasswordField(20);
        passText.setBounds(120, 100, 165, 25);
        panel.add(passText);

    }

    // this is executed everytime we click the button
    @Override
    public void actionPerformed(ActionEvent e) {
        // get username entered in textbox
        String user = userText.getText();
        String pass = passText.getText();
        System.out.println(user + " " + pass);

        if(user.equals("Colin") && pass.equals("DistSystems")){
            panel.remove(userText);
            panel.remove(userLabel);
            panel.remove(passText);
            panel.remove(passLabel);
            panel.remove(button);
            //reload page to clear above
            panel.revalidate();
            panel.repaint();
            //Display this text
            success.setText("Login successful...");
        }
        else {
            success.setText("Login details incorrect, please try again...");
        }

    }
}

