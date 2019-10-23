package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sammit");
        primaryStage.setScene(new Scene(root, 487, 328));
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Request> answers = new ArrayList<>();


    public static String FXML_SAMPLE = "sample.fxml";
    public static String FXML_SIGN_UP = "sign_up.fxml";
    public static String FXML_SAMMIT = "sammit.fxml";
    public static String FXML_ADMIN = "admin.fxml";

    public static String ADMIN_LOGIN = "admin";
    public static String ADMIN_PASSWORD = "admin";

}
