package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class ControllerSammit extends Main{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button choose_button;

    @FXML
    private DatePicker end_data;


    @FXML
    private DatePicker start_data;

    @FXML
    private ComboBox<String> country_combobox;


    @FXML
    void initialize() {
        //добавление подготовленных стран в combo_box
        country_combobox.getItems().addAll(set_comboboxDays());
        choose_button.setOnAction(actionEvent -> {
            String start = start_data.getEditor().getText();
            String end = end_data.getEditor().getText();
            String country = country_combobox.getSelectionModel().getSelectedItem();

            if(correct_input_sammit(start, end, country)){
                if(is_correct_SammitData(start, end)){
                    choose_data_for_Sammit(country, start, end);
                    close_and_openNext(FXML_SAMPLE);
                }
                else
                    clean_Input_Sammit();
            }
            else
                clean_Input_Sammit();

            System.out.println(answers.size());
        });
    }

    private String[] set_comboboxDays(){
        String str[] = new String[5];
        str[0] = "France";
        str[1] = "Germany";
        str[2] = "Italy";
        str[3] = "United Kingdom";
        str[4] = "Austria";
        return str;
    }

    //Проверка корректности ввода даты с и по
    private boolean is_correct_SammitData(String start, String end){
        //День начала
        int day_start = Integer.parseInt(String.valueOf(start.charAt(0)) + String.valueOf(start.charAt(1)));
        int month_start = Integer.parseInt(String.valueOf(start.charAt(3)) + String.valueOf(start.charAt(4)));
        //День конца
        int day_end = Integer.parseInt(String.valueOf(end.charAt(0)) + String.valueOf(end.charAt(1)));
        int month_end = Integer.parseInt(String.valueOf(end.charAt(3)) + String.valueOf(end.charAt(4)));

        return ((month_start < month_end) || ((month_start == month_end) && (day_start < day_end)));
    }

    //Очистка полей ввода
    private void clean_Input_Sammit(){
        start_data.getEditor().clear();
        end_data.getEditor().clear();
    }

    //добавление даты
    private void choose_data_for_Sammit(String country, String start, String end) {
        Request request = new Request(country, start, end);
        answers.add(request);
    }

    private boolean correct_input_sammit(String start, String end, String country){
        return !start.isEmpty() && !end.isEmpty() && !country.isEmpty();
    }

    private void FXMLDocumentController(Stage stage, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sammit");
        stage.show();
    }

    private void close_and_openNext(String fxml) {
        Stage stage = new Stage();
        try {
            FXMLDocumentController(stage, fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = (Stage) choose_button.getScene().getWindow();
        primaryStage.hide();
    }
}