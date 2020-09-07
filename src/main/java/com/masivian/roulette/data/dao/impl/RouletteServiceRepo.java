package com.masivian.roulette.data.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masivian.roulette.data.dao.interfaces.IRouletteServiceRepo;
import com.masivian.roulette.data.model.Roulette;
import com.masivian.roulette.data.repo.IRouletteRepo;
import com.masivian.roulette.util.Constants;
import com.masivian.roulette.util.PlayLogic;
import com.masivian.roulette.util.dto.BetDTO;
import com.masivian.roulette.util.dto.RouletteDTO;

@Service
@Transactional
public class RouletteServiceRepo implements IRouletteServiceRepo {

	@Autowired
	private IRouletteRepo rouletteRepo;

	@Override
	public List<RouletteDTO> getRoulettes() {
		List<RouletteDTO> roulettes = new ArrayList<>();
		rouletteRepo.findAll().forEach(r -> {
			RouletteDTO roulette = new RouletteDTO(r);
			roulettes.add(roulette);
		});

		return roulettes;
	}

	@Override
	public String createRoulette() {
		Roulette roulette = new Roulette();
		roulette.setState(Constants.ROULETTE_CLOSED);
		roulette.setLastResults(Constants.RESULTS_EMPTY);
		
		return rouletteRepo.save(roulette).getId();
	}

	@Override
	public Optional<Roulette> openRoulette(String id) {
		Optional<Roulette> roulette = rouletteRepo.findById(id);
		roulette.ifPresent(r -> {
			r.setState(Constants.ROULETTE_OPEN);
			rouletteRepo.save(r);
		});

		return roulette;
	}

	@Override
	public Optional<Roulette> wager(BetDTO bet, String id) {
		Optional<Roulette> roulette = rouletteRepo.findById(id);
		if (roulette.isPresent()) {
			Roulette r = roulette.get();
			if (r.getState().equals(Constants.ROULETTE_OPEN)) {
				bet.setResult(Constants.PENDING);
				r.addBet(bet);
				rouletteRepo.save(r);
			} else
				return null;
		}
		
		return roulette;
	}

	@Override
	public RouletteDTO closeRoulette(String id) {
		Optional<Roulette> roulette = rouletteRepo.findById(id);
		if (roulette.isPresent()) {
			Roulette r = roulette.get();
			int number = (int) (Math.random() * 36);
			String color = PlayLogic.ROULETTE.get(number);
			List<BetDTO> bets = new ArrayList<>();
			for (BetDTO bet : r.getBets()) {
				Boolean results = PlayLogic.isWinner(number, color, bet.getBetValue());
				bets.add(new BetDTO(bet.getUser(), bet.getBetValue(), bet.getAmount(),
						results ? Constants.WINNER : Constants.LOSER));
			}
			r.setState(Constants.ROULETTE_CLOSED);
			r.setLastResults(number + " " + color);
			r.setBets(new ArrayList<>());
			rouletteRepo.save(r);
			r.setBets(bets);
			
			return new RouletteDTO(r);
		} else
			
			return null;

	}
}