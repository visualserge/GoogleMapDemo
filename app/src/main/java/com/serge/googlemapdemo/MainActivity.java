package com.serge.googlemapdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.location.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class MainActivity extends ActionBarActivity {

    private double longitude;
    private double latitude;
    private GoogleMap mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        LocationManager lm = (LocationManager)getSystemService(getApplicationContext().LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();

        //Set default location :)
        SetMap(mapView, GoogleMap.MAP_TYPE_NORMAL);
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
                    SetMap(mapView, GoogleMap.MAP_TYPE_HYBRID);
                    break;
            case R.id.action_street:
                    SetMap(mapView, GoogleMap.MAP_TYPE_NORMAL);
                    break;
            case R.id.action_terrain:
                    SetMap(mapView, GoogleMap.MAP_TYPE_TERRAIN);
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SetMap(GoogleMap map, int mapType){
        LatLng myLocation = new LatLng(latitude, longitude);
        map.setMyLocationEnabled(true);
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));

        map.setMapType(mapType);

        //map.addMarker(new MarkerOptions()
        //        .title("This is your current location")
        //        .snippet("Hi Sergio! :)")
        //        .position(myLocation));



        //LatLng mapCenter = new LatLng(41.889, -87.622);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));

        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.
        map.addMarker(new MarkerOptions()
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.direction_arrow))
                .title("This is your current location")
                .snippet("Hi Sergio! :)")
                .position(myLocation)
                .flat(true)
                .rotation(245));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(myLocation)
                .zoom(13)
                .bearing(90)
                .build();

        // Animate the change in camera view over 2 seconds
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000, null);
    }

}
