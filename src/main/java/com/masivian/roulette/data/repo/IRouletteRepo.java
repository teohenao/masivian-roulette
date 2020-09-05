package com.masivian.roulette.data.repo;

import org.springframework.data.repository.CrudRepository;

import com.masivian.roulette.data.model.Roulette;

public interface IRouletteRepo extends CrudRepository<Roulette, String> {
	
	
}
