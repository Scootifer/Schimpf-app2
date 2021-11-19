package com.org.inventoryapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Welcome to the core of the inventory manager. Most methods here will operate on a PASS or FAIL scale
// A method will return 0 for pass, or -1 for fail as declared by the final variables
//

public class InventoryManagerCore {

    final int PASS = 0;
    final int FAIL = -1;

    ObservableList<InventoryItem> inventory = FXCollections.observableArrayList();
    ObservableList<InventoryItem> display_list = FXCollections.observableArrayList();
    InventoryItem selectedItem;

    //0 for show all, 1 for price, 2 for serial, 3 for name
    int current_sort = 0;

    //Takes in the name, serial number, and price of an item
    // if the details are valid then the item will be added and a new list will be
    public void addItem(String serial, String name, String price) {

        this.inventory.add(new InventoryItem(name, serial, Double.parseDouble(price)));
        this.current_sort = 0;
        refreshDisplayList();

    }

    //sorts the display list and returns it
    //the sorting will be done by an insertion sort
    void sortByPrice(){

        ObservableList<InventoryItem> previous_list = this.display_list;
        this.display_list = FXCollections.observableArrayList();

        for(InventoryItem i : previous_list) {

            if(this.display_list.size() == 0) {
                this.display_list.add(i);
                continue;
            }

            for(int x = 0; x<this.display_list.size(); x++) {
                InventoryItem current = display_list.get(x);

                if(i.getPrice() >= current.getPrice()) {
                    this.display_list.add(x, i);
                    break;
                }

            }
        }


    }

    //sorts the display list and returns it
    //the sorting will be done by the FXCollections sort and a comparator
    void sortBySerialNumber() {

    }

    //sorts the display list and returns it
    //the sorting will be done by the FXCollections sort and a comparator
    void sortByName() {

    }

    //Traverses the list and sets display list as a list containing only the same name items
    int searchName(String name) {
        return PASS;
    }

    //Traverses the list and sets display list as a list containing only the same serial number item
    //TODO add flag for no item found
    int SearchSerial(String serial) { return PASS;}

    //will validate the passed string and return 0 for valid or -1 for invalid format, or -2 for duplicate serial number
    int validateSerialNumber(String serial) {
        return PASS;
    }

    //will validate the passed string and return 0 for valid or -1 for invalid
    int validateItemPrice(String price) {
        return PASS;
    }

    //will validate the passed string and return 0 for valid or -1 for invalid
    int validateItemName(String name) {
        return PASS;
    }

    //clears both the display and inventory list and returns an empty list
    void clearInventory() {}

    //removes the currently selected item and changes the display and inventory list.
    int deleteSelectedItem() {
        return PASS;
    }

    //edits the selected item, and then replaces it with a new but modified copy
    void editSelectedItemName(String name) {}

    //edits the selected item, and then replaces it with a new but modified copy
    void editSelectedItemSerial(String serial) {}

    //edits the selected item, and then replaces it with a new but modified copy
    void editSelectedItemPrice(double price) {}

    void save(){

    }

    void load() {

    }

    void refreshDisplayList(){
        switch (this.current_sort) {
            case 0 -> this.display_list = this.inventory;
            case 1 -> sortByPrice();
            case 2 -> sortBySerialNumber();
            case 3 -> sortByName();
        }
    }

    //returns if a cell is selected, used to avoid the onclick event handler from producing an error
    public boolean cellSelectedDoesNotExist(){
        return selectedItem == null;
    }

    //sets the datamanagers focus, this is the item that will be acted on in most functions.
    public void setCurrentCell(InventoryItem currentCell) {
        this.selectedItem = currentCell;
    }

    public String getSelectedCellSerial(){
        return this.selectedItem.getSerial_number();
    }

    public String getSelectedCellName(){
        return this.selectedItem.getName();
    }

    public String getSelectedCellPriceString(){
        double pr = this.selectedItem.getPrice();
        return Double.toString(pr);
    }

    public ObservableList<InventoryItem> getDisplay_list() {
        return display_list;
    }
}
