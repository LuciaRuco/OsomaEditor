package co.mz.osoma.editor.modelo;

import java.util.ArrayList;
import java.util.List;

public class QuestionMultiChoice extends Question{


    protected List<Choice> choices = new ArrayList<>();
    protected int totalOfChoices = 4;


    public QuestionMultiChoice(){
        super();
        this.qtype = QuestionType.SIGLE;
    }

    public QuestionMultiChoice(String question, String explanation, QuestionType questionType, List<Choice> choices, int totalOfChoices) {
        super(question, explanation);
        this.choices = choices;
        this.qtype = QuestionType.SIGLE;
        this.totalOfChoices = totalOfChoices;
    }


    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public int getTotalOfChoices() {
        return totalOfChoices;
    }

    public void setTotalOfChoices(int totalOfChoices) {
        this.totalOfChoices = totalOfChoices;
    }


    @Override
    public List<NodeObject> hasChild() {
        if(choices.size()==0)return null;
        List<NodeObject> nodeObjects = new ArrayList<>();
        for (NodeObject n : choices){
            nodeObjects.add(n);
        }
        return nodeObjects;
    }
}
