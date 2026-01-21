package com.wipro.wms.util;

public class InsufficientStockException extends Exception {
    public String toString() {
        return "Not enough stock";  
    }
}
