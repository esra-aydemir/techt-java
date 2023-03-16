package model;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, InventoryItem> items = new HashMap<>();

    public Inventory() {
    }

    public HashMap<String, InventoryItem> getItems() {
        return items;
    }
}
