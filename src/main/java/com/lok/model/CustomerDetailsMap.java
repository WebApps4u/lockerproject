package com.lok.model;

public enum CustomerDetailsMap {

	CUSTOMERID("CUSTOMERID"),
	AADHAR_FILE("AADHARPATH"),
	FIRSTNAME("FIRSTNAME"),
	PP_FILE("PPPATH"),
	LASTNAME("LASTNAME"),
	EMAILID("EMAILID"),
	PHOTO_FILE("PHOTOPATH"),
	SIGN_FILE("SIGNPATH"),
	DL_FILE("DLICENSEPATH"),
	ADDRESS1("ADDRESS1"),
	ADDRESS2("ADDRESS2"),
	CITY("CITY"),
	STATE("STATE"),
	COUNTRY("COUNTRY"),
	PINCODE("PINCODE"),
	LANDMARK("LANDMARK"),
	MOBILENUM("MOBILENUM");
	
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
