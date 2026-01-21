package com.wipro.wms.util;



public class TransactionException extends Exception {
    public String toString() {
        return "Transaction failed - invalid quantity or type";  
    }
}
