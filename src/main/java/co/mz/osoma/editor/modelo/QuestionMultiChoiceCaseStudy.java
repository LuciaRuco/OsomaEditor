package co.mz.osoma.editor.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class QuestionMultiChoiceCaseStudy extends QuestionMultiChoice {
    private StringProperty caseOfStudy = new SimpleStringProperty();

    public QuestionMultiChoiceCaseStudy(){super();}

    public QuestionMultiChoiceCaseStudy(String question, String explanation, QuestionType questionType, List<Choice> choices, int numberOfChoices, StringProperty caseOfStudy) {
        super(question, explanation, questionType, choices, numberOfChoices);
        this.caseOfStudy = caseOfStudy;
    }

    public String getCaseOfStudy() {
        return caseOfStudy.get();
    }

    public StringProperty caseOfStudyProperty() {
        return caseOfStudy;
    }

    public void setCaseOfStudy(String caseOfStudy) {
        this.caseOfStudy.set(caseOfStudy);
    }

    @Override
    public String toString() {
        return "QuestionMultiChoiceCaseStudy{" +
                "caseOfStudy=" + caseOfStudy +
                '}';
    }
}
