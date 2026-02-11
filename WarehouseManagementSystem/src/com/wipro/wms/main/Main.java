package com.wipro.wms.main;

import java.util.ArrayList;
import com.wipro.wms.entity.*;
import com.wipro.wms.service.WarehouseService;
import com.wipro.wms.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("I001", "LED TV", "Electronics", 50));
        items.add(new Item("I002", "Refrigerator", "Appliances", 20));
        
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("L001", "Rack A - Shelf 1", 100, 30));
        locations.add(new Location("L002", "Rack B - Shelf 2", 80, 10));
        
        ArrayList<WarehouseTransaction> transactions = new ArrayList<>();
        
        WarehouseService service = new WarehouseService(items, locations, transactions);
        
        try {
           
            WarehouseTransaction t1 = service.inbound("I001", "L001", 10);
            System.out.println("Inbound Success. Transaction ID: " + t1.getTransactionId());
           
            WarehouseTransaction t2 = service.outbound("I002", "L002", 5);
            System.out.println("Outbound Success. Transaction ID: " + t2.getTransactionId());
            
            System.out.println("--- Stock Summary ---");
            service.printStockSummary();
            System.out.println("--- Location Summary ---");
            service.printLocationSummary();
            
        } catch (InvalidItemException iie) {
            System.out.println(iie);
        } catch (InvalidLocationException ile) {
            System.out.println(ile);
        } catch (InsufficientStockException ise) {
            System.out.println(ise);
        } catch (TransactionException te) {
            System.out.println(te);
        } catch (Exception ex) {
            System.out.println("Unexpected Error: " + ex);
        }
    }
}

