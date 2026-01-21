package com.wipro.wms.service;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wipro.wms.entity.*;
import com.wipro.wms.util.*;

public class WarehouseService {
    private ArrayList<Item> items;
    private ArrayList<Location> locations;
    private ArrayList<WarehouseTransaction> transactions;

    public WarehouseService(ArrayList<Item> items, ArrayList<Location> locations, 
                           ArrayList<WarehouseTransaction> transactions) {
        this.items = items;
        this.locations = locations;
        this.transactions = transactions;
    }

    public boolean validateItem(String itemId) throws InvalidItemException {
        Item item = findItem(itemId);
        if (item == null) {
            throw new InvalidItemException();
        }
        return true;
    }
 
    public boolean validateLocation(String locationId) throws InvalidLocationException {
        Location loc = findLocation(locationId);
        if (loc == null) {
            throw new InvalidLocationException();
        }
        return true;
    }

    public WarehouseTransaction inbound(String itemId, String locationId, int qty) 
            throws InvalidItemException, InvalidLocationException, 
                   InsufficientStockException, TransactionException {
        
        if (qty <= 0) throw new TransactionException();
        
        validateItem(itemId);
        validateLocation(locationId);
        
        Item item = findItem(itemId);
        Location loc = findLocation(locationId);
        
        if (loc.getCurrentLoad() + qty > loc.getCapacity()) {
            throw new InsufficientStockException();
        }
        
        String transId = "T" + System.currentTimeMillis();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        WarehouseTransaction trans = new WarehouseTransaction(transId, itemId, locationId, "INBOUND", qty, date);
        transactions.add(trans);
        
        item.setTotalQuantity(item.getTotalQuantity() + qty);
        loc.setCurrentLoad(loc.getCurrentLoad() + qty);
        
        return trans;
    }

    public WarehouseTransaction outbound(String itemId, String locationId, int qty) 
            throws InvalidItemException, InvalidLocationException, 
                   InsufficientStockException, TransactionException {
        
        if (qty <= 0) throw new TransactionException();
        
        validateItem(itemId);
        validateLocation(locationId);
        
        Item item = findItem(itemId);
        Location loc = findLocation(locationId);
        
        if (loc.getCurrentLoad() < qty) {
            throw new InsufficientStockException();
        }
        
        String transId = "T" + System.currentTimeMillis();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        WarehouseTransaction trans = new WarehouseTransaction(transId, itemId, locationId, "OUTBOUND", qty, date);
        transactions.add(trans);
        
        item.setTotalQuantity(item.getTotalQuantity() - qty);
        loc.setCurrentLoad(loc.getCurrentLoad() - qty);
        
        return trans;
    }

    public void printStockSummary() {
        System.out.println("--- Stock Summary ---");
        for (Item i : items) {
            System.out.println(i);
        }
    }

    public void printLocationSummary() {
        System.out.println("--- Location Summary ---");
        for (Location l : locations) {
            System.out.println(l);
        }
    }

    
    public void printItemHistory(String itemId) {
        System.out.println("--- Item History for " + itemId + " ---");
        for (WarehouseTransaction t : transactions) {
            if (t.getItemId().equals(itemId)) {
                System.out.println(t.getTransactionId() + " | " + t.getTransactionType() + 
                                 " | " + t.getQuantity() + " | " + t.getDate());
            }
        }
    }

  
    private Item findItem(String id) {
        for (Item i : items) {
            if (i.getItemId().equals(id)) return i;
        }
        return null;
    }

    private Location findLocation(String id) {
        for (Location l : locations) {
            if (l.getLocationId().equals(id)) return l;
        }
        return null;
    }
}
