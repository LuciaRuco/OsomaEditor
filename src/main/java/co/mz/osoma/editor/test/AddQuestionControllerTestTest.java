package co.mz.osoma.editor.test;

import co.mz.osoma.editor.controlador.MainGUIController;
import co.mz.osoma.editor.modelo.Question;
import javafx.scene.control.TreeItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddQuestionControllerTestTest{



    @Test
    void getSelectedItemObject() {
        MainGUIController mainGUIController = null;
        TreeItem<Object> selectedItem = mainGUIController.getSeletedItem();
        Assertions.assertEquals((Question) selectedItem.getValue(),mainGUIController.getSeletedItem().getValue());

    }
}