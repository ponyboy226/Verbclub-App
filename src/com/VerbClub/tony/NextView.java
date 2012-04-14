package com.VerbClub.tony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NextView extends Activity {

	public static String z_var = "";
	private WhereList longOperationContext = null;
	ProgressDialog pd = null;
	TextView tv = null;
	ListView lv1 = null;
	String[] lv_arr;
	String[] lv_arr_id;
	private static final int ACTIVITY_CREATE = 0;

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main_list); 
	     //   new LongOperation2(this).execute();
	    }



	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    //    new LongOperation2(this).execute();
	        return true;
	    }
	}

	class LongOperation2 extends AsyncTask<String, Void, String> {
	    private WhereList longOperationContext = null; //used to be private Main longOperationContext = null;
	    ProgressDialog pd = null;
	    TextView tv = null;
	    ListView lv1 = null;
	    String [] lv_arr;
	    String [] lv_arr_id;
	    private static final int ACTIVITY_CREATE = 0;



	    public LongOperation2(WhereList context) {
	        longOperationContext = context;
	        Log.v("LongOper", "Konstuktor");
	    }

	    @Override
	    protected String doInBackground(String... params) {
	        Log.v("doInBackground", "inside");
	        try {
	            URL json = new URL("http://dev.verbclub.com/verb/get/areas/z/5/*/*");
	            URLConnection tc = json.openConnection();
	            BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

	            String line;
	            while ((line = in.readLine()) != null) {
	                    JSONArray ja = new JSONArray(line);
	                    Log.v("line = ", " " + ja.length());
	                    lv_arr = new String[ja.length()];
	                    lv_arr_id = new String[ja.length()];
	                    for (int i=0;i<ja.length();i++) {
	                        JSONObject jo = (JSONObject) ja.get(i);
	                        lv_arr[i] = jo.getString("name");
	                        lv_arr_id[i] = jo.getString("aid");
	                    }
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	            Log.v("Error", "URL exc");
	        } catch (IOException e) {
	            e.printStackTrace();
	            Log.v("ERROR", "IOEXECPTOIn");
	        } catch (JSONException e) {
	            e.printStackTrace();
	            Log.v("Error", "JsonException");
	        }
	        Log.v("Line: ", lv_arr[0] + " - " + lv_arr[1]);
	        return null;
	    } //

	    @Override
	    protected void onPostExecute(String result) {
	        lv1.setAdapter(new ArrayAdapter<String>(longOperationContext,android.R.layout.simple_list_item_1 , lv_arr));
	        lv1.setTextFilterEnabled(true);
	        lv1.setOnItemClickListener(new OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	                        Intent i = new Intent(longOperationContext, NextView.class); // was  SingleMenuItemActivity.class
	                        i.putExtra("id", lv_arr_id[position]);
	                        i.putExtra("name", lv_arr[position]);
	                        longOperationContext.startActivityForResult(i, ACTIVITY_CREATE);
	                }
	        });
	        pd.dismiss(); //used to be pd.hide();
	    }
	  
	    
	    protected void onPreExecute() {
	        lv1 = (ListView)longOperationContext.findViewById(android.R.id.list);
	        pd = new ProgressDialog(longOperationContext);
	        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pd.setMessage("Loading...");
	        pd.setIndeterminate(true);
	        pd.setCancelable(false);
	        pd.show();
	    }

	    @Override
	    protected void onProgressUpdate(Void... values) {
	    }
	}