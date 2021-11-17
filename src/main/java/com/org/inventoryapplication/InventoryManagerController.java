package com.org.inventoryapplication;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class InventoryManagerController {

    @FXML
    ToggleGroup SortGroup;
    @FXML
    RadioButton SortNameBtn;
    @FXML
    RadioButton SortSerialBtn;
    @FXML
    RadioButton SortPriceBtn;

    @FXML
    TextField SerialNumField;
    @FXML
    TextField NameField;
    @FXML
    TextField PriceField;
    @FXML
    Button AddBtn;
    @FXML
    Button EditBtn;
    @FXML
    Button RemoveBtn;
    @FXML
    Button ClearBtn;

    @FXML
    Button SearchBtn;
    @FXML
    Button ShowAllBtn;
    @FXML
    TextField SearchSerialField;
    @FXML
    TextField SearchNameField;

}