package com.masivian.roulette.util;

import java.util.HashMap;

public class PlayLogic {
	
	public static final HashMap<Integer, String> ROULETTE = generateRoulette();

	public static HashMap<Integer, String> generateRoulette(){
		HashMap<Integer, String> roulette = new HashMap<>();
		roulette.put(0,Constants.GREEN);
		int change = 10;
		int color = 0;
		for (int i = 1,k=1; i <= 36; i++) {
			if(i == change && i < 33) {
				roulette.put(i,color == 0 ? Constants.RED : Constants.BLACK );
				change = 10 + change - k;
				k = k + 1;
			}else {
				roulette.put(i,color == 0 ? Constants.RED : Constants.BLACK );
				color = color == 0 ? 1 : 0;
			}
		}
		return roulette;
	}
	
	public static Boolean isWinner(int number,String color,String value){
		if(value.equals(color) || String.valueOf(number).equals(value)) {
			return true;
		}else 
			return false;
	}
}
