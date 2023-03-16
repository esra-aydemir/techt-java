package org.techt;

import helpers.Operation;
import model.InventoryItem;
import service.InventoryService;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static final Integer EXIT = -1;
    static Scanner scanner = new Scanner(System.in);
    static InventoryService service = new InventoryService();
    public static void main(String[] args) {
        System.out.println("Welcome to the inventory app!");
        System.out.println("(You can exit the system with command "+EXIT+")");
        doOperation();
        scanner.close();
    }
    public static void doOperation(){
        Operation operation = null;
        while(operation == null){
            int operationNumber = getOperationNumber();
            if(operationNumber == -1){
                return;
            }
            if(Operation.ADD.value == operationNumber){
                operateAdd();
            }else if(Operation.REMOVE.value == operationNumber){
                operateRemove();
            }else if(Operation.UPDATE.value == operationNumber){
                operateUpdate();
            }else if(Operation.VIEW.value == operationNumber){
                operateView();
            }else if(Operation.VIEW_ALL.value == operationNumber){
                operateViewAll();
            }else{
                System.err.println("Input a valid operation number. Or enter "+EXIT+" to exit. (hint: 1)");
            }
        }
    }

    private static void operateAdd() {
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        while (name.isEmpty()){
            name = scanner.nextLine();
            System.err.println("Item name can not be empty. Enter item name:");
        }
        System.out.println("Enter item description:");
        String description = scanner.nextLine();
        while (description.isEmpty()){
            description = scanner.nextLine();
            System.err.println("Item description can not be empty. Enter item description:");
        }
        System.out.println("Enter item quantity:");
        String quantityInput;
        boolean parsedQuantity = false;
        Integer quantity = 0;
        while(!parsedQuantity){
            quantityInput = scanner.nextLine();
            if(!quantityInput.isEmpty()) {
                quantity = readIntInput(quantityInput);
                if(quantity == null || quantity < 0){
                    System.err.println("Enter a valid positive number as item quantity:");
                }else{
                    parsedQuantity = true;
                }
            }else{
                parsedQuantity = true;
            }
        }
        service.addItem(name, description, quantity);
    }

    private static Integer readIntInput(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            return null;
        }
    }
    private static void operateRemove() {
        System.out.println("Enter item id to remove.");
        String id = scanner.nextLine();
        service.removeItem(id);
    }
    private static void operateUpdate() {
        System.out.println("Enter item id to update.");
        String id = scanner.nextLine();
        if(!service.hasItem(id)){
            System.err.println("Could not update item. Item with id "+id+" not found.");
        }else{
            InventoryItem existingItem = service.getItem(id);
            System.out.println("Enter new item name: (press enter to keep existing value)");
            String newName = scanner.nextLine();
            if(newName.isEmpty()){
                newName = existingItem.getName();
            }
            System.out.println("Enter new item description:  (press enter to keep existing value)");
            String newDescription = scanner.nextLine();
            if(newDescription.isEmpty()){
                newDescription = existingItem.getDescription();
            }
            System.out.println("Enter new item quantity:  (press enter to keep existing value)");
            String newQuantityInput;
            Integer newQuantity = existingItem.getQuantity();
            boolean parsedQuantity = false;
            while(!parsedQuantity){
                try{
                    newQuantityInput = scanner.nextLine();
                    if(!newQuantityInput.isEmpty()) {
                        newQuantity = readIntInput(newQuantityInput);
                        if(newQuantity == null || newQuantity<0){
                            System.out.println("Enter a valid positive number as item quantity: (press enter to keep existing value)");
                        }else{
                            parsedQuantity = true;
                        }
                    }else{
                        parsedQuantity = true;
                    }
                }catch (NumberFormatException e){
                    System.out.println("Enter a valid positive number as item quantity: (press enter to keep existing value)");
                }
            }
            service.updateItem(id, newName, newDescription, newQuantity);
        }
    }
    private static void operateView() {
        System.out.println("Enter item id to view.");
        String id = scanner.nextLine();
        System.out.println(service.getItem(id).toString());
    }
    private static void operateViewAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{");
        sb.append("items=[\n");
        service.getInventory().forEach(item -> sb.append(item.toString()+"\n"));
        sb.append("]\n}");
        System.out.println(sb);
    }

    public static int getOperationNumber(){
        System.out.println("\nEnter the number of your operation:");
        System.out.println("" + Operation.ADD.value + " - Add Item\n" +
                Operation.REMOVE.value + " - Remove Item\n" +
                Operation.UPDATE.value + " - Update Item\n" +
                Operation.VIEW.value + " - View Item\n" +
                Operation.VIEW_ALL.value + " - View Inventory (All Items)");
        String input = scanner.nextLine();
        Integer operationNumber = null;
        while (operationNumber == null){
            operationNumber = readIntInput(input);
            if(operationNumber == null){
                input = scanner.nextLine();
                System.err.println("Input a valid operation number. Or enter "+EXIT+" to exit. (hint: 1)");
            }
        }
        return operationNumber;
    }
}