package co.mz.osoma.editor.modelo;

import co.mz.osoma.editor.service.Helper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;


public abstract class Question implements NodeObject{
    

    private StringProperty id = new SimpleStringProperty();
    protected StringProperty question = new SimpleStringProperty();
    protected StringProperty feedback = new SimpleStringProperty();
    protected QuestionType qtype;
    protected int count;


    public Question() {
        this.count = ++Helper.totalQuestions;
        this.id.set(UUID.randomUUID().toString());
    }

    public Question(String question, String feedback) {
        this.id.set(UUID.randomUUID().toString());
        this.count = ++Helper.totalQuestions;
        this.question = new SimpleStringProperty(question);
        this.feedback = new SimpleStringProperty(feedback);
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

    public QuestionType getQtype() {
        return qtype;
    }

    public void setQtype(QuestionType qtype) {
        this.qtype = qtype;
    }




    public Question(String question, String feedback, QuestionType qtype) {
        this.question = new SimpleStringProperty(question);
        this.feedback = new SimpleStringProperty(feedback);
        this.qtype = qtype;
    }
    public String getQuestion() {
        return question.get();
    }

    public StringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getFeedback() {
        return feedback.get();
    }

    public StringProperty feedbackProperty() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback.set(feedback);
    }

    @JsonIgnore
    @Override
    public NodeObject getItem() {
        return this;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Question " + count;
    }
}
