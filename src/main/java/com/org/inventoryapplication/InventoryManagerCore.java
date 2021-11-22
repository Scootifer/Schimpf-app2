package com.org.inventoryapplication;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Scott Schimpf
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Welcome to the core of the inventory manager. Most methods here will operate on a PASS or FAIL scale
// A method will return 0 for pass, or -1 for fail as declared by the final variables
//

public class InventoryManagerCore {

    final int PASS = 0;
    final int FAIL = -1;
    final int FAIL_ITEM_NOT_FOUND = -1;
    final int FAIL_ITEM_EXISTS = -2;

    ArrayList<InventoryItem> inventory = new ArrayList<>();

    //ObservableList<InventoryItem> inventory = FXCollections.observableArrayList();
    ObservableList<InventoryItem> display_list = FXCollections.observableArrayList(inventory);
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

        this.display_list.clear();
        this.current_sort = 1;

        for(InventoryItem i : this.inventory) {

            if(this.display_list.size() == 0) {
                this.display_list.add(i);
                continue;
            }

            for(int x = 0; x<this.display_list.size(); x++) {
                InventoryItem current = display_list.get(x);

                if(i.getPrice() >= current.getPrice()) {
                    this.display_list.add(x, i);
                    x=this.display_list.size() +1; //to stop the nested loop without stopping the original one.
                }
                else if(x== this.display_list.size()-1) {
                    this.display_list.add(i);
                }

            }
        }

    }

    //sorts the display list and returns it
    //the sorting will be done by the FXCollections sort and a comparator
    void sortBySerialNumber() {


        this.display_list.clear();
        this.current_sort = 2;

        for(InventoryItem i : this.inventory) {

            if(this.display_list.size() == 0) {
                this.display_list.add(i);
                continue;
            }

            for(int x = 0; x<this.display_list.size(); x++) {
                InventoryItem current = display_list.get(x);

                if(i.getSerial_number().compareTo(current.getSerial_number()) >= 0) {
                    this.display_list.add(x, i);
                    x=this.display_list.size() +1; //to stop the nested loop without stopping the original one.
                }
                else if(x== this.display_list.size()-1) {
                    this.display_list.add(i);
                }

            }
        }

    }

    //sorts the display list
    // The sorting is done by coming each item with the compare to function.
    void sortByName() {

        this.display_list.clear();
        this.current_sort = 3;

        for(InventoryItem i : this.inventory) {

            if(this.display_list.size() == 0) {
                this.display_list.add(i);
                continue;
            }

            for(int x = 0; x<this.display_list.size(); x++) {
                InventoryItem current = display_list.get(x);

                if(i.getName().compareTo(current.getName()) >= 0) {
                    this.display_list.add(x, i);
                    x=this.display_list.size() +1; //to stop the nested loop without stopping the original one.
                }
                else if(x== this.display_list.size()-1) {
                    this.display_list.add(i);
                    x = this.display_list.size() +1;
                }

            }
        }

    }

    //Traverses the list and sets display list as a list containing only the same name items
    int searchName(String name) {
        this.display_list.clear();
        this.selectedItem = null;
        boolean found = false;

        for(InventoryItem i : this.inventory) {
            if(i.getName().equals(name)) {
                this.display_list.add(i);
                found = true;
            }
        }


        if(found) return PASS;
        else return FAIL_ITEM_NOT_FOUND;
    }

    //Traverses the list and sets display list as a list containing only the same serial number item
    int searchSerial(String serial) {
        this.display_list.clear();
        this.selectedItem = null;


        for (InventoryItem i : this.inventory) {

            if (i.getSerial_number().equals(serial)) {
                this.display_list.add(i);
                return PASS;
            }
        }
        return FAIL_ITEM_NOT_FOUND;
    }

    //will validate the passed string and return 0 for valid or -1 for invalid format, or -2 for duplicate serial number
    int validateSerialNumber(String serial) {

        if(serial == null || serial.length() == 0) {
            return FAIL;
        }

        if(serial.length() != 13) {
            return FAIL;
        }

        char c = serial.charAt(0);
        if( !(c >64 && c<91) && !(c>96 && c<123) ) {
            return FAIL;
        }

        try {
            Integer.parseInt(serial.substring(2,5));
            Integer.parseInt(serial.substring(6,9));
            Integer.parseInt(serial.substring(10));
        }catch (Exception e) {
            return FAIL;
        }

        for(InventoryItem i : this.inventory) {
            if(i.getSerial_number().equals(serial)) return FAIL_ITEM_EXISTS;
        }

        return PASS;
    }

    //will validate the passed string and return 0 for valid or -1 for invalid
    int validateItemPrice(String price) {
        try {
            if(Double.parseDouble(price) >= 0) return PASS;
        } catch(Exception e) {
            return FAIL;
        }
        return PASS;
    }

    //will validate the passed string and return 0 for valid or -1 for invalid
    int validateItemName(String name) {
        int len = name.length();
        if(len < 2 || len > 256) {
            return FAIL;
        }
        return PASS;
    }

    //clears both the display and inventory list and returns an empty list
    void clearInventory() {
        this.inventory.clear();
        this.display_list.clear();
        this.current_sort = 0;
    }

    //removes the currently selected item and changes the display and inventory list.
    void removeSelectedItem() {
        if (cellSelectedDoesNotExist()) {
            return;
        }
        inventory.remove(this.selectedItem);
        refreshDisplayList();
    }

    //edits the selected item, and then replaces it with a new but modified copy
    void editSelectedItemName(String name) {
        if(cellSelectedDoesNotExist() || validateItemName(name) == FAIL) {
            return;
        }

        int index = inventory.indexOf(this.selectedItem);
        this.selectedItem.setName(name);
        this.inventory.remove(index);
        this.inventory.add(index, this.selectedItem);

        this.refreshDisplayList();

    }

    //edits the selected item, and then replaces it with a new but modified copy
    void editSelectedItemSerial(String serial) {
        if(cellSelectedDoesNotExist() || validateSerialNumber(serial) == FAIL) {
            return;
        }

        int index = inventory.indexOf(this.selectedItem);
        this.selectedItem.setSerial_number(serial);
        this.inventory.remove(index);
        this.inventory.add(index, this.selectedItem);

        this.refreshDisplayList();
    }

    //edits the selected item, and then replaces it with a new but modified copy
    void editSelectedItemPrice(String price) {
        if(cellSelectedDoesNotExist() || validateItemPrice(price) == FAIL) {
            return;
        }

        int index = inventory.indexOf(this.selectedItem);
        this.selectedItem.setPrice(Double.parseDouble(price));
        this.inventory.remove(index);
        this.inventory.add(index, this.selectedItem);

        this.refreshDisplayList();
    }

    //Saves the files in 3 parts, the serial number, name, and price, all separated by \t
    int save(File file){
        try{
            FileWriter fw = new FileWriter(file);
            for(InventoryItem i : this.inventory) {
                fw.append(i.getSerial_number()).append("\t").append(i.getName()).append("\t").append(String.valueOf(i.getPrice())).append("\n");
            }
            fw.close();

        }catch(Exception e) {
            return FAIL;
        }


        return PASS;
    }

    //Uses string.split to split the string at its separation points. IE \t
    // assigns the appropriate parts to a new item.
    void load(File file) {


        try {
            Scanner scan = new Scanner(file);

            while(scan.hasNextLine()) {

                String line = scan.nextLine();

                String[] parts = line.split("\t");
                String serial = parts[0];
                String name = parts[1];
                String price = parts[2];

                this.addItem(serial, name, price);
            }

        } catch (Exception e) {
          return;
        }
        refreshDisplayList();

    }

    //refreshes the display list, used after making an alteration to a pre-sorted or searched list.
    void refreshDisplayList(){
        switch (this.current_sort) {
            case 0 -> this.display_list = FXCollections.observableArrayList(inventory);
            case 1 -> sortByPrice();
            case 2 -> sortBySerialNumber();
            case 3 -> sortByName();
        }
    }

    //sets the display list to all items
    void showAll(){
        this.current_sort = 0;
        refreshDisplayList();
    }

    //returns if a cell is selected, used to avoid the onclick event handler from producing an error
    public boolean cellSelectedDoesNotExist(){
        return selectedItem == null;
    }

    //sets the focus, this is the item that will be acted on in most functions.
    public void setCurrentCell(InventoryItem currentCell) {
        this.selectedItem = currentCell;
    }

    // returns selected cells serial number
    public String getSelectedCellSerial(){
        return this.selectedItem.getSerial_number();
    }

    //returns the selected cells name
    public String getSelectedCellName(){
        return this.selectedItem.getName();
    }

    //returns the selected cells price
    public String getSelectedCellPriceString(){
        double pr = this.selectedItem.getPrice();
        return Double.toString(pr);
    }

    //returns an observable list of the display list, usable by the refresh list function in the controller
    public ObservableList<InventoryItem> getDisplay_list() {
        return display_list;
    }

    //returns the inventory of the core as an array list
    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }
}
