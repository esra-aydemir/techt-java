package org.techt.service;

import org.techt.model.Inventory;
import org.techt.model.InventoryItem;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryService {
    private Inventory inventory = new Inventory();

    /**
     * Adds a new item to the inventory with the specified name, description, and quantity.
     * @param name
     * @param description
     * @param quantity
     */
    public void addItem(String name, String description, int quantity){
        InventoryItem item = new InventoryItem(name, description, quantity);
        inventory.getItems().put(item.getId(), item);
        System.out.println("Successfully added item with id: "+item.getId());
    }

    /**
     * Removes the item with the specified identifier from the inventory.
     * @param itemId id of the item to be deleted.
     */
    public void removeItem(String itemId){
        InventoryItem removedItem = inventory.getItems().remove(itemId);
        if(removedItem == null){
            System.err.println("Could not remove item. Item with id "+itemId+" not found.");
        }else{
            System.out.println("Successfully deleted item with id: "+itemId);
        }
    }

    /**
     * Updates the item with the specified identifier with the new name, description, and quantity.
     * @param itemId    id of existing item
     * @param name  new name
     * @param description   new description
     * @param quantity  new quantity
     */
    public void updateItem(String itemId, String name, String description, int quantity){
        // TODO: 16.03.2023 take system interaction to main
        InventoryItem item = inventory.getItems().get(itemId);
        if(item == null){
            System.err.println("Could not update item. Item with id "+itemId+" not found.");
        }else{
            item.setName(name);
            item.setDescription(description);
            item.setQuantity(quantity);
            System.out.println("Successfully updated item with id "+itemId);
        }
    }

    /**
     *  Returns the item with the specified identifier.
     * @param itemId id of the item
     * @return
     */
    public InventoryItem getItem(String itemId){
        // TODO: 16.03.2023 handle null case on main
        return inventory.getItems().get(itemId);
    }

    /**
     * @return list of all items in the inventory.
     */
    public List<InventoryItem> getInventory(){
        return inventory.getItems().values().stream().collect(Collectors.toList());
    }
    /**
     * @return if the item is in the inventory.
     */
    public boolean hasItem(String itemId){
        return inventory.getItems().containsKey(itemId);
    }
}
