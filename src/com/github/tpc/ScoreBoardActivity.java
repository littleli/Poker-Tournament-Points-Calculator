package com.github.tpc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity {
	
	private final LayoutParams LAYOUT_PARAMS = new LayoutParams(
			LayoutParams.FILL_PARENT,
			LayoutParams.WRAP_CONTENT
	);
	
	@Override
    public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		setContentView(R.layout.scoretable);
		TableLayout layout = (TableLayout) findViewById(R.id.top);
						
		Bundle extras = getIntent().getExtras();
		Score score = new Score();
		score.buyIn = extras.getInt("buyIn");
		score.finalPosition = extras.getInt("finalPosition");
		score.playersCount = extras.getInt("playersCount");
		
		String[][] pointArray = score.getPointsList();
		for (int i = 0; i < pointArray.length; i++) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(LAYOUT_PARAMS);
			addRow(tr, pointArray[i], score.finalPosition == i + 1);
			layout.addView(tr);
		}		
	}
		
	void addRow(TableRow parent, String[] record, boolean highlight) {
		TextView column1 = new TextView(this);
		column1.setLayoutParams(LAYOUT_PARAMS);
		column1.setPadding(50, 5, 5, 5);
		column1.setGravity(Gravity.LEFT);
		column1.setTextSize(28);
		column1.setText( record[0] );
		TextView column2 = new TextView(this);
		column2.setLayoutParams(LAYOUT_PARAMS);
		column2.setPadding(5, 5, 50, 5);
		column2.setGravity(Gravity.RIGHT);
		column2.setTextSize(28);
		column2.setText( record[1] );
		if (highlight) {
			column1.setTextColor(Color.GREEN);
			column2.setTextColor(Color.GREEN);
		}
		parent.addView(column1);		
		parent.addView(column2);
	}
}
