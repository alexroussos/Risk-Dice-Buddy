package com.alex.riskroller;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

// TODO later - when click an entry box, clear default value
public class MultiBattleActivity extends Activity {
	
	private BattleRef ref;
	private LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.multibattle);        
		
        final Button singleModeButton = (Button) findViewById(R.id.single);
        singleModeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(); 
                setResult(RESULT_OK, intent); // could return info back
                finish();
            }
        });
        
        final Button runButton = (Button) findViewById(R.id.run);
        runButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	EditText numAtt = ((EditText)findViewById(R.id.numAtt));
            	EditText numDef = ((EditText)findViewById(R.id.numDef));
            	EditText runUntil = ((EditText)findViewById(R.id.numAttUntil));
            	
                runBattle(Integer.parseInt(numAtt.getText().toString()), 
                		Integer.parseInt(numDef.getText().toString()),
                		Integer.parseInt(runUntil.getText().toString()),
                		v.getContext());
            }
        });

		layoutParams.weight = 1;
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
		
        ref = new BattleRef(new DiceSet(3), new DiceSet(2));
	}
	
	private void runBattle(int att, int def, int runUntil, Context context) {
		Vector<BattleResult> results = new Vector<BattleResult>();
		int roundNum = 1;
		
		TableLayout table = (TableLayout)findViewById(R.id.tableLayout1);
		table.removeViews(1, table.getChildCount()-1); // leave the title
		
		TableRow row = makeTableRow(0, att, def, context);
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		while (att >= runUntil && att > 1 && def > 0) {
			BattleResult result = ref.calculateBattle(Math.min(3, att-1), Math.min(2, def));
			results.add(result);
			att -= result.attLost;
			def -= result.defLost;
			
			row = makeTableRow(roundNum++, att, def, context);
			table.addView(row, 1, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); // insert to index 1
		}
	}
	
	private TableRow makeTableRow(int roundNum, int att, int def, Context context) {
		TableRow row = new TableRow(context);
        
		 TextView round = new TextView(context);
		 round.setText(Integer.toString(roundNum));
		 round.setLayoutParams(layoutParams);
		 round.setPadding(8, 8, 0, 0);
		 row.addView(round);
		 
		 TextView numAtt = new TextView(context);
		 numAtt.setText(Integer.toString(att));
		 numAtt.setLayoutParams(layoutParams);
		 row.addView(numAtt);
		 
		 TextView numDef = new TextView(this);
		 numDef.setText(Integer.toString(def));
		 numDef.setLayoutParams(layoutParams);
		 row.addView(numDef);
		 
		 return row;
	}
}
