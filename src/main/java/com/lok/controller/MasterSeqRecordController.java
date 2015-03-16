package com.lok.controller;


import com.googlecode.genericdao.search.Search;
import com.lok.model.Lok_master_seq;
import com.lok.service.MasterSeqService;

public class MasterSeqRecordController {

	
	private static MasterSeqService masterSeqService;
	
	static {
		
		//anonymous class to generate master service
		masterSeqService = new BaseController<MasterSeqService>(MasterSeqService.class).getService();    //only one time generate the service
	}
	
	/**
	 * Pass the next seq (or id) for the given table
	 */
	public static String generateNextId(Class<?> entityClass) throws IllegalArgumentException{
		
		String nextId = "";
		
		Search searchSeq = new Search();
		
		//all table names in the master seq is of lower case
		//get the table name and lower it to get the seq id and initial
		searchSeq.addFilterEqual("object_type", entityClass.getSimpleName().toLowerCase());
		
		//check for the size and get the first record, as there can be at max one record
		//object_type is unique in the table
		Lok_master_seq lastSeqRecord = (Lok_master_seq)((masterSeqService.search(searchSeq).size()!=0)?masterSeqService.search(searchSeq).get(0):null);
		
		if(lastSeqRecord == null){
			//there is no entry in the table for this class
			throw new IllegalArgumentException();
		}
		//Create next seq
		Long lastSeq = lastSeqRecord.getLast_seq();        //1233
		
		nextId = lastSeqRecord.getObject_intial() +  ++lastSeq;  // B-1234, updated seq
		
		//Update the seq table
		lastSeqRecord.setLast_seq(lastSeq);
		masterSeqService.save(lastSeqRecord);
		
		return nextId;
	}
	

}
