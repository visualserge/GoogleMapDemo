package com.serge.googlemapdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.location.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {

    private double longitude;
    private double latitude;
    private GoogleMap mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager lm = (LocationManager)getSystemService(getApplicationContext().LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.action_satellite:
                    mapView.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    break;
            case R.id.action_street:
                    mapView.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    break;
            case R.id.action_terrain:
                    mapView.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMapReady(GoogleMap map) {
        LatLng myLocation = new LatLng(latitude, longitude);
        this.mapView = map;
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        map.addMarker(new MarkerOptions()
                .title("This is your current location")
                .snippet("Hi Sergio! :)")
                .position(myLocation));
    }
}
