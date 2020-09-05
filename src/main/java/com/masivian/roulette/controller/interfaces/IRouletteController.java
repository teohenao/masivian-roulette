package com.masivian.roulette.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.masivian.roulette.util.dto.BetDTO;
import com.masivian.roulette.util.dto.Response;

public interface IRouletteController {
	
	public ResponseEntity<Response> createRoulette();
	
	public ResponseEntity<Response> getAllRoulettes();
	
	public ResponseEntity<Response> openRoulette(String id);
	
	public ResponseEntity<Response> wager(String user,BetDTO bet,String id);
	
	public ResponseEntity<Response> closeRoulette(String id);
	
}
