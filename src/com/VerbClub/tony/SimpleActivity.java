package com.VerbClub.tony;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.MapActivity;


public class SimpleActivity extends MapActivity implements OnClickListener{
	/** Called when the activity is first created. */

	Button who, what, when, where, createNew, wherelist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		who.setOnClickListener(this);
		what.setOnClickListener(this);
		when.setOnClickListener(this);
		where.setOnClickListener(this);
		createNew.setOnClickListener(this);
		wherelist.setOnClickListener(this);
	}

	private void init() {
		// TODO Define bridges
		who = (Button) findViewById(R.id.buttonWho);
		what = (Button) findViewById(R.id.buttonWhat);
		when = (Button) findViewById(R.id.buttonWhen);
		where = (Button) findViewById(R.id.buttonWhere);
		createNew = (Button) findViewById(R.id.bCreatNew);
		wherelist = (Button) findViewById(R.id.buttonWhereList);
	}

	@Override
	public void onClick(View v) {
		// TODO give functionality to buttons
		switch (v.getId()) {
		case R.id.buttonWho:
			Intent i = new Intent();
			i.setClass(this, Who.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			break;
		case R.id.buttonWhat:
			Intent p = new Intent();
			p.setClass(this, What.class);
			p.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(p);
			break;
		case R.id.buttonWhen:
			Intent m = new Intent();
			m.setClass(this, When.class);
			m.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(m);
			break;
		case R.id.buttonWhere:
			Intent j = new Intent();
			j.setClass(this, Where.class);
			j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(j);
			break;
		case R.id.bCreatNew:
			Intent c = new Intent();
			c.setClass(this, Tabs.class);
			c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(c);
			break;
		case R.id.buttonWhereList:
			Intent t = new Intent();
			t.setClass(this, WhereList.class);
			t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(t);
			break;
		}

	}



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
		switch (item.getItemId()) {
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
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
