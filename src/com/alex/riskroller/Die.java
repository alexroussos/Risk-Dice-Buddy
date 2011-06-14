package com.alex.riskroller;

import java.util.Random;

public class Die  implements Comparable<Die> {
	private int numSides;
	private boolean enabled = true;
	private Random r = new Random();
	public int value = 0;
	public boolean won = false;
	
	public Die(int sides) {
		numSides = sides;
	}
	
	public int roll() {
		if (enabled) {
			won = false;
			value = r.nextInt(numSides) + 1;
			return value;
		}
		return 0;
	}

	public void disable() {
		enabled = false;
		won = false;
	}
	
	public void enable() {
		enabled = true;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public int compareTo(Die another) {
		return enabled ? this.value - another.value : Integer.MIN_VALUE;
	}
}
