package co.mz.osoma.editor.test;

import co.mz.osoma.editor.controlador.MainGUIController;
import co.mz.osoma.editor.modelo.Choice;
import jdk.nashorn.internal.ir.LiteralNode;
import org.junit.jupiter.api.Assertions;

class AddChoiceControllerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void getSelectedItemObject() {
        LiteralNode<Object> selectedItem = null;
        MainGUIController mainGUIController=null;
        Assertions.assertEquals((Choice) selectedItem.getValue(),mainGUIController.getSeletedItem());
    }
}