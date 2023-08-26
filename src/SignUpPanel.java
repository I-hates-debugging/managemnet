package src;

import src.DatabaseConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class SignUpPanel extends AImagePanel {
    private AImageLabel signUplogo = new AImageLabel("art/signUp/static/writing/signUp.png", 64, 0, 0, 0);
    private AImageLabel usernameImage = new AImageLabel("art/signUp/static/writing/username.png", 45, 0, 5, 0);
    private ATextBox usernameBox = new ATextBox();//Text box for the username
    private AImageLabel passwordImage = new AImageLabel("art/signUp/static/writing/password.png", 25, 0, 5, 0);//The "password" text in between the text box's
    private APasswordTextBox passwordBox = new APasswordTextBox();//Text box for the password
    private AImageLabel confirmPasswordImage = new AImageLabel("art/signUp/static/writing/ComfPassword.png", 25, 0, 5, 0);//The "confirm password" text in between the text box's
    private APasswordTextBox confirmPasswordBox = new APasswordTextBox();//Text box for the  password confirmation
    private AButton signUpButton = new AButton("art/signUp/animate/signUpButton/normal.png", "art/signUp/animate/signUpButton/hover.png", "art/signUp/animate/signUpButton/press.png", 66, 0, 58, 0);
    private AImageLabel haveAnAccImage = new AImageLabel("art/signUp/static/writing/haveAnAcc.png", 0, 0, 2, 0);
    private AStringButton loginButton = new AStringButton("Login");

    SignUpPanel(String file) {
        super(file);
        setVisible(false);
        addToPanel();
        addLoginButtonAction();
        addSignUpButtonAction();
        mainPanel.setBorder(new EmptyBorder(0, 28, 0, 28));

    }

    void addToPanel() {
        addMain(signUplogo);
        addMain(usernameImage);
        addMain(usernameBox);
        addMain(passwordImage);
        addMain(passwordBox);
        addMain(confirmPasswordImage);
        addMain(confirmPasswordBox);
        addMain(signUpButton);
        addMain(haveAnAccImage);
        addMain(loginButton);
    }

    void addLoginButtonAction(){
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControlPanels.showLogin();
            }
        });
    }
    void addSignUpButtonAction() {
        signUpButton.label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerUser();
            }
        });
    }


    private void registerUser() {
        String username = usernameBox.textLabel.getText();
        String password = passwordBox.textLabel.getText();
        String confirmPassword = confirmPasswordBox.textLabel.getText();
        try {
            Connection connection = DriverManager.getConnection(DatabaseConfig.jdbcUrl, DatabaseConfig.username, DatabaseConfig.password);

            if (usernameExists(connection, username)) {
                JOptionPane.showMessageDialog(null, "username already exists", "signup error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (password.equals(confirmPassword)) {
                    addUser(connection, username, password);
                    ControlPanels.showLogin();
                } else {
                    JOptionPane.showMessageDialog(null, "passwords do not match", "signup Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean usernameExists(Connection connection, String username) {
        String selectQuery = "SELECT username FROM user_login WHERE username = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addUser(Connection connection, String username, String password) {
        String insertQuery = "INSERT INTO user_login(username, password) VALUES(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
