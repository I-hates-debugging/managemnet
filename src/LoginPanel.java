import src.DatabaseConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.sql.*;

public class LoginPanel extends AImagePanel {
    static boolean state=true;

   private AImageLabel loginImage=new AImageLabel("art/loginPage/comp/loginIcon.png",61,163,36,163);//The login image at the top
    private AImageLabel usernameImage=new AImageLabel("art/loginPage/comp/userAndPass/username.png",0,0,5,0);//The "username" text above the text box's
    private ATextBox usernameBox=new ATextBox();//Text box for the username
    private AImageLabel passwordImage=new AImageLabel("art/loginPage/comp/userAndPass/password.png",25,0,5,0);//The "password" text in between the text box's
    private APasswordTextBox passwordBox=new APasswordTextBox();//Text box for the password
    private ACheckBox checkBox=new ACheckBox(15,0, 17,0);//A check boc
    private AImageLabel rememberMeImage=new AImageLabel("art/loginPage/comp/userAndPass/rememberMe.png",17,5,17,178);//The "remember me" text below the text box's
    private AStringButton forgotPasswordButton=new AStringButton("Forgot Password?");//A string button for that says "Forgort Password?"
    private AButton loginbutton=new AButton("art/loginPage/comp/login/normal3.png","art/loginPage/comp/login/hover.png","art/loginPage/comp/login/press2.png",42,0,82,0);
    private AImageLabel dontHaveAccImage=new AImageLabel("art/loginPage/comp/login/dontHaveAcc.png",0,0,2,0);
    private AStringButton signUpButton=new AStringButton("Sign up");





    LoginPanel(String file) {
        super(file);
        addToPanel();
        addLoginbuttonAction();
        addSignUpButtonAction();
        mainPanel.setBorder(new EmptyBorder(0,28,0,28));

    }
    void addToPanel(){
        addMain(loginImage);
        addMain(usernameImage);
        addMain(usernameBox);
        addMain(passwordImage);
        addMain(passwordBox);
        addMain(checkBox);
        addMain(rememberMeImage);
        addMain(forgotPasswordButton);
        addMain(loginbutton);
        addMain(dontHaveAccImage);
        addMain(signUpButton);
    }
    Component getThisPanel(){
        return this;
    }
    void addLoginbuttonAction(){
        loginbutton.label.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                verifyPassword();
            }
        });
    }
    void addSignUpButtonAction(){
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlPanels.showSignUp();
            }
        });
    }

    private void verifyPassword(){
        String username=usernameBox.textLabel.getText();
        String password=passwordBox.textLabel.getText();
        try{
            //load the mysql JDBC driver
            Connection connection = DriverManager.getConnection(DatabaseConfig.jdbcUrl, DatabaseConfig.username, DatabaseConfig.password);

            String query = "SELECT password FROM user_login WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String pass = resultSet.getString("password");

                if (pass.equals(password)){
                    ControlPanels.showTop();
                }
                else{
                    JOptionPane.showMessageDialog(null, "username and password do not match", "login error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "username doesn't exist", "login error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
