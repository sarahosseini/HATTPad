//Filename: Login.java
//Authors: Sara Hosseini, Nanziba Tasneem, Bilal Ahmed, Hermann
//Date: June 6, 2017
//Description: The Login class designs a simple GUI for logging in a user or admin.

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.io.File; 
import java.awt.image.*; 
import javax.imageio.*; 
import java.io.IOException;

/** The Login class designs a simple GUI for logging in a user or admin.
* @author Sara Hosseini, Nanziba Tasneem, Bilal Ahmed, Hermann Tamnou */

public class Login extends JFrame
{
   /** Construct Login */
   public Login() //Constructor to prepare window size and login page.
   {
      //Construct JFrame
      final JFrame login = new JFrame("Login");
      login.setResizable(false);
      //login.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Image Resources\\Bowlerhat.png")));
      
      //Construct JPanel
      final ImagePanel loginPanel = new ImagePanel(new ImageIcon("Image Resources\\loginBackground.jpg").getImage());
      loginPanel.setLayout(null);
      
      //Construct JLabels for user instructions
      JLabel setUser = new JLabel("Username:");
      setUser.setFont(new Font("Century Gothic", Font.BOLD, 14));
      setUser.setBounds(75, 90, 150, 30);
      
      JLabel setPassword = new JLabel("Password:");
      setPassword.setFont(new Font("Century Gothic", Font.BOLD, 14));
      setPassword.setBounds(75, 160, 150, 30);
      
      //Construct Username and Password textfields
      final JTextField loginUsername = new JTextField();
      loginUsername.setToolTipText("Usernames are case-sensitive!");
      loginUsername.setText("");
      loginUsername.setBounds(75, 120, 150, 30);
      
      final JPasswordField passwordField = new JPasswordField(20);
      passwordField.setToolTipText("Passwords are case-sensitive!");
      passwordField.setFont(new Font("Century Gothic", Font.BOLD, 14));
      passwordField.setEchoChar('*');
      passwordField.setBounds(75, 190, 150, 30);
      
      
      
      //Make JButtons (x-axis, y-axis, width, height)
		/**JButton user = new JButton("User");
      user.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("User"))
            System.exit(0);
         }
      });
      
		JButton admin = new JButton("Admin");
      admin.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Admin"))
            System.exit(0);
         }
      });*/
      
      JButton loginUser = new JButton("Login");
      loginUser.setBounds(100, 260, 100, 30);
      loginUser.addActionListener(new ActionListener()
      {
         /** The actionPerformed() method makes buttons perform a specific task if pressed. 
      * @param e    The event that is occurring.  
      */

         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Login"))
            {
               String username = loginUsername.getText();
               String password = passwordField.getText();
               String userPass = username + " " + password;
               if(userPass.equals("user password") || userPass.equals("admin hattpad"))
               {
                  login.dispose();
                  boolean admin = true;
                  if(userPass.equals("user password"))
                  admin = false;
                  HATTPAD hattpad = new HATTPAD(admin);
               }
               else
               {
                  JOptionPane.showMessageDialog(loginPanel, "Incorrect Username/Password! Please try again.", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE); //Display error message
               }
            
            }
         }
      });
      
      JButton exit = new JButton("Exit");
      exit.setBounds(100, 320, 100, 30);
      exit.addActionListener(new ActionListener()
      {
         /** The actionPerformed() method makes buttons perform a specific task if pressed. 
      * @param e    The event that is occurring.  
      */

         public void actionPerformed(ActionEvent e)
         {
            String str = e.getActionCommand();  //Find out which button the user pressed
            if(str.equals("Exit"))
            System.exit(0);
         }
      });
      
      int r = 0;
      JCheckBox showPass = new JCheckBox("");
      showPass.setBounds(200, 220, 25, 20);
      
      showPass.addItemListener(new ItemListener() 
      {
      /** The itemStateChanged() method checks if CheckBox is selected or not. 
      * @param e    The event that is occurring.  
      */

         public void itemStateChanged(ItemEvent e) 
         {
            if (e.getStateChange() == ItemEvent.SELECTED)
               passwordField.setEchoChar('*');
            else 
               passwordField.setEchoChar((char) 0);
         }
      });
      
      //Customize JButton look
      loginUser.setBackground(new Color(206,52,52)); //Hex colour code
      loginUser.setForeground(Color.WHITE);
      loginUser.setFocusPainted(false);
      loginUser.setFont(new Font("Calibri", Font.BOLD, 16));
      
      exit.setBackground(new Color(95,128,203)); //Hex colour code
      exit.setForeground(Color.WHITE);
      exit.setFocusPainted(false);
      exit.setFont(new Font("Calibri", Font.BOLD, 16));
      
      showPass.setBackground(Color.WHITE);
      ImageIcon img = new ImageIcon("Image Resources\\eye-clipart-small.png");   //Set an icon for the button
      showPass.setIcon(img);
      
      
      //Add items to the JPanel
      loginPanel.add(setUser);
      loginPanel.add(setPassword);
      loginPanel.add(loginUsername);
      loginPanel.add(passwordField);
      loginPanel.add(loginUser);
      loginPanel.add(exit);
      loginPanel.add(showPass);
      
      //Add JPanel to the JFrame, and set the properties of the frame as well
      login.add(loginPanel);
		login.setSize(300, 500);
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
      
   }











}