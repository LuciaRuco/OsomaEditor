package co.mz.osoma.editor.modelo;

import co.mz.osoma.editor.service.Helper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Exam implements NodeObject{


    private StringProperty id = new SimpleStringProperty();
    private University university;
    private IntegerProperty examYear = new SimpleIntegerProperty();
    private StringProperty description = new SimpleStringProperty();
    private Category category;
    private ObservableList<Question> questions = FXCollections.observableArrayList();
    private int count;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Exam() {
        this.count = ++Helper.totalExams;
        this.id.set(UUID.randomUUID().toString());
    }

    public Exam(String description, ObservableList<Question> questions) {
        this.count = ++Helper.totalExams;
        this.id.set(UUID.randomUUID().toString());
        this.description = new SimpleStringProperty(description);
        this.questions = questions;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ObservableList<Question> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public int getExamYear() {
        return examYear.get();
    }

    public IntegerProperty examYearProperty() {
        return examYear;
    }

    public void setExamYear(int examYear) {
        this.examYear.set(examYear);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @JsonIgnore
    @Override
    public NodeObject getItem() {
        return this;
    }

    @Override
    public List<NodeObject> hasChild() {
        if(questions.size()==0)return null;
        List<NodeObject> nodeObjects = new ArrayList<>();
        for (NodeObject n : questions){
            nodeObjects.add(n);
        }
        return nodeObjects;
    }

    @Override
    public String toString() {
        return "Exam "  + count;
    }
}
