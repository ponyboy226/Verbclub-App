package com.VerbClub.tony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Who extends ListActivity{

	
	private final static String firstName = "field_profile_first_name_value";
	private final static String lastName = "field_profile_last_name_value";
	String photo = "path";


	/** Called when the activity is first created. */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.who);

		setListAdapter(new ArrayAdapter(this,

		android.R.layout.simple_list_item_1, this.fetchVerbClubStuff()));
	}
	
	public ArrayList<String> fetchVerbClubStuff() {
		ArrayList<String> listItems = new ArrayList<String>();

		
		
		try {
			URL people = new URL(
					"http://dev.verbclub.com/verb/get/people");
			URLConnection tc = people.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONArray ja = new JSONArray(line);

				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);


					
					listItems.add(jo.getString(firstName)+" "+(jo.getString(lastName)));

				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItems;
	}

	
	
	
	
//	String people[] = {"Tony", "Cameron", "Chris"};
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		
//		setListAdapter(new ArrayAdapter<String>(Who.this,
//				android.R.layout.simple_list_item_1, people));
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent a = new Intent("com.VerbClub.tony.ABOUT");
			startActivity(a);
			break;
		case R.id.preferences:
			Intent p = new Intent("com.VerbClub.tony.PREFS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		case R.id.profile:
			Intent pr = new Intent("com.VerbClub.tony.PROFILE");
			startActivity(pr);
			break;
		}
		return false;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Loop through the ListItems and go to profile based on the name selected
		super.onListItemClick(l, v, position, id);
	
		
		
//		This has been working for hard coding it		
//		String cheese = people[position];
//		try {
//			Class<?> ourClass = Class.forName("com.VerbClub.tony." + cheese.toUpperCase());
//			Intent ourIntent = new Intent(Who.this, ourClass);
//			startActivity(ourIntent);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	
	}

}
