package com.example.maps.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(15);
        // Add a marker in Sydney and move the
        LatLng mazatlan = new LatLng(23.16150, -106.26450 );
        mMap.addMarker(new MarkerOptions().position(mazatlan).title("Marcador en Mazatlán"));
        CameraPosition camara= new CameraPosition .Builder()
                .target(mazatlan)
                .zoom(10)
                .bearing(90)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override

            public void onMapClick(LatLng latLng) {

                Toast.makeText(MapsActivity.this, "Click on: \n"+"Lat:"+latLng.latitude+"\n" +
                        "Long: "+latLng.longitude, Toast.LENGTH_SHORT).show();
                Double lat = latLng.latitude;
                Double log = latLng.longitude;
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference("Lat - Long");
                String gps= lat+log.toString();
                ref.push().setValue(gps);

            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

            }
        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });
    }
}
