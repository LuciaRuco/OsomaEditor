/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mz.osoma.editor.service;

import co.mz.osoma.editor.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;

import co.mz.osoma.editor.controlador.MainGUIController;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jsoup.Jsoup;

/**
 * @author Lenovo
 */
public class MyTreeCell extends TextFieldTreeCell<Object> {

    private ContextMenu questionMenu, examMenu, choiceMenu, collectionMenu, previewMenu;
    private MainGUIController controller;

    public MyTreeCell(MainGUIController mainGUIController) {

        this.controller = mainGUIController;

        MenuItem menu1 = MenuItemBuilder.create().text("Multi Choice (Single Select)").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {


                            QuestionMultiChoice questionMultiChoice = new QuestionMultiChoice();
                            questionMultiChoice.setQtype(QuestionType.SIGLE);
                            ((Exam)mainGUIController.getSeletedItem().getValue()).getQuestions().add(questionMultiChoice);

                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            Helper.totalChoices = 0;

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                Choice choice = new Choice();
                                questionMultiChoice.getChoices().add(choice);
                                mainGUIController.makeBranch(choice, node);
                            }

                        }catch (Exception e){

                        }
                    }
                }
        ).build();
        MenuItem menu2 = MenuItemBuilder.create().text("Multi Choice (Multiple Select)").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {


                            QuestionMultiChoice questionMultiChoice = new QuestionMultiChoice();
                            questionMultiChoice.setQtype(QuestionType.MULTI);
                            ((Exam)mainGUIController.getSeletedItem().getValue()).getQuestions().add(questionMultiChoice);

                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            Helper.totalChoices = 0;

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                Choice choice = new Choice();
                                questionMultiChoice.getChoices().add(choice);
                                mainGUIController.makeBranch(choice, node);
                            }

                        }catch (Exception e){

                        }
                    }
                }
        ).build();
        MenuItem menu3 = MenuItemBuilder.create().text("Multi Choice (Single Select) With Case of Study").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {


                            QuestionMultiChoiceCaseStudy questionMultiChoice = new QuestionMultiChoiceCaseStudy();
                            questionMultiChoice.setQtype(QuestionType.SIGLECASESTUDY);
                            ((Exam)mainGUIController.getSeletedItem().getValue()).getQuestions().add(questionMultiChoice);

                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            Helper.totalChoices = 0;

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                Choice choice = new Choice();
                                questionMultiChoice.getChoices().add(choice);
                                mainGUIController.makeBranch(choice, node);
                            }

                        }catch (Exception e){

                        }

                    }
                }
        ).build();
        MenuItem menu4 = MenuItemBuilder.create().text("Multi Choice (Multiple Select) With Case of Study").build();
        examMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuBuilder.create().text("New Question").items(menu1, menu2, menu3, menu4).build(),
                        MenuItemBuilder.create()
                                .text("preview")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                Rectangle2D r = Screen.getPrimary().getBounds();
                                                VBox root = new VBox();
                                                root.setPadding(new Insets(8, 8, 8, 8));
                                                root.setSpacing(5);
                                                root.setAlignment(Pos.TOP_LEFT);
//adicionar o exame no painel
                                                Exam exam = (Exam)mainGUIController.getSeletedItem().getValue();
                                                //StackPane secondaryLayout = new StackPane();
                                                //int linha = 0;
                                                for(Question q:exam.getQuestions()){
                                                    Text ord = new Text();
                                                    Text  questao = new Text();
                                                    Text  esplicacao = new Text();
                                                    ord.setText(q.getItem().toString());
                                                    ord.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,30));
                                                    questao.setText("Pergunta: "+Jsoup.parse(q.getQuestion()).text());
                                                    esplicacao.setText("Explicaca: "+Jsoup.parse(q.feedbackProperty().getValue()).text());
                                                    questao.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                                                    esplicacao.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
                                                    root.getChildren().addAll(ord,questao,esplicacao);
                                                    char i='A';
                                                    for(Choice c : ((QuestionMultiChoice)q).getChoices()){
                                                        Text choice = new Text();
                                                        choice.setText(i+") "+Jsoup.parse(c.getDescription()).text());
                                                        choice.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
                                                        root.getChildren().add(choice);
                                                        i++;
                                                    }
                                                }

                                                Scene secondScene = new Scene(root, r.getWidth(), r.getHeight());

