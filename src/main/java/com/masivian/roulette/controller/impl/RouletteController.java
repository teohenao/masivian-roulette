package com.masivian.roulette.controller.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.masivian.roulette.controller.interfaces.IRouletteController;
import com.masivian.roulette.data.dao.interfaces.IRouletteServiceRepo;
import com.masivian.roulette.data.model.Roulette;
import com.masivian.roulette.util.Constants;
import com.masivian.roulette.util.dto.BetDTO;
import com.masivian.roulette.util.dto.Response;
import com.masivian.roulette.util.dto.RouletteDTO;

@RestController
@RequestMapping(Constants.GET_API)
public class RouletteController implements IRouletteController {

	@Autowired
	private IRouletteServiceRepo rouletteService;

	@Override
	@GetMapping(Constants.GET_ALL_ROULETTES)
	public ResponseEntity<Response> getAllRoulettes() {
		Response response = new Response(HttpStatus.OK, Constants.RESPONSE_OK);
		response.addPayload("ruletas", rouletteService.getRoulettes());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@PostMapping(Constants.POST_ROULETTE)
	public ResponseEntity<Response> createRoulette() {
		Response response = new Response(HttpStatus.CREATED, Constants.CREATED_MESSAGE);
		response.addPayload("idRuleta", rouletteService.createRoulette());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	@PutMapping(Constants.PUT_ROULETTE_OPEN + "/{id}")
	public ResponseEntity<Response> openRoulette(@PathVariable("id") String id) {
		Optional<Roulette> roulette = rouletteService.openRoulette(id);
		if (!roulette.isPresent()) {
			Response response = new Response(HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MESSAGE);
			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		Response response = new Response(HttpStatus.OK, Constants.RESPONSE_OK);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@PutMapping(Constants.PUT_ROULETTE_WAGER + "/{id}")
	public ResponseEntity<Response> wager(@RequestHeader(value = "user") String user, @RequestBody BetDTO bet,
			@PathVariable("id") String id) {
		if (user != null && bet != null && bet.validBed()) {
			bet.setUser(user);
			Optional<Roulette> roulette = rouletteService.wager(bet, id);
			if (roulette == null) {
				Response response = new Response(HttpStatus.OK, Constants.BAD_ROULETTE_CLOSED);
				
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else if (roulette.isPresent()) {
				Response response = new Response(HttpStatus.OK, Constants.RESPONSE_OK);
				response.addPayload("ruleta", roulette.get());
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				Response response = new Response(HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MESSAGE);
				
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} else {
			Response response = new Response(HttpStatus.BAD_REQUEST, Constants.BAD_REQUEST_MESSAGE);
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@PutMapping(Constants.PUT_ROULETTE_CLOSE + "/{id}")
	public ResponseEntity<Response> closeRoulette(@PathVariable("id") String id) {
		RouletteDTO rouletteDTO = rouletteService.closeRoulette(id);

		if (rouletteDTO == null) {
			Response response = new Response(HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MESSAGE);
			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		Response response = new Response(HttpStatus.OK, Constants.RESPONSE_OK);
		response.addPayload("resultados", rouletteDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}