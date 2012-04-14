package com.VerbClub.tony;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost th;
	TextView showR;
	long start, stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		init();
		th.setup();
		Button newTab = (Button) findViewById(R.id.bAddTab);
		Button lessTab = (Button) findViewById(R.id.bRemoveTab);
		Button bStart = (Button) findViewById(R.id.bStartWatch);
		Button bStop = (Button) findViewById(R.id.bStopWatch);
		showR = (TextView) findViewById(R.id.tvShowResults);
		newTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		lessTab.setOnClickListener(this);

		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		th.setup();
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab Two");
		th.addTab(specs);
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
		start = 0;
	}

	private void init() {
		// TODO Auto-generated method stub
		th = (TabHost) findViewById(R.id.tabhost);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bAddTab:
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new Tab");
					return (text);
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			

			break;
		case R.id.bRemoveTab:
			th.getTabWidget().removeView(th.getTabWidget().getChildTabViewAt(3));
			break;
		case R.id.bStartWatch:
			start = System.currentTimeMillis();
			showR.setText("Press Stop to stop the StopWatch");

			break;
		case R.id.bStopWatch:
			stop = System.currentTimeMillis();

			if (start != 0) {
				long result = (stop - start);
				int mil = (int) result;
				int sec = (int) result / 1000;
				int min = sec / 60;
				mil = mil % 100;
				sec = sec % 60;

				showR.setText(String.format("%d:%02d:%02d", min, sec, mil));
			}

			break;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}