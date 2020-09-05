package com.masivian.roulette.data.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masivian.roulette.util.dto.BetDTO;

@RedisHash("roulette")
public class Roulette {

	@Id
	private String id;
	
	@JsonProperty("state")
	private String state;
		
	@JsonProperty("bets")
	private List<BetDTO> bets;
	
	@JsonProperty("lastResults")
	private String lastResults;
	
	public Roulette() {
		super();
		this.bets = new ArrayList<>();
	}

	public Roulette(String id, String state, List<BetDTO> bets) {
		super();
		this.id = id;
		this.state = state;
		this.bets = bets;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<BetDTO> getBets() {
		return bets;
	}

	public void setBets(List<BetDTO> bets) {
		this.bets = bets;
	}
	
	public void addBet(BetDTO bet) {
		this.bets.add(bet);
	}

	public String getLastResults() {
		return lastResults;
	}

	public void setLastResults(String lastResults) {
		this.lastResults = lastResults;
	}
	
}
