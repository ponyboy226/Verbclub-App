package com.VerbClub.tony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class What extends ListActivity {
	
	String verb = "name";
	
	
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
					"http://verbclub.com/verb/get/verbs");
			URLConnection tc = people.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONArray ja = new JSONArray(line);

				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);
					listItems.add(jo.getString(verb));
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

	

}
