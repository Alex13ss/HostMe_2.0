package com.softserve.edu.model;

public enum SightseeingType {
	PARK("Park"), MUSEUM("Museum"), MONUMENT("Monument"), EXHIBITION("Exhibition"), THEATER("Theatre");
	private String value;
	
	SightseeingType(String value){
		this.value = value;
	}
	public String getValue() {
        return value;
    } 
    public void setValue(String value) {
        this.value = value;
    }  
}
