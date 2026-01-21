package com.wipro.wms.entity;

public class Location {
    
    private String locationId;        
    private String description;       
    private int capacity;             
    private int currentLoad;          

    public Location(String locationId, String description, int capacity, int currentLoad) {
        this.locationId = locationId;
        this.description = description;
        this.capacity = capacity;
        this.currentLoad = currentLoad;
    }

    public String getLocationId() { 
    	return locationId; 
    	}
    public String getDescription() {
    	return description; 
    	}
    public int getCapacity() { 
    	return capacity; 
    	}
    public int getCurrentLoad() {
    	return currentLoad; 
    	}
    
    public void setCurrentLoad(int load) { 
    	this.currentLoad = load; 
    	}

    public String toString() {
        return "Location: " + locationId + " (" + description + "), Load: " + currentLoad + "/" + capacity;
    }
}