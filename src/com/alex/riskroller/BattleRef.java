package com.alex.riskroller;

public class BattleRef {

	private DiceSet attDice, defDice;
	
	public BattleRef() {
	}	
	
	public BattleRef(DiceSet att, DiceSet def) {
		attDice = att;
		defDice = def;
	}
	
	public BattleResult calculateBattle(int numAtt, int numDef) {
		if (attDice == null || defDice == null) {
			return null;
		}
		
		attDice.setEnabled(numAtt); // ok if numToEnable > numDice, will enable max
		defDice.setEnabled(numDef);
		
    	return calculateBattle(attDice, defDice);
	}
	
	public BattleResult calculateBattle(DiceSet att, DiceSet def) {
    	BattleResult result = new BattleResult();

    	att.rollAndSortEnabledDice();
    	def.rollAndSortEnabledDice();
    	
    	if (att == null || def == null) {
    		return result;
    	}

    	// check if highest die is enabled, since we allow player to disable all dice.
    	if (att.is1stEnabled() && def.is1stEnabled()) {
	    	boolean firstOutcome = runBattle(att.getHighest(), def.getHighest(), result);
			att.setHighestOutcome(firstOutcome);
			def.setHighestOutcome(!firstOutcome);
    	}
    	
    	if (att.is2ndEnabled() && def.is2ndEnabled()) {
    		boolean secondOutcome = runBattle(att.get2ndHighest(), def.get2ndHighest(), result);
    		att.set2ndHighestOutcome(secondOutcome);
    		def.set2ndHighestOutcome(!secondOutcome);
    	}
    	
    	return result;
    }
    
    private boolean runBattle(int attVal, int defVal, BattleResult result) {
		if(attVal > defVal) {
    		result.defLost();
    		return true;
		} else {
    		result.attLost();
    		return false;
	    }
    }
}
