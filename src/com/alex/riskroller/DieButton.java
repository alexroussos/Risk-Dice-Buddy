package com.alex.riskroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class DieButton extends Button {

	private Die die = new Die(6);// TODO later - take in ctor, with isAtt. How to pass in customer attrs?
	public boolean isAttacker = false; 
	
	public DieButton(Context context) {
		super(context);
		die.roll();
	}

	public DieButton(Context context, AttributeSet atts) {
		super(context, atts);
		die.roll();
	}

	public DieButton(Context context, AttributeSet atts, int something) {
		super(context, atts, something);
		die.roll();
	}
	
	public void toggle() {
		if (die.isEnabled()) 
			die.disable();
		else
			die.enable();
	}
	
	public Die getDie() {
		return die;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.setBackgroundResource(getResId(isAttacker, die.won, die.isEnabled(), die.value));
		super.setTextColor(Color.YELLOW);
		super.setText(die.isEnabled() ? "" : "Tap to Enable");
		super.onDraw(canvas);
	}
	
	private int getResId(boolean isAtt, boolean won, boolean enabled, int value) {
		if (!isAtt) {
			if (won) {
				switch (value) {
				case 1: return(R.drawable.white1win);
				case 2: return(R.drawable.white2win);
				case 3: return(R.drawable.white3win);
				case 4: return(R.drawable.white4win);
				case 5: return(R.drawable.white5win);
				case 6: return(R.drawable.white6win);
				default: return(0);
				}
			} else if (!enabled) {
				switch (value) {
				case 1: return(R.drawable.white1dis);
				case 2: return(R.drawable.white2dis);
				case 3: return(R.drawable.white3dis);
				case 4: return(R.drawable.white4dis);
				case 5: return(R.drawable.white5dis);
				case 6: return(R.drawable.white6dis);
				default: return(0);
				}
			} else {
				switch (value) {
				case 1: return(R.drawable.white1);
				case 2: return(R.drawable.white2);
				case 3: return(R.drawable.white3);
				case 4: return(R.drawable.white4);
				case 5: return(R.drawable.white5);
				case 6: return(R.drawable.white6);
				default: return(0);
				}
			}
		} else {
			if (won) {
				switch (value) {
				case 1: return(R.drawable.red1win);
				case 2: return(R.drawable.red2win);
				case 3: return(R.drawable.red3win);
				case 4: return(R.drawable.red4win);
				case 5: return(R.drawable.red5win);
				case 6: return(R.drawable.red6win);
				default: return(0);
				}
			} else if (!enabled) {
				switch (value) {
				case 1: return(R.drawable.red1dis);
				case 2: return(R.drawable.red2dis);
				case 3: return(R.drawable.red3dis);
				case 4: return(R.drawable.red4dis);
				case 5: return(R.drawable.red5dis);
				case 6: return(R.drawable.red6dis);
				default: return(0);
				}
			} else {
				switch (value) {
				case 1: return(R.drawable.red1);
				case 2: return(R.drawable.red2);
				case 3: return(R.drawable.red3);
				case 4: return(R.drawable.red4);
				case 5: return(R.drawable.red5);
				case 6: return(R.drawable.red6);
				default: return(0);
				}
			}
		}
	}
	
}
