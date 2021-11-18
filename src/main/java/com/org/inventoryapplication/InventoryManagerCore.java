package com.org.inventoryapplication;

import javafx.collections.ObservableList;



public class InventoryManagerCore {

    ObservableList<InventoryItem> inventory;
    ObservableList<InventoryItem> display_list;
    InventoryItem selectedItem;

    //returns a sorted version of the current display list, and sets the new displayed list
    //the sorting will be done by the FXCollections sort and a comparator
    ObservableList<InventoryItem> SortByPrice(){}

    //returns a sorted version of the current display list, and sets the new displayed list
    //the sorting will be done by the FXCollections sort and a comparator
    ObservableList<InventoryItem> SortBySerialNumber() {}
    //returns a sorted version of the current display list, and sets the new displayed list
    //the sorting will be done by the FXCollections sort and a comparator
    ObservableList<InventoryItem> SortByName() {}

    //Traverses the list and returns a list containing only the matching element(s)
    ObservableList<InventoryItem>SearchName(String name) {}

    //returns a list containing only the corresponding item if it exists
    //TODO add flag for no item found
    ObservableList<InventoryItem>SearchSerial(String serial) {}

    //will validate the passed string and return 0 for valid or -1 for invalid
    int ValidateSerialNumber(String serial) {}

    //will validate the passed string and return 0 for valid or -1 for invalid
    int ValidateItemPrice(String price) {}

    //will validate the passed string and return 0 for valid or -1 for invalid
    int ValidateItemName(String name) {}

    //clears both the display and inventory list and returns an empty list
    ObservableList<InventoryItem> ClearInventory() {}

    //removes the currently selected item and changes the display and inventory list.
    ObservableList<InventoryItem> DeleteSelectedItem() {}

    //edits the selected item, and then replaces it with a new but modified copy
    void EditSelectedItemName(String name) {}

    //edits the selected item, and then replaces it with a new but modified copy
    void EditSelectedItemSerial(String serial) {}

    //edits the selected item, and then replaces it with a new but modified copy
    void EditSelectedItemPrice(double price) {}
}
