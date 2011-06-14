package com.alex.riskroller;

import java.util.Arrays;
import java.util.Comparator;

public class DiceSet {
	private Die dice[];
	
	public DiceSet(Die...dies) {
		dice = dies;
	}
	
	public DiceSet(int numDice) {
		dice = new Die[numDice];
		for (int i = 0; i < numDice; i++) {
			dice[i] = new Die(RiskRollerActivity.DICE_NUM_SIDES);
		}
	}

	public int getHighest() {
		return dice[0].value;
	}
	
	public int get2ndHighest() {
		return dice[1].isEnabled() ? dice[1].value : 0;
	}
	
	public void setHighestOutcome(boolean outcome) {
		dice[0].won = outcome;
	}	
	
	public void set2ndHighestOutcome(boolean outcome) {
		dice[1].won = outcome;
	}

	public boolean is1stEnabled() {
		return dice[0].isEnabled();
	}
	
	public boolean is2ndEnabled() {
		return dice[1].isEnabled();
	}
	
    public void enableRollAndSortEnabledDice(int numToEnable) {
    	setEnabled(numToEnable);
    	rollAndSortEnabledDice();
    }
	
	public void setEnabled(int numToEnable) {
		int numEnabled = 0;
		for (Die die : dice) {
			if (numEnabled < numToEnable) {
				die.enable();
				numEnabled++;
			} else {
				die.disable();
			}
		}
	}
	
	// Ensure dice can never be rolled without being sorted
    public void rollAndSortEnabledDice() {
    	for (Die die : dice) {
    		if (die.isEnabled()) {
    			die.roll();
    		}
    	}
    	Arrays.sort(dice, new DieComparator());
    }
    
    class DieComparator implements Comparator<Die> {
    	public int compare(Die die1, Die die2) {
    		if (!die1.isEnabled())
    			return 1;
    		if (!die2.isEnabled())
    			return -1;
    		return die2.value - die1.value;
    	}
    }
}
