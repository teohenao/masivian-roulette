package com.masivian.roulette.util.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.masivian.roulette.util.Constants;

public class BetDTO {
	
	@JsonProperty("usuario")
	private String user;

	@JsonProperty("valor")
	private String betValue;
	
	@JsonProperty("cantidad")
	private Integer amount;
	
	@JsonProperty("resultado")
	private String result;

	public BetDTO(String user,String betValue, Integer amount,String result) {
		super();
		this.user = user;
		this.betValue = betValue;
		this.amount = amount;
		this.result = result;
	}
	
	
	@JsonIgnore
	public Boolean validBed() {
		return (0 < this.amount && this.amount <= 10000 && (this.betValue.equals(Constants.BLACK)
				|| this.betValue.equals(Constants.RED) || valueValid()));
	}

	private Boolean valueValid() {
		try {
			int parse = Integer.parseInt(this.betValue);
			return (0 <= parse && parse <= 36);
		} catch (Exception e) {
			return false;
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBetValue() {
		return betValue;
	}

	public void setBetValue(String betValue) {
		this.betValue = betValue;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}



}