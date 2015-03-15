package com.lok.model;

/**
 * All the update/add/delete operations 
 * will return this bean
 * @author USER
 *
 */
public class ReturnMessage {

	private String errMsg;
	private String successMsg;
	private String status;
	
	//Set of statuses of all return messages
	public enum StatusOfMessage{
		FAILURE,SUCCESS
	}
	
	public enum SuccessSet{
		SAVED_SUCCESS("Saved successfully "),
		UPDATE_SUCCESS("Updated successfully "),
		DELETE_SUCCSS("Deleted successfully "),
		INSERT_SUCCESS("Created successfully ");
		
		private String success;
		private SuccessSet(String success){
			this.success = success;
		}
		
		public String toString(){
			return success;
		}
	}
	
	public enum ErrorSet{
		UNKNOWN_ERROR("Encountered an unknown error, please send mail to deepansh1987@gmail.com");
		
		private String error;
		private ErrorSet(String error){
			this.error = error;
		}
		
		public String toString(){
			return error;
		}
	}
	
	public ReturnMessage(){
		
	}
	
	public ReturnMessage(String errMsg,String successMsg,String status){
		this.errMsg = errMsg;
		this.successMsg = successMsg;
		this.status = status;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.status = StatusOfMessage.FAILURE.toString();
		this.errMsg = errMsg;
	}

	/**
	 * @return the successMsg
	 */
	public String getSuccessMsg() {
		return successMsg;
	}

	/**
	 * @param successMsg the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.status = StatusOfMessage.SUCCESS.toString();
		this.successMsg = successMsg;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusOfMessage status) {
		this.status = status.toString();
	}
	
	/**
	 * Return the default error message
	 */
	public ReturnMessage setDefaultErr(){
		
		setErrMsg( ErrorSet.UNKNOWN_ERROR.toString());
		
		return this;
	}
}
