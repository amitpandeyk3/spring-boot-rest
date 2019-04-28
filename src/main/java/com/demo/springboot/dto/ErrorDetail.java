package com.demo.springboot.dto;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

	private String title;
	private int status;
	private String detail;
	private Timestamp timeStamp;
	private String developerMessage;
	private Map<String, List<ValidationError>> errors = new HashMap<>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	public Map<String, List<ValidationError>> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}

	public static class ValidationError {
		private String code;
		private String message;
		public ValidationError(String code, String message) {
			super();
			this.code = code;
			this.message = message;
		}
		public String getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}		
				
	}
	
}
