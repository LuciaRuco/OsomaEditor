package co.mz.osoma.editor.modelo;

import co.mz.osoma.editor.service.Helper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;

import java.util.List;
import java.util.UUID;

public class Choice implements NodeObject{

    private StringProperty id = new SimpleStringProperty();
    private StringProperty label = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private BooleanProperty rightChoice = new SimpleBooleanProperty();
    private int count;

    public Choice() {
        this.count = ++Helper.totalChoices;
        this.id.set(UUID.randomUUID().toString());
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

    public boolean isRightChoice() {
        return rightChoice.get();
    }

    public BooleanProperty rightChoiceProperty() {
        return rightChoice;
    }

    public void setRightChoice(boolean rightChoice) {
        this.rightChoice.set(rightChoice);
    }

    public Choice(StringProperty description) {
        this.description = description;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }


    public String getLabel() {
        return label.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Choice " + (char)(count+64);
    }

    @JsonIgnore
    @Override
    public NodeObject getItem() {
        return this;
    }

    @Override
    public List<NodeObject> hasChild() {
        return null;
    }



}
