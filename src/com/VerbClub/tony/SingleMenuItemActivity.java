package com.VerbClub.tony;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItemActivity extends Activity{

	TextView tvName;
	TextView tvAid;
	String [] lv_arr_id;
	
	String fromTheLastIntent_aid, fromTheLastIntent_name;
	String IntentToString_aid, IntentToString_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		
		tvName = (TextView)findViewById(R.id.tvname);
		tvAid = (TextView)findViewById(R.id.tvaid);
		
		Serializable fromTheLastIntent_aid = getIntent().getSerializableExtra("id");
		Serializable fromTheLastIntent_name = getIntent().getSerializableExtra("name");
		
	
		IntentToString_aid = fromTheLastIntent_aid.toString();
		IntentToString_name = fromTheLastIntent_name.toString();
		
		
		tvAid.setText(IntentToString_aid);
		tvName.setText(IntentToString_name);
		
		
	}

}
