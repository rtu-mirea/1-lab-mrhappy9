package fin;
import javax.swing.*;

public class Main extends JFrame{

    public static void main(String[] args) {
        ManageUser user = new ManageUser();
        user.launch_Gui(false, false, false);
    }
}
