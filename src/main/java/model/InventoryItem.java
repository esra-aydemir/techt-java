package model;

import java.util.UUID;

public class InventoryItem {
    // A unique identifier for the item.
    String id;
    // The name of the item.
    String name;
    // A description of the item.
    String description;
    // The quantity of the item in the inventory.
    int quantity;

    public InventoryItem(String name, String description, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
