import javax.swing.*;
import java.awt.*;

public class ControlPanels {
    private static Component login, signUp, top, course, manage, settings;
    public ControlPanels(Component login, Component signUp, Component top, Component settings, Component manage, Component course) {
        this.login = login;
        this.signUp = signUp;
        this.top = top;
        this.settings = settings;
        this.manage = manage;
        this.course = course;
    }
    public ControlPanels(){
        this(new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel());
    }

    public static void setLogin(Component login) {
        ControlPanels.login = login;
    }

    public static void setSignUp(Component signUp) {
        ControlPanels.signUp = signUp;
    }

    public static void setTop(Component top) {
        ControlPanels.top = top;
    }

    public static void setSettings(Component settings) {
        ControlPanels.settings = settings;
    }

    public static void setManage(Component manage) {
        ControlPanels.manage = manage;
    }

    public static void setCourse(Component course) {
        ControlPanels.course = course;
    }


    private static void hideAllExcept(Component component) {
        Component[] components = {login, signUp, top, settings, manage, course};
        for (Component c : components) {
            if (c != null && c != component) {
                c.setVisible(false);
            }
        }
    }

    public static void showSignUp() {
        signUp.setVisible(true);
        hideAllExcept(signUp);
    }

    public static void showLogin() {
        login.setVisible(true);
        hideAllExcept(login);
    }

    public static void showTop() {
        top.setVisible(true);
        hideAllExcept(top);
    }

    public static void showSettings() {
        settings.setVisible(true);
        hideAllExcept(settings);
    }

    public static void showManage() {
        manage.setVisible(true);
        hideAllExcept(manage);
    }

    public static void showCourse() {
        course.setVisible(true);
        hideAllExcept(course);
    }
}
