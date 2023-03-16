import org.techt.model.InventoryItem;
import org.junit.Assert;
import org.junit.Test;
import org.techt.service.InventoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryServiceTest {
    @Test
    public void testAddItem(){
        InventoryService service = new InventoryService();
        String name = "name";
        String description = "description";
        int quantity = 2;
        String name2 = "name2";
        String description2 = "description2";
        service.addItem(name,description, quantity);
        service.addItem(name,description, quantity);
        service.addItem(name2,description2, quantity);
        List<InventoryItem> savedItemsWithName = service.getInventory().stream().filter(x->
                x.getName().equals(name)
                        && x.getDescription().equals(description)
                        && x.getQuantity() == quantity
        ).collect(Collectors.toList());
        List<InventoryItem> savedItemsWithName2 = service.getInventory().stream().filter(x->
                x.getName().equals(name2)
                        && x.getDescription().equals(description2)
                        && x.getQuantity() == quantity
        ).collect(Collectors.toList());
        Assert.assertEquals(3, service.getInventory().size());
        Assert.assertEquals(2, savedItemsWithName.size());
        Assert.assertEquals(1, savedItemsWithName2.size());
    }
    @Test
    public void testHasItem(){
        InventoryService service = new InventoryService();
        String name = "itemTest";
        String description = "item test description";
        int quantity = 2;
        service.addItem(name,description, quantity);
        InventoryItem savedItem = service.getInventory().stream().filter(x->x.getName().equals(name)).findAny().get();
        Assert.assertEquals(true, service.hasItem(savedItem.getId()));
        service.removeItem(savedItem.getId());
        Assert.assertEquals(false, service.hasItem(savedItem.getId()));
    }
    @Test
    public void testRemoveItem(){
        InventoryService service = new InventoryService();
        String removedName = "removedName";
        String notRemovedName = "notRemovedName";
        String description = "item test description";
        int quantity = 2;
        service.addItem(removedName,description, quantity);
        service.addItem(notRemovedName,description, quantity);
        InventoryItem savedItem = service.getInventory().stream().filter(x->x.getName().equals(removedName)).findAny().get();
        service.removeItem(savedItem.getId());
        // check if item removed successfully
        Optional<InventoryItem> removedItem = service.getInventory().stream().filter(x->x.getName().equals(removedName)).findFirst();
        Assert.assertEquals(Optional.empty(), removedItem);
        // check if other items effected from removal of item
        InventoryItem notRemovedItem = service.getInventory().stream().filter(x->x.getName().equals(notRemovedName)).findAny().orElseGet(null);
        Assert.assertNotEquals(null, notRemovedItem);
    }
    @Test
    public void testUpdateItem(){
        InventoryService service = new InventoryService();
        String nameToBeUpdated = "nameToBeUpdated";
        String updatedName = "updatedName";
        String staticName = "staticName";
        String description = "item test description";
        int quantity = 2;
        String updatedDescription = "updatedDescription";
        int updatedQuantity = 5;
        service.addItem(nameToBeUpdated,description, quantity);
        service.addItem(staticName,description, quantity);
        InventoryItem savedStaticItem = service.getInventory().stream().filter(x->x.getName().equals(staticName)).findAny().get();
        InventoryItem savedUpdateItem = service.getInventory().stream().filter(x->x.getName().equals(nameToBeUpdated)).findAny().get();
        service.updateItem(savedUpdateItem.getId(), updatedName, updatedDescription, updatedQuantity);
        InventoryItem updatedItem = service.getItem(savedUpdateItem.getId());
        InventoryItem staticItem = service.getItem(savedStaticItem.getId());
        //Check if item fields updated
        Assert.assertEquals(updatedName, updatedItem.getName());
        Assert.assertEquals(updatedDescription, updatedItem.getDescription());
        Assert.assertEquals(updatedQuantity, updatedItem.getQuantity());
        //Check if other items fields are effected
        Assert.assertEquals(staticName, staticItem.getName());
        Assert.assertEquals(description, staticItem.getDescription());
        Assert.assertEquals(quantity, staticItem.getQuantity());
    }

    @Test
    public void testGetItem(){
        InventoryService service = new InventoryService();
        String name = "itemTest";
        String description = "item test description";
        int quantity = 2;
        service.addItem(name,description,quantity);
        InventoryItem savedItem = service.getInventory().stream().filter(x->x.getName().equals(name)).findAny().get();
        InventoryItem foundItem = service.getItem(savedItem.getId());
        Assert.assertEquals(name, foundItem.getName());
        Assert.assertEquals(description, foundItem.getDescription());
        Assert.assertEquals(quantity, foundItem.getQuantity());
    }
    @Test
    public void testGetItems(){
        InventoryService service = new InventoryService();
        service.addItem("a","a",1);
        service.addItem("a","a",1);
        service.addItem("a","a",1);
        Assert.assertEquals(3, service.getInventory().size());
    }
}
