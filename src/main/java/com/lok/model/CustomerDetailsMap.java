package com.lok.model;

public enum CustomerDetailsMap {

	AADHAR_FILE("AADHARPATH"),
	FIRSTNAME("FIRSTNAME"),
	PP_FILE("PPPATH"),
	LASTNAME("LASTNAME"),
	EMAILID("EMAILID"),
	PHOTO_FILE("PHOTOPATH"),
	ADDRESS1("ADDRESS1");

    private final String description;                                                                                               

    private CustomerDetailsMap(String description) {                                                                                        
        this.description = description;                                                                                             
    }                                                                                                                               

    public String getCode() {
        return this.toString();                                                                                                     
    }                                                                                                                               

    public String getDescription() {
        return this.description;                                                                                                    
    }                                                                                                                               

    public static String getDescriptionByCode(String code) {
        try {
            return valueOf(code).getDescription();
        } catch (IllegalArgumentException noSuchCode) {
            return null;
        }
    }
}
