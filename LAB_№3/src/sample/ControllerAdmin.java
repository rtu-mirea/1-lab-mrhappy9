package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ControllerAdmin extends Main{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit_button_admin;

    @FXML
    private Button total_button_admin;

    @FXML
    private TextArea info_admin;

    @FXML
    void initialize() {
        exit_button_admin.setOnAction(actionEvent -> {
            close_and_openNext(FXML_SAMPLE);
        });
        total_button_admin.setOnAction(actionEvent -> {
            if(!countrySelected().equals("None") && !startDataSelected().equals("None") && !endDataSelected().equals("None")){
                info_admin.setText("Country for Sammit: " + countrySelected() + "\nData from: " + startDataSelected()
                                    + "\nData to: " + endDataSelected());
            }
            else
                info_admin.setText("There are no convenient data! \nReload the app and try again!");
        });
    }

    //поиск удобной даты для последнего дня саммита
    private String endDataSelected(){
        int max = 0;
        int repeat = 0;
        String dayTo = "";
        ArrayList<String> endData = new ArrayList<>();
        for(int index = 0; index < answers.size(); index++)
            endData.add(answers.get(index).getDay_to());

        for(int index = 0; index < answers.size(); index++){
            repeat = Collections.frequency(endData, endData.get(index));
            if (repeat > max){
                dayTo = endData.get(index);
                max = repeat;
            }
        }
        if(max == endData.size() || max == endData.size() - 1 || max == endData.size() - 2){
            return dayTo;
        }
        else
            return "None";
    }

    //поиск удобной даты для начала проведения саммита
    private String startDataSelected(){
        int max = 0;
        int repeat = 0;
        String dayFrom = "";
        ArrayList<String> startData = new ArrayList<>();
        for(int index = 0; index < answers.size(); index++)
            startData.add(answers.get(index).getDay_from());

        for(int index = 0; index < answers.size(); index++){
            repeat = Collections.frequency(startData, startData.get(index));
            if (repeat > max){
                dayFrom = startData.get(index);
                max = repeat;
            }
        }

        if(max == startData.size() || max == startData.size() - 1)
            return dayFrom;
        else
            return "None";
    }

    //Поиск страны, которую выбрало большинство участников
    private String countrySelected() {
        ArrayList<String> countries = new ArrayList<>();

        //Добавление всех стран в ArrayList
        for(int index = 0; index < answers.size(); index++)
            countries.add(answers.get(index).getCountry());

        String country = "";
        int repeat = 0;
        int max = 0;

        //поиск самой "популярной" страны
        for(int index = 0; index < countries.size(); index++){
            repeat = Collections.frequency(countries, countries.get(index));
            if (repeat > max){
                country = countries.get(index);
                max = repeat;
            }
        }

        //самую "популярную" страну должны выбрать ([все] || [все, кроме одного])
        if(max == countries.size() - 1 || max == countries.size()){
            return country;
        }
        else
            return "None";
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
        Stage primaryStage = (Stage) exit_button_admin.getScene().getWindow();
        primaryStage.hide();
    }
}
