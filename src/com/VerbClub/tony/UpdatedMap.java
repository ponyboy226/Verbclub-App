package com.VerbClub.tony;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;

public class UpdatedMap extends MapActivity implements OnClickListener {

	TabHost th;
	TextView showR;
	long start, stop;
	MapView mapView;
	MapController mc;
	GeoPoint p;
	Button bTakePicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		// Adding the zoom option
				mapView = (MapView) findViewById(R.id.MapView);
				LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.zoom);
				View zoomView = mapView;

				zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				mapView.displayZoomControls(true);

				mc = mapView.getController();
				String coordinates[] = { "32.2217429", " -110.926479" };
				double lat = Double.parseDouble(coordinates[0]);
				double lng = Double.parseDouble(coordinates[1]);

				p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));

				mc.animateTo(p);
				mc.setZoom(10);
				mapView.invalidate();
				mapView.setSatellite(false);
				mapView.setStreetView(false);
				// bTakePicture = (Button)findViewById(R.id.bTakePicture);
				// bTakePicture.setOnClickListener(this);
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
					TextView text = new TextView(UpdatedMap.this);
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
