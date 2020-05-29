package co.mz.osoma.editor.service;

import co.mz.osoma.editor.modelo.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomRootObjectDeserializer extends StdDeserializer<RootObject> {

    public CustomRootObjectDeserializer() {
        this(null);
    }

    public CustomRootObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RootObject deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException, JsonProcessingException {
        Helper.resetTotals();
        RootObject rootObject = new RootObject();

        try {

            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);


            if(!node.get("title").asText().equals("null"))
            rootObject.setTitle(node.get("title").asText());

            if(!node.get("fileVersion").asText().equals("null"))
            rootObject.setFileVersion(node.get("fileVersion").asText());

            if(!node.get("number").asText().equals("null"))
            rootObject.setNumber(node.get("number").asText());

            if(!node.get("passingScore").asText().equals("null"))
            rootObject.setPassingScore(node.get("passingScore").asDouble());

            if(!node.get("timeLimit").asText().equals("null"))
            rootObject.setTimeLimit(node.get("timeLimit").asInt());

            JsonNode examsNode = node.get("exams");
            ObservableList<Exam> exams = FXCollections.observableArrayList();
            examsNode.forEach(exam -> {
                Exam newExam = new Exam();
                newExam.setId(exam.get("id").asText());
                newExam.setDescription(exam.get("description").asText());

                JsonNode questionNode = exam.get("questions");
                ObservableList<Question> questions = FXCollections.observableArrayList();
                questionNode.forEach(question -> {
                    questions.add(new QuestionFactory().create(question));
                    Helper.totalChoices = 0;
                });

                newExam.setQuestions(questions);
                exams.add(newExam);
//                Helper.totalQuestions = 0;

            });

            rootObject.setExams(exams);


        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        } catch (NullPointerException exn) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(exn.getMessage());
            alert.show();
        }


        return rootObject;
    }
}

class QuestionFactory{

    public Question create(JsonNode questionNode){


        String questionType = questionNode.get("qtype").asText();


        if (questionType.equals(QuestionType.SIGLE.toString())) {
            QuestionMultiChoice question = new QuestionMultiChoice();

            question.setId( questionNode.get("id").asText());
            question.setQtype(QuestionType.SIGLE);

            if(!questionNode.get("question").asText().equals("null"))
            question.setQuestion(questionNode.get("question").asText());

            if(!questionNode.get("feedback").asText().equals("null"))
            question.setFeedback(questionNode.get("feedback").asText());

            JsonNode choices = questionNode.get("choices");
            List<Choice> choiceList = new ArrayList<>();
            choices.forEach(choice -> {
                Choice newChoice = new Choice();

                newChoice.setId(choice.get("id").asText());

                if(!choice.get("description").asText().equals("null"))
                newChoice.setDescription(choice.get("description").asText());

                if(!choice.get("label").asText().equals("null"))
                newChoice.setLabel(choice.get("label").asText());
                newChoice.setRightChoice(choice.get("rightChoice").asBoolean());

                choiceList.add(newChoice);
            });

            question.setChoices(choiceList);

            return question;
        }
        if (questionType.equals(QuestionType.SIGLECASESTUDY.toString())) {
            QuestionMultiChoiceCaseStudy question = new QuestionMultiChoiceCaseStudy();

            question.setId( questionNode.get("id").asText());
            question.setQtype(QuestionType.SIGLECASESTUDY);

            if(!questionNode.get("caseOfStudy").asText().equals("null"))
                question.setQuestion(questionNode.get("caseOfStudy").asText());
            if(!questionNode.get("question").asText().equals("null"))
                question.setQuestion(questionNode.get("question").asText());
            if(!questionNode.get("feedback").asText().equals("null"))
                question.setFeedback(questionNode.get("feedback").asText());

            JsonNode choices = questionNode.get("choices");
            List<Choice> choiceList = new ArrayList<>();
            choices.forEach(choice -> {
                Choice newChoice = new Choice();

                newChoice.setId(choice.get("id").asText());

                if(!choice.get("description").asText().equals("null"))
                    newChoice.setDescription(choice.get("description").asText());

                if(!choice.get("label").asText().equals("null"))
                    newChoice.setLabel(choice.get("label").asText());
                newChoice.setRightChoice(choice.get("rightChoice").asBoolean());

                choiceList.add(newChoice);
            });

            question.setChoices(choiceList);

            return question;
        }
        if (questionType.equals(QuestionType.MULTI.toString())) {
            return null;
        }
        if (questionType.equals(QuestionType.MULTICASESTUDY.toString())) {

            return null;
        }

        return null;
    }


}
