package co.mz.osoma.editor.test;

import co.mz.osoma.editor.controlador.ExamPaneController;
import javafx.beans.property.SimpleStringProperty;

import java.net.URL;
import java.util.ResourceBundle;

class ExamPaneControllerTest {


    @org.junit.jupiter.api.Test
    void initialize() {
        ExamPaneController examecontroller =new ExamPaneController();
        SimpleStringProperty s= new SimpleStringProperty();
        URL location;
        ResourceBundle resources;
       // SimpleStringProperty ss =examecontroller.initialize( location,  resources);


      //  if (s.set(Jsoup.parse(question).text()))



    }
}