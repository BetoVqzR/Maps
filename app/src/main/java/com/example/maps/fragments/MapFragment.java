package com.example.maps.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maps.R;
import com.example.maps.activities.MainActivity;
import com.example.maps.activities.MapsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap gmap;
    private MapView mapView;
    private View rootView;
    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = rootView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

        LatLng mazatlan = new LatLng(23.230321098850688, -106.38315193355083 );
        gmap.addMarker(new MarkerOptions().position(mazatlan).title("Marcador en Mazatlán"));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(mazatlan));
        CameraPosition camara= new CameraPosition .Builder()
                .target(mazatlan)
                .zoom(15)
                .bearing(0)
                .tilt(60)
                .build();
        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(camara));

        gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {


            }
        });
        gmap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Double lat = latLng.latitude;
                Double log = latLng.longitude;
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference ref = db.getReference("Lat - Long");
                String gps= lat+log.toString();
                try {
                    ref.push().setValue(gps);
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Ha Ocurrido un Error", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), "Ubicacion Guardada", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
