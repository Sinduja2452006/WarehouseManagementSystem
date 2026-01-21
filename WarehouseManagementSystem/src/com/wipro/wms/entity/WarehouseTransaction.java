package com.wipro.wms.entity;



public class WarehouseTransaction {
    private String transactionId;      
    private String itemId;            
    private String locationId;        
    private String transactionType;   
    private int quantity;            
    private String date;             
    public WarehouseTransaction(String transactionId, String itemId, String locationId, 
                               String transactionType, int quantity, String date) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.locationId = locationId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
    }

    public String getTransactionId() { 
    	return transactionId; 
    	}
    public String getItemId() {
    	return itemId; 
    	}
    public String getLocationId() {
    	return locationId; 
    	}
    public String getTransactionType() { 
    	return transactionType; 
    	}
    public int getQuantity() {
    	return quantity; 
    	}
    public String getDate() {
    	return date;
    	}
}
