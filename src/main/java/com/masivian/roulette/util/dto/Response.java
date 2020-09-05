package com.masivian.roulette.util.dto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;


public class Response {

	private String status;
	private String message;
	private HashMap<String,Object> payload;
	
	public Response(HttpStatus status, String message) {
		this.status = status.toString();
		this.message = message;
		this.payload = new HashMap<>();
	}
	
	public void addPayload(String field, Object value) {
		this.payload.put(field, value);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status.toString();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(HashMap<String,Object> payload) {
		this.payload = payload;
	}
	
}
