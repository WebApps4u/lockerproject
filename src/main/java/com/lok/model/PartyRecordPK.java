package com.lok.model;

import java.io.Serializable;

public class PartyRecordPK implements Serializable{
	
	private String KNO;
	private String LSNO;
	
	public PartyRecordPK(){}
	
	/**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((KNO == null) ? 0 : KNO.hashCode());
        result = prime * result + ((LSNO == null) ? 0 : LSNO.hashCode());
        return result;
    }
     
    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	 if(obj instanceof PartyRecordPK){
    		 PartyRecordPK partyPK = (PartyRecordPK) obj;
  
             if(!partyPK.getKNO().equals(KNO)){
                 return false;
             }
  
             if(!partyPK.getLSNO().equals(LSNO)){
                 return false;
             }
  
             return true;
         }
  
         return false;

    }

	/**
	 * @return the kNO
	 */
	public String getKNO() {
		return KNO;
	}

	/**
	 * @param kNO the kNO to set
	 */
	public void setKNO(String kNO) {
		KNO = kNO;
	}

	/**
	 * @return the lSNO
	 */
	public String getLSNO() {
		return LSNO;
	}

	/**
	 * @param lSNO the lSNO to set
	 */
	public void setLSNO(String lSNO) {
		LSNO = lSNO;
	}

}
