package com.github.tpc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class CalculatorActivity extends OptionsMenuActivity implements 
	View.OnClickListener, ViewSwitcher.ViewFactory {
    	
    EditText playersCount;
    EditText buyIn;
    EditText finalPosition;
    TextSwitcher switcher;
    Button button1;
    Button button2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);        
        
        playersCount = (EditText) findViewById(R.id.editText1);
        buyIn = (EditText) findViewById(R.id.editText2);
        finalPosition = (EditText) findViewById(R.id.editText3);
        
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);        
        switcher = (TextSwitcher) findViewById(R.id.switcher);
        switcher.setFactory(this);
        switcher.setInAnimation(in);
        switcher.setOutAnimation(out);        
        
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Score score = new Score();
		if (!validateScore(score)) {
			switcher.setText("");
			return;
		}
		
		switch (v.getId()) {
		case R.id.button1:
			switcher.setText( String.valueOf(score.countPointsForPosition(score.finalPosition)) );			
			break;
		case R.id.button2:
			Intent showScoreBoard = new Intent(this, ScoreBoardActivity.class);
			showScoreBoard.putExtra("playersCount", score.playersCount);
			showScoreBoard.putExtra("buyIn", score.buyIn);
			showScoreBoard.putExtra("finalPosition", score.finalPosition);
			startActivity(showScoreBoard);
			break;
		}		
	}

	boolean validateScore(Score score) {
		try {
			score.playersCount = Integer.valueOf( playersCount.getText().toString() );
			score.buyIn = Integer.valueOf( buyIn.getText().toString() );
			score.finalPosition = Integer.valueOf( finalPosition.getText().toString() );
			
			if (!score.isPlayersCountValid()) {
				Toast.makeText(this, R.string.validation_playersCount, Toast.LENGTH_LONG).show();
			} else if (!score.isBuyInValid()) {
				Toast.makeText(this, R.string.validation_buyIn, Toast.LENGTH_LONG).show();
			} else if (!score.isFinalPositionValid()) {
				Toast.makeText(this, R.string.validation_finalPosition, Toast.LENGTH_LONG).show();	
			} else {
				return true;
			}
						
			return false;
		} catch (NumberFormatException e) {
			if (score.playersCount == -1) {
				Toast.makeText(this, R.string.validation_playersCount_empty, Toast.LENGTH_LONG).show();
			}
			if (score.buyIn == -1) {
				Toast.makeText(this, R.string.validation_buyIn_empty, Toast.LENGTH_LONG).show();
			}
			if (score.finalPosition == -1) {
				Toast.makeText(this, R.string.validation_finalPosition_empty, Toast.LENGTH_LONG).show();
			}
			
			return false;
		}			
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		button1 = button2 = null; 
	}

	@Override
	public View makeView() {
        TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setTextSize(36);
        return t;	
    }
}