// New window (Stage)
                                                Stage newWindow = new Stage();//importante
                                                newWindow.setTitle("Exame Preview");
                                                newWindow.setScene(secondScene);

// Set position of second window, related to primary window.
                                                newWindow.setX(0);
                                                newWindow.setY(0);

                                                newWindow.show();

                                                /*try {
                                                    Exam exam = (Exam)mainGUIController.getSeletedItem().getValue();
                                                    for (Question q: exam.getQuestions()) {
                                                        System.out.println(q.getQuestion());
                                                        switch (q.getQtype()){
                                                            case SIGLE:{
                                                                QuestionMultiChoice qmc= (QuestionMultiChoice)q;
                                                                for(Choice c: qmc.getChoices()){
                                                                    System.out.print(" "+c.getDescription()+", ");
                                                                }
                                                            }break;
                                                            case SIGLECASESTUDY:{
                                                                QuestionMultiChoiceCaseStudy qmccs= (QuestionMultiChoiceCaseStudy)q;
                                                                for(Choice c: qmccs.getChoices()){
                                                                    System.out.print(" "+c.getDescription()+", ");
                                                                }
                                                            }break;

                                                        }

                                                    }

                                                }catch (Exception e){

                                                }*/
                                            }
                                        }
                                ).build(),
                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                try {
                                                    Exam exam = new Exam();
                                                    ((RootObject)mainGUIController.getSeletedItem().getValue()).getExams().add(exam);
                                                    TreeItem<Object> node = mainGUIController.makeBranch(exam, mainGUIController.getSeletedItem());
                                                    Helper.totalQuestions = 0;

                                                }catch (Exception e){

                                                }
                                            }
                                        }
                                ).disable(true)
                                .build()
                )
                .build();

        choiceMenu = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Move Up")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Down")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),

                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {

                                                TreeItem<Object> objectTreeItem = mainGUIController.getSeletedItem();
                                                ((QuestionMultiChoice)objectTreeItem.getParent().getValue()).getChoices().remove((Choice) objectTreeItem.getValue());
                                                boolean remove = objectTreeItem.getParent().getChildren().remove(objectTreeItem);


                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();

        questionMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Add Choice")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {

                                                Helper.totalChoices = ((QuestionMultiChoice)mainGUIController.getSeletedItem().getValue()).getChoices().size()-1;

                                                Choice choice = new Choice();
                                                ((QuestionMultiChoice)mainGUIController.getSeletedItem().getValue()).getChoices().add(choice);
                                                mainGUIController.makeBranch(new Choice(), mainGUIController.getSeletedItem());
                                            }
                                        }
                                )
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Up")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Down")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Properties")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }

                                        }
                                )
                                .build(),

                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                TreeItem<Object> objectTreeItem = mainGUIController.getSeletedItem();
                                               ((Exam)objectTreeItem.getParent().getValue()).getQuestions().remove((Question)objectTreeItem.getValue());
                                                boolean remove = objectTreeItem.getParent().getChildren().remove(objectTreeItem);
                                            }
                                        }
                                )
                                .build(),
                        // other menu item

                        MenuItemBuilder.create()
                                .text("Previsuaizar")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                )
                                .build()


                )
                .build();

        collectionMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("New Exam")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                Exam novo= new Exam();
                                                ObservableList<Exam> exams = FXCollections.observableArrayList();
                                                exams.add(novo);
                                                RootObject rootObject = new RootObject(exams);
                                                TreeItem<Object> node = mainGUIController.makeBranch(novo, mainGUIController.getSeletedItem());


                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();


    }

    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);


      if (!empty && item instanceof RootObject) {
          setContextMenu(collectionMenu);
          return;
      }

        if(!empty && item instanceof Exam){
            setContextMenu(examMenu);
            return;
        }

        if(!empty && item instanceof Question){
            setContextMenu(questionMenu);
            return;
        }

        if(!empty && item instanceof Choice){
            setContextMenu(choiceMenu);
            return;
        }
//        setContextMenu(questionMenu);
    }

}
