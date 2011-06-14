package com.alex.riskroller;

public class BattleResult {
	public int defLost = 0;
	public int attLost = 0;
	
	public BattleResult() {
		
	}
	
	public void attLost() {
		attLost++;
	}
	
	public void defLost() {
		defLost++;
	}
}
