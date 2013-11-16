package jp.tetra2000.geophoto.util;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Location {
    private LocationManager mManager;
    private LocationCallback mCallback;
    private boolean started=false;

    public double latitude = Double.MAX_VALUE, longitude = Double.MAX_VALUE;

    public Location(Context context) {
        mManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void start() {
        if(!started) {
            // TODO 後でGPS有効のチェックコード追加
            String locationProvider = LocationManager.GPS_PROVIDER;
        	
//        	// TODO デバッグ用なので後で削除
//        	String locationProvider = LocationManager.NETWORK_PROVIDER;
            mManager.requestLocationUpdates(locationProvider, 0, 0, listener);
        }
    }

    public void stop() {
        if(started) {
            mManager.removeUpdates(listener);
        }
    }
    
    public void setCallback(LocationCallback callback) {
    	mCallback = callback;
    }

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
        	latitude = location.getLatitude();
        	longitude = location.getLongitude();
        	
        	if(mCallback != null)
        		mCallback.updated();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
