package com.org.inventoryapplication;

import javafx.collections.ObservableList;



public class InventoryManagerCore {

    ObservableList<InventoryItem> inventory;
    ObservableList<InventoryItem> display_list;
    InventoryItem selectedItem;

    ObservableList<InventoryItem> SortByValue(){}

    ObservableList<InventoryItem> SortBySerialNumber() {}

    ObservableList<InventoryItem> SortByName() {}

    ObservableList<InventoryItem>SearchName(String name) {}

    ObservableList<InventoryItem>SearchSerial(String serial) {}

    int ValidateSerialNumber(String serial) {}

    int ValidateItemPrice(double price) {}

    int ValidateItemName(Sting name) {}

    void ClearInventory() {}

    void DeleteSelectedItem() {}

    void EditSelectedItemName(String name) {}

    void EditSelectedItemSerial(String serial) {}

    void EditSelectedItemPrice(double price) {}
}
