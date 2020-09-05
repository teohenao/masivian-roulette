package com.masivian.roulette.util.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masivian.roulette.data.model.Roulette;

public class RouletteDTO {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("estado")
	private String state;
	
	@JsonProperty("apuestas")
	private List<BetDTO> bets;
	
	@JsonProperty("ultimoResultados")
	private String lastResults;
	
	public RouletteDTO(Roulette roulette) {
		super();
		this.id = roulette.getId();
		this.state = roulette.getState();
		this.bets = roulette.getBets();
		this.lastResults = roulette.getLastResults();
	}

	public List<BetDTO> getBets() {
		return bets;
	}

	public void setBets(List<BetDTO> bets) {
		this.bets = bets;
	}

	public String getLastResults() {
		return lastResults;
	}

	public void setLastResults(String lastResults) {
		this.lastResults = lastResults;
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

	
}