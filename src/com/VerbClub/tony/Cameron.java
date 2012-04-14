package com.VerbClub.tony;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class Cameron extends MapActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_cameron);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
