/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mz.osoma.editor.service;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import co.mz.osoma.editor.controlador.MainGUIController;

/**
 *
 * @author Lenovo
 */
public class CallbackCustom implements Callback<TreeView<Object>,TreeCell<Object>> {

    private MainGUIController mainGUIController;

    public CallbackCustom(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    @Override
    public TreeCell<Object> call(TreeView<Object> arg0) {
        // custom tree cell that defines a context menu for the root tree item
        return new MyTreeCell(mainGUIController);
    }

//    @Override
//    public TreeCell<Object> call(TreeView<Object> param) {
//        return null;
//    }
}
