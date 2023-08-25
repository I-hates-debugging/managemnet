import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpPanel extends AImagePanel {
    private AImageLabel signUplogo=new AImageLabel("art/signUp/static/writing/signUp.png",64,0,0,0);
    private AImageLabel usernameImage=new AImageLabel("art/signUp/static/writing/username.png",45,0,5,0);
    private ATextBox usernameBox=new ATextBox();//Text box for the username
    private AImageLabel passwordImage=new AImageLabel("art/signUp/static/writing/password.png",25,0,5,0);//The "password" text in between the text box's
    private APasswordTextBox passwordBox=new APasswordTextBox();//Text box for the password
    private AImageLabel confirmPasswordImage=new AImageLabel("art/signUp/static/writing/ComfPassword.png",25,0,5,0);//The "confirm password" text in between the text box's
    private APasswordTextBox confirmPasswordBox=new APasswordTextBox();//Text box for the  password confirmation
    private AButton signUpButton=new AButton("art/signUp/animate/signUpButton/normal.png","art/signUp/animate/signUpButton/hover.png","art/signUp/animate/signUpButton/press.png",66,0,58,0);
    private AImageLabel haveAnAccImage=new AImageLabel("art/signUp/static/writing/haveAnAcc.png",0,0,2,0);
    private AStringButton loginButton=new AStringButton("Login");
    SignUpPanel(String file) {
        super(file);
        setVisible(false);
        addToPanel();
        addLoginButtonAction();
        mainPanel.setBorder(new EmptyBorder(0,28,0,28));

    }
    void addToPanel(){
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
}
