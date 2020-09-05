package com.masivian.roulette.data.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.masivian.roulette.data.model.Roulette;
import com.masivian.roulette.util.dto.BetDTO;
import com.masivian.roulette.util.dto.RouletteDTO;

public interface IRouletteServiceRepo {
	
	public List<RouletteDTO> getRoulettes();

	public String createRoulette();
	
	public Optional<Roulette> openRoulette(String id);
	
	public Optional<Roulette> wager(BetDTO bet,String id);
	
	public RouletteDTO closeRoulette(String id);
	
}
