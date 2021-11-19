package com.org.inventoryapplication;

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
        return String.format(" %-13s | %-256s | %15.2f", this.serial_number, this.name, this.price);
    }
}
