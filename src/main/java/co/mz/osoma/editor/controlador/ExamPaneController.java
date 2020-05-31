package co.mz.osoma.editor.controlador;

import co.mz.osoma.editor.service.TreeItemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import co.mz.osoma.editor.modelo.Exam;
import co.mz.osoma.editor.modelo.Question;
import org.jsoup.Jsoup;


import java.net.URL;
import java.util.ResourceBundle;

public class ExamPaneController implements Initializable, TreeItemController {

    private MainGUIController mainGUIController;
    private Exam exam;

    @FXML
    private TextField txtTitle;

    @FXML
    private TableView<Question> tblQuestions;

    @FXML
    private TableColumn<Question, String> item;

    @FXML
    private TableColumn<Question, String> type;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        item.setCellValueFactory(cellData -> {
            if (cellData.getValue().questionProperty().isEmpty().get()) return cellData.getValue().questionProperty();

            String question = cellData.getValue().questionProperty().get();
            StringProperty s = new SimpleStringProperty();
            s.set(Jsoup.parse(question).text());

            return s;
        });
    }

    public void setMainGUIController(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    public void fillForm(Object root) {
        Exam exam = (Exam) root;

        tblQuestions.setItems(exam.getQuestions());

    }
}
