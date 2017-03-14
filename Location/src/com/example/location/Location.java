package com.example.location;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Location implements LocationListener{

	
	Context context;
	LocationManager manager;
	android.location.Location location;
	Criteria criteria;
	String provider;
	   
	
	   public Location(Context context) {
		super();
		this.context = context;
	}


	public double getLat(){
		double lat = 0;
		   manager=(LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		   criteria=new Criteria();
		   
	        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
	        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
	        
	        provider=manager.getBestProvider(criteria, true);
	        
	        manager.requestLocationUpdates(provider, 1, 10, this);
	        
	        location=manager.getLastKnownLocation(provider);
	        
	        lat= location.getLatitude();
	        return lat;
	   }

	public double getLong(){
		double lng = 0;
		   manager=(LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		   criteria=new Criteria();
		   
	        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
	        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
	        
	        provider=manager.getBestProvider(criteria, true);
	        
	        manager.requestLocationUpdates(provider, 1, 10, this);
	        
	        location=manager.getLastKnownLocation(provider);
	        
	        lng = location.getLongitude();
	        return lng;
	   }


	@Override
	public void onLocationChanged(android.location.Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
