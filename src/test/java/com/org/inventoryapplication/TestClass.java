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
        core.addItem("A-111-222-332", "name", "10.50");
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
}
