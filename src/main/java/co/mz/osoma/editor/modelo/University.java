package co.mz.osoma.editor.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class University {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty shortname = new SimpleStringProperty();

    public University(int id, String name, String shortname) {
        this.id.set(id);
        this.name.set(name);
        this.shortname.set(shortname);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShortname() {
        return shortname.get();
    }

    public StringProperty shortnameProperty() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname.set(shortname);
    }
}
