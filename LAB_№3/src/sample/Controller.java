package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller extends Main{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field_signin;

    @FXML
    private Button login_button;

    @FXML
    private Button signup_button;

    private void FXMLDocumentController(Stage stage, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sammit");
        stage.show();
    }

    @FXML
    private PasswordField password_field_signin;

    @FXML
    void initialize() {
        signup_button.setOnAction(actionEvent -> {
            close_and_openNext(FXML_SIGN_UP);
        });
        login_button.setOnAction(actionEvent -> {
            if(is_admin(login_field_signin.getText(), password_field_signin.getText())){
                close_and_openNext(FXML_ADMIN);
            }
            if(check_profile(login_field_signin.getText(), password_field_signin.getText()))
                close_and_openNext(FXML_SAMMIT);
            else
                clean_Input_signin();
        });
    }

    //вход для админа
    private boolean is_admin(String login, String password) {
        return login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD);
    }

    //проверка - зарегестрирован ли пользователь или нет?
    private boolean check_profile(String login, String password) {
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getLogin().equals(login) && users.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    //открытие нового окна и закрытие текущего окна
    private void close_and_openNext(String fxml) {
        Stage stage = new Stage();
        try {
            FXMLDocumentController(stage, fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = (Stage) signup_button.getScene().getWindow();
        primaryStage.hide();
    }
    private void clean_Input_signin(){
        login_field_signin.clear();
        password_field_signin.clear();
    }
}
