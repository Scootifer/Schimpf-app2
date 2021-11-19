package com.org.inventoryapplication;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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


    //reused from previous application adapted for this one
    @Override
    public void initialize(URL url, ResourceBundle rb) {



    }

    //display a list of the items sorted by name
    @FXML
    void SortNameBtnClick(){

    }

    //display a list of the items sorted by serial number
    @FXML
    void SortSerialNumClick(){

    }

    //display a list of the items sorted by price
    @FXML
    void SortPriceClick(){

    }

    //todo set filter to show all
    @FXML
    void addItem(){

    }

    //will edit the selected element
    @FXML
    void editItem(){

    }

    //will remove the selected element
    @FXML
    void removeBtnClick(){

    }

    //function will clear the core inventory and display list
    @FXML
    void clearBtnClick(){

    }

    //function will display the core save of the list in the ManagerCore
    @FXML
    void showAllBtnClick(){

    }

    //todo set selected cell to null
    //function will check if the serial number or name section are null and search for the item. if both spaces are occupied use the program will throw an error
    @FXML
    void searchBtnClick(){

    }


    @FXML
    void SaveBtnClick(){

    }

    @FXML
    void LoadBtnClick(){

    }

    //will refresh the listview with the passed in list, presumably the "display_list"
    @FXML
    void RefreshList(ObservableList<InventoryItem> filteredList) {

    }
}