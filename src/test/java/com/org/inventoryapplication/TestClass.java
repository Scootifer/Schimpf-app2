package com.org.inventoryapplication;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class TestClass {

    @Test
    void testAdd() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();

        InventoryItem item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);

        core.addItem("A-111-222-333", "name", "10.50");
        Assertions.assertTrue(EXPECTED.get(0).equals(core.getInventory().get(0)));
    }

    @Test
    void testRemove() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();

        InventoryItem item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);

        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name3", "10.50");

        core.setCurrentCell(core.getInventory().get(1));
        core.removeSelectedItem();

        Assertions.assertTrue(EXPECTED.get(0).equals(core.getInventory().get(0)));
    }

    @Test
    void testEdit() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();

        InventoryItem item = new InventoryItem("name2", "A-111-222-334", 10.60);
        EXPECTED.add(item);

        core.addItem("A-111-222-333", "name", "10.50");

        core.setCurrentCell(core.getInventory().get(0));
        core.editSelectedItemSerial("A-111-222-334");
        core.editSelectedItemPrice("10.60");
        core.editSelectedItemName("name2");

        Assertions.assertTrue(EXPECTED.get(0).equals(core.getInventory().get(0)));
    }

    @Test
    void testLoad() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();
        File f = new File("./data/test.tsv");

        InventoryItem item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);

        System.out.println(core.load(f));

        Assertions.assertTrue(EXPECTED.get(0).equals(core.getInventory().get(0)));

    }

    @Test
    void testSortSerial() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();

        InventoryItem item = new InventoryItem("name2", "A-111-222-334", 10.50);
        EXPECTED.add(item);
        item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);



        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name2", "10.50");

        core.sortBySerialNumber();
        

        Assertions.assertAll(
                () -> Assertions.assertTrue(EXPECTED.get(0).equals(core.getDisplay_list().get(0))),
                () -> Assertions.assertTrue(EXPECTED.get(1).equals(core.getDisplay_list().get(1)))
        );
    }

    @Test
    void testSortName() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();

        InventoryItem item = new InventoryItem("name2", "A-111-222-334", 10.50);
        EXPECTED.add(item);
        item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);



        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name2", "10.50");

        core.sortByName();


        Assertions.assertAll(
                () -> Assertions.assertTrue(EXPECTED.get(0).equals(core.getDisplay_list().get(0))),
                () -> Assertions.assertTrue(EXPECTED.get(1).equals(core.getDisplay_list().get(1)))
        );
    }

    @Test
    void testSortPrice() {
        InventoryManagerCore core = new InventoryManagerCore();
        ArrayList<InventoryItem> EXPECTED = new ArrayList<>();

        InventoryItem item = new InventoryItem("name2", "A-111-222-334", 10.60);
        EXPECTED.add(item);
        item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);



        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name2", "10.60");

        core.sortByPrice();



        Assertions.assertAll(
                () -> Assertions.assertTrue(EXPECTED.get(0).equals(core.getDisplay_list().get(0))),
                () -> Assertions.assertTrue(EXPECTED.get(1).equals(core.getDisplay_list().get(1)))
        );
    }
}
