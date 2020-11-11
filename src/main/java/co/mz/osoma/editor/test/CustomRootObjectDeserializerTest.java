package co.mz.osoma.editor.test;

import co.mz.osoma.editor.modelo.Exam;
import co.mz.osoma.editor.modelo.RootObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;

class CustomRootObjectDeserializerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void deserializeWithType() {
        Exam newExam0 = new Exam();
        Exam newExam1 = new Exam();
        Exam newExam2 = new Exam();
        ObservableList<Exam> exams = FXCollections.observableArrayList();
        exams.add(newExam0);
        exams.add(newExam1);
        exams.add(newExam2);
        RootObject rootObject=null;
        rootObject.setExams(exams);
        Assertions.assertArrayEquals(new ObservableList[]{exams}, new ObservableList[]{rootObject.getExams()});
    }
}