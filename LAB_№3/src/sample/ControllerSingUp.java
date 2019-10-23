package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerSingUp extends Main{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field_signup;

    @FXML
    private Button signup_button_second;

    @FXML
    private PasswordField password_field_signup;

    @FXML
    private TextField name_field_signup;

    @FXML
    void initialize() {
        signup_button_second.setOnAction(actionEvent -> {
            System.out.println(is_correctInput(login_field_signup.getText(), name_field_signup.getText(),
                    password_field_signup.getText()));
            if (is_correctInput(login_field_signup.getText(), name_field_signup.getText(),
                                        password_field_signup.getText())) {
                if (users.size() == 0) {
                    registration_newUser();
                    close_and_openNext(FXML_SAMPLE);
                }
                else {
                    if (is_single_login(login_field_signup.getText())) {
                        registration_newUser();
                        close_and_openNext(FXML_SAMPLE);
                    }
                    else
                        clean_Input_signup();
                }
            }
            else
                clean_Input_signup();

            System.out.println(users.size());
        });
    }

    //закрытие текущего окна после регистрации и открытие главного
    private void close_and_openNext(String fxml) {
        Stage stage = new Stage();
        try {
            FXMLDocumentController(stage, fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = (Stage) signup_button_second.getScene().getWindow();
        primaryStage.hide();
    }

    //Проверка коректных данных, введенных при вводе
    private boolean is_correctInput(String login, String name, String password){
        String test = "";
        String extra_test = "";

        //Проверка имени
        Pattern pattern = Pattern.compile("^[A-Za-zА-яа-я]+$");
        Matcher matcher = pattern.matcher(name);
        while(matcher.find())
            test = matcher.group();

        //Проверка логина
        Pattern extra_pattern = Pattern.compile("^[A-Za-z0-9]+[@]+[A-Za-z]+[.]+[A-Za-z]+$");
        Matcher extra_matcher = extra_pattern.matcher(login);
        while(extra_matcher.find())
            extra_test = extra_matcher.group();

        return !test.equals("") && !extra_test.equals("") && !password.isEmpty();
    }

    //Регистрация нового пользователя
    private void registration_newUser(){
        User person = new User(name_field_signup.getText(), login_field_signup.getText(),
                               password_field_signup.getText());
        users.add(person);
    }

    //Проверка логина на не занятость
    private boolean is_single_login(String check_login){
        for (int index = 0; index < users.size(); index++){
            if(users.get(index).getLogin().equals(check_login)) // проверка логина на не занятость
                return false;
        }
        return true;
    }

    //Очистка полей ввода
    private void clean_Input_signup(){
        login_field_signup.clear();
        password_field_signup.clear();
        name_field_signup.clear();
    }
    private void FXMLDocumentController(Stage stage, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sammit");
        stage.show();
    }
}
