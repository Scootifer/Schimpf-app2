package com.org.inventoryapplication;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Scott Schimpf
 */


public class InventoryItem {
    String serial_number;
    String name;
    double price;

    public InventoryItem(String name, String serial, double price) {
        this.name = name;
        this.serial_number = serial;
        this.price = price;
    }

    void setSerial_number(String serial) {
        this.serial_number = serial;
    }

    String getSerial_number() {
        return this.serial_number;
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setPrice(double price) {
        this.price = price;
    }

    double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {

        return String.format("%-13s | %-256s | %-8.2f", this.serial_number, this.name, this.price);
    }


    public boolean equals(InventoryItem i){
        return this.serial_number.equals(i.getSerial_number()) && this.name.equals(i.getName()) && this.price == i.getPrice();
    }
}
