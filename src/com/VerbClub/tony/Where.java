package com.VerbClub.tony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Where extends MapActivity implements OnTabChangeListener {

	MapView mapView1;
	MapController mc;
	GeoPoint p;

	private static final String LIST_TAB_TAG = "List";
	private static final String MAP_TAB_TAG = "Map";
	private TabHost tabHost;
	private ListView listView;
	private MapView mapView;
	String[] loc = { "Mt. Lemmon", "Tucson", "Phoenix", "Netherlands" };

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// set Activity to full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.where);
		

		
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.setOnTabChangedListener(this);
		// setup list view
		listView = (ListView) findViewById(R.id.listmap);
		listView.setEmptyView((TextView) findViewById(R.id.empty));

		// create coordinates to add to the list
		

		/** Called when the activity is first created. */

		//Here is what creates the coordinates on the list view
		List<GeoPoint> pointsList = new ArrayList<GeoPoint>();
		pointsList.add(new GeoPoint((int) (32.260042 * 1E6),
				(int) (-110.800540 * 1E6)));
		pointsList.add(new GeoPoint((int) (31.916900 * 1E6),
				(int) (-106.042900 * 1E6)));
		
		listView.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, pointsList));
		
		TextView test = (TextView)findViewById(R.id.tvNext);
		test.setText("I hope this works");

		// add an onclicklistener to see point on the map
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View view,
					int position, long id) {

				GeoPoint geoPoint = (GeoPoint) listView.getAdapter().getItem(
						position);

				if (geoPoint != null) {
					// have map view moved to this point
					setMapZoomPoint(geoPoint, 14);
					// switch tabs to the map view
					tabHost.setCurrentTab(1);
				}
			}
		});
		// setup map view
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.postInvalidate();
		// add views to tab host

		tabHost.addTab(tabHost.newTabSpec(MAP_TAB_TAG).setIndicator("Map")
				.setContent(new TabContentFactory() {
					public View createTabContent(String arg0) {

						// Adding the zoom option

						// View zoomView = mapView.getZoomControls();

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

						return mapView;

					}

				}));

		tabHost.addTab(tabHost.newTabSpec(LIST_TAB_TAG).setIndicator("List")
				.setContent(new TabContentFactory() {

					public View createTabContent(String arg0) {
						

						return listView;

					}

				}));

		// otherwise the map view would be bleeding through and visible
		tabHost.setCurrentTab(1);

		tabHost.setCurrentTab(0);

	}

	/**
	 * 
	 * Tells the map view to navigate to the point and zoom level.
	 * 
	 * @param geoPoint
	 * 
	 * @param zoomLevel
	 */

	private void setMapZoomPoint(GeoPoint geoPoint, int zoomLevel) {
		mapView.getController().setCenter(geoPoint);
		mapView.getController().setZoom(zoomLevel);
		mapView.postInvalidate();

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;

	}

	/*
	 * Define what the tabs do
	 */

	public void onTabChanged(String tabName) {
		if (tabName.equals(MAP_TAB_TAG)) {
			// do something on the map

		}

		else if (tabName.equals(LIST_TAB_TAG)) {
			// do something on the list

		}
	}
}
