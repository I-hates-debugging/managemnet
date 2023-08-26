package src;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        LoginPanel loginPanel=new LoginPanel("art/loginPage/background/background.png");
        SignUpPanel signUpPanel=new SignUpPanel("art/signUp/static/background/background.png");
        TopPanel topPanel=new TopPanel("art/topBar/static/background.png");
        HomePanel homePanel=new HomePanel("art\\homePage\\static\\background\\background1.png");
        AFrame frame=new AFrame();
//        frame.add(loginPanel);
//        frame.add(signUpPanel);
        frame.add(topPanel);
        frame.add((homePanel));

        ControlPanels controlPanels=new ControlPanels();
        ControlPanels.setLogin(loginPanel);
        ControlPanels.setSignUp(signUpPanel);
        ControlPanels.setTop(topPanel);
        ControlPanels.setHome(homePanel);








    }
}