package main;
import View.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import View.Boundary;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
public class login implements ActionListener{
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    HashMap<String,String> logindata = new HashMap<String,String>();
    login(HashMap<String,String> logininfo)
    {
        logindata = logininfo;
        
        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);
        
        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));
        
        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);
        
        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);        
        
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==resetButton)
        {
            userIDField.setText("");
            userPasswordField.setText("");
        }
        if(e.getSource()==loginButton)
        {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            
            if(logindata.containsKey(userID))
            {
                if(logindata.get(userID).equals(password))
                {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();
                    int choice = -1;
                    Scanner input = new Scanner(System.in); 
                    while (true) {
                       if (Boundary.moduleSelection(choice, input) == 1) {
                         do {
                            Boundary.displayOptions("staffMenu");
                            choice = input.nextInt();
                            switch (choice) {
                                case 1:
                                    MovieMod.createMovieListing(input);
                                    break;
                                case 2:
                                    MovieMod.updateMovie(input);
                                    break;
                                case 3:
                                	SystemSettingsView systemsettings = new SystemSettingsView();
                                	systemsettings.start(input);
                                	break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Error! Please enter either 0, 1, 2:");
                            }
                        } while (choice != 0);
                    }
        }
                }
                else
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }
            }
            else
            {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Username not found");
            }
        }
    }
}
