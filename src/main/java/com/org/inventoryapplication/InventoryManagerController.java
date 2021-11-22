package com.org.inventoryapplication;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Scott Schimpf
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerController implements Initializable {

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

    @FXML
    Button SaveBtn;
    @FXML
    Button LoadBtn;

    @FXML
    ListView<InventoryItem> ListViewID;

    InventoryManagerCore core = new InventoryManagerCore();
    final int FAIL = -1;
    final int FAIL_INVALID_FORMAT = -1;
    final int FAIL_ITEM_EXISTS = -2;
    final int FAIL_ITEM_NOT_FOUND = -1;


    //reused from previous application adapted for this one
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Here the onMouseClick event is updated with a custom event
        // First it checks that a cell was actually selected
        // Next it sets the fields to the value of the item for editing
        // Finally it sets the radio buttons accordingly7
        ListViewID.setOnMouseClicked(event -> {
            core.setCurrentCell(ListViewID.getSelectionModel().getSelectedItem());
            if(core.cellSelectedDoesNotExist()){
                return;
            }

            //sets desc and date fields for editing
            PriceField.setText(core.getSelectedCellPriceString());
            NameField.setText(core.getSelectedCellName());
            SerialNumField.setText(core.getSelectedCellSerial());

        });

    }

    //display a list of the items sorted by name
    @FXML
    void SortNameBtnClick(){
        core.sortByName();
        refreshList();
    }

    //display a list of the items sorted by serial number
    @FXML
    void SortSerialNumClick(){
        core.sortBySerialNumber();
        refreshList();
    }


    //display a list of the items sorted by price
    @FXML
    void SortPriceClick(){
        core.sortByPrice();
        refreshList();
    }


    @FXML
    void addItem(){
        String name = NameField.getText();
        String price = PriceField.getText();
        String serial = SerialNumField.getText();

        if(core.validateItemName(name) == FAIL) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Name input is not valid");
            error.setContentText("The items name must be 2-256 characters in length.");
            error.showAndWait();
            return;
        }
        if(core.validateItemPrice(price) == FAIL) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Price input is not valid");
            error.setContentText("The items price must be a decimal point number.");
            error.showAndWait();
            return;
        }
        if(core.validateSerialNumber(serial) == FAIL_INVALID_FORMAT) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Serial Number input is not valid");
            error.setContentText("The serial number must be input in the format A-XXX-XXX-XXX \nWhere A must be a letter and X can be either a letter or digit");
            error.showAndWait();
            return;
        }
        if(core.validateSerialNumber(serial) == FAIL_ITEM_EXISTS) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Serial Number input is not valid");
            error.setContentText("The serial number of each item must be unique!");
            error.showAndWait();
            return;
        }

        core.addItem(serial, name, price);
        refreshList();

    }

    //will edit the selected element
    @FXML
    void editItem(){
        String name = NameField.getText();
        String serial = SerialNumField.getText();
        String price = PriceField.getText();

        if(!name.equals(core.getSelectedCellName())) {
            core.editSelectedItemName(name);
        }
        if(!serial.equals(core.getSelectedCellSerial())) {
            core.editSelectedItemSerial(serial);
        }
        if(!price.equals(core.getSelectedCellPriceString())) {
            core.editSelectedItemPrice(price);
        }


    }

    //will remove the selected element
    @FXML
    void removeBtnClick(){
        core.removeSelectedItem();
        refreshList();
    }

    //function will clear the core inventory and display list
    @FXML
    void clearBtnClick(){
        core.clearInventory();
        refreshList();
    }

    //function will display the core save of the list in the ManagerCore
    @FXML
    void showAllBtnClick(){
        core.showAll();
        refreshList();

        //Sets the sort radials correct
        if(SortNameBtn.isSelected()) SortNameBtn.fire();
        if(SortSerialBtn.isSelected()) SortSerialBtn.fire();
        if(SortPriceBtn.isSelected()) SortPriceBtn.fire();
    }

    //function will check if the serial number or name section are null and search for the item. if both spaces are occupied use the program will throw an error
    @FXML
    void searchBtnClick(){
        String serial = SearchSerialField.getText();
        String name = SearchNameField.getText();

         if( (serial == null && name == null) ) {


             Alert error = new Alert(Alert.AlertType.ERROR);
             error.setHeaderText("Invalid Search Parameters");
             error.setContentText("Please enter only a serial number OR name!");
             error.showAndWait();
             return;
         }
         if( (serial!= null && name!= null) && (serial.length() >0 && name.length() > 0) ) {
             Alert error = new Alert(Alert.AlertType.ERROR);
             error.setHeaderText("Invalid Search Parameters");
             error.setContentText("Please enter only a serial number OR name!");
             error.showAndWait();
             return;
         }


         if(serial == null || serial.length() == 0) {
             if(core.searchName(name) == FAIL_ITEM_NOT_FOUND) {
                 Alert error = new Alert(Alert.AlertType.ERROR);
                 error.setHeaderText("Item not found!");
                 error.showAndWait();
                 return;
             }
         }

         else {
             if(core.searchSerial(serial) == FAIL_ITEM_NOT_FOUND) {
                 Alert error = new Alert(Alert.AlertType.ERROR);
                 error.setHeaderText("Item not found!");
                 error.showAndWait();
                 return;
             }
         }
        refreshList();
    }


    @FXML
    void SaveBtnClick(){
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(SaveBtn.getScene().getWindow());
        if(core.save(file) == FAIL) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error Saving");
            error.showAndWait();
        }

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Successfully saved.");
        success.show();
    }

    @FXML
    void LoadBtnClick(){
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(LoadBtn.getScene().getWindow());
        core.load(file);
        refreshList();
    }

    //will refresh the listview with the passed in list, presumably the "display_list"
    @FXML
    void refreshList() {
        ListViewID.setItems(core.getDisplay_list());
    }
}