package com.org.inventoryapplication;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        File f = new File("./data/test.txt");

        InventoryItem item = new InventoryItem("name", "A-111-222-333", 10.50);
        EXPECTED.add(item);

        core.load(f);

        Assertions.assertTrue(EXPECTED.get(0).equals(core.getInventory().get(0)));

    }

    @Test
    void testSave() throws FileNotFoundException {
        InventoryManagerCore core = new InventoryManagerCore();
        File f = new File("./data/testSave.txt");
        core.addItem("A-111-222-333", "name", "10.50");

        core.save(f);
        Scanner EXPECTED = new Scanner(new File("./data/testSaveEXPECTED.txt"));
        Scanner actual = new Scanner(f);


        Assertions.assertEquals(EXPECTED.nextLine(), actual.nextLine());

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

    @Test
    void testClear(){
        InventoryManagerCore core = new InventoryManagerCore();

        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name2", "10.60");

        core.clearInventory();

        Assertions.assertEquals(0, core.getInventory().size());
    }

    @Test
    void testSearchName() {
        InventoryManagerCore core = new InventoryManagerCore();
        InventoryItem EXPECTED = new InventoryItem("name", "A-111-222-333", 10.50);

        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name2", "10.60");

        core.searchName("name");

        Assertions.assertTrue(EXPECTED.equals(core.getDisplay_list().get(0)));
    }

    @Test
    void testSearchSerial() {
        InventoryManagerCore core = new InventoryManagerCore();
        InventoryItem EXPECTED = new InventoryItem("name", "A-111-222-333", 10.50);

        core.addItem("A-111-222-333", "name", "10.50");
        core.addItem("A-111-222-334", "name2", "10.60");

        core.searchSerial("A-111-222-333");

        Assertions.assertTrue(EXPECTED.equals(core.getDisplay_list().get(0)));
    }

    @Test
    void testValidationChecks(){
        InventoryManagerCore core = new InventoryManagerCore();

        Assertions.assertAll(
                () -> Assertions.assertEquals(-1, core.validateItemName("a")),
                () -> Assertions.assertEquals(0, core.validateItemName("abb")),
                () -> Assertions.assertEquals(-1, core.validateSerialNumber("a")),
                () -> Assertions.assertEquals(0, core.validateItemName("A-111-111-111")),
                () -> Assertions.assertEquals(-1, core.validateItemPrice("a")),
                () -> Assertions.assertEquals(0, core.validateItemPrice("15"))
        );
    }
}
