package com.wipro.wms.entity;

public class Item {
    private String itemId;         
    private String itemName;      
    private String category;       
    private int totalQuantity;     

    public Item(String itemId, String itemName, String category, int totalQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.totalQuantity = totalQuantity;
    }

    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public String getCategory() { return category; }
    public int getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(int qty) { this.totalQuantity = qty; }

    public String toString() {
        return "Item: " + itemId + " - " + itemName + " (" + category + "), Qty: " + totalQuantity;
    }
}
