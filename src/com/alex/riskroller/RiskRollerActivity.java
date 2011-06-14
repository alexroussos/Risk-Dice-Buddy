package com.alex.riskroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

// TODO later - sound effects
// TODO later - shake
// TODO later - tab layout for single/multi
// TODO multimode button doesnt show up on small lores screen
public class RiskRollerActivity extends Activity {
	
	public static final int DICE_NUM_SIDES = 6;
	
	private OnClickListener diceListener = new OnClickListener() {
        public void onClick(View v) {
        	((DieButton)v).toggle();
        }
    };
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
     
        final DieButton dieA1 = (DieButton) findViewById(R.id.dieA1);
        dieA1.setOnClickListener(diceListener);        
        dieA1.isAttacker = true;
        final DieButton dieA2 = (DieButton) findViewById(R.id.dieA2);
        dieA2.setOnClickListener(diceListener);      
        dieA2.isAttacker = true;
        final DieButton dieA3 = (DieButton) findViewById(R.id.dieA3);
        dieA3.setOnClickListener(diceListener);      
        dieA3.isAttacker = true;
        dieA3.getDie().disable(); // so it shows 'click to enable' tip
        final DieButton dieD1 = (DieButton) findViewById(R.id.dieD1);
        dieD1.setOnClickListener(diceListener);      
        dieD1.isAttacker = false;
        final DieButton dieD2 = (DieButton) findViewById(R.id.dieD2);
        dieD2.setOnClickListener(diceListener);     
        dieD2.isAttacker = false;
        
        final DiceSet attDice = new DiceSet(dieA1.getDie(), dieA2.getDie(), dieA3.getDie());
        final DiceSet defDice = new DiceSet(dieD1.getDie(), dieD2.getDie());
        
        final BattleRef ref = new BattleRef();

        final Button rollDiceButton = (Button) findViewById(R.id.rolldice);
        rollDiceButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	BattleResult result = ref.calculateBattle(attDice, defDice); // dice selected to determine num att/def
            	TextView output = (TextView) findViewById(R.id.battleoutput);
            	output.setText("Attacker lost " + result.attLost + "\t\t\tDefender lost " + result.defLost);         		
            }
        });
        
        final Button multiModeButton = (Button) findViewById(R.id.multi);
        multiModeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
		        Intent myIntent = new Intent(v.getContext(), MultiBattleActivity.class);
		        startActivityForResult(myIntent, 0);
            }
        });
    }
}