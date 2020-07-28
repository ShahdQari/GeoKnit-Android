package com.example.covied;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavigationView);
        bottomNavigationView.setSelectedItemId(R.id.map);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.test:
                        startActivity(new Intent(getApplicationContext(), test.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.map:
                        return true;
                    case R.id.permit:
                        startActivity(new Intent(getApplicationContext(), permit.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.hospatil:
                        startActivity(new Intent(getApplicationContext(), hospital.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

   @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        LatLng makkah = new LatLng(21.3709817 , 39.7932463);
       googleMap.addMarker(new MarkerOptions().position(makkah).title("Home"));
       googleMap.moveCamera(CameraUpdateFactory.newLatLng(makkah));
       Polygon polygon = googleMap.addPolygon(new PolygonOptions()
               .add(
                       new LatLng(21.36887192,39.80334971),
                       new LatLng(21.3587692, 39.80183488),
                       new LatLng(21.35494637,39.80247013),
                       new LatLng(21.35267083,39.8022258),
                       new LatLng(21.35034974,39.80090643),
                       new LatLng(21.34884784,39.79885408),
                       new LatLng(21.34789208,39.79489598),
                       new LatLng(21.34793759,39.78717523),
                       new LatLng(21.34884784,39.78409671),
                       new LatLng(21.35071383,39.78307053),
                       new LatLng(21.35144202,39.78121364),
                       new LatLng(21.35171509,39.77657142),
                       new LatLng(21.35480984,39.77422588),
                       new LatLng(21.35926981,39.77261332),
                       new LatLng(21.36286498,39.77124508),
                       new LatLng(21.36696065,39.76982798),
                       new LatLng(21.37128373,39.77574071),
                       new LatLng(21.37478761,39.78145797),
                       new LatLng(21.37542467,39.78531834),
                       new LatLng(21.37597071,39.79362548),
                       new LatLng(21.37897395,39.79518917),
                       new LatLng(21.37920146,39.79821883),
                       new LatLng(21.37883744,39.80325197),
                       new LatLng(21.37860992,39.80496227),
                       new LatLng(21.37228485,39.80369176),
                       new LatLng(21.36896294,39.80330084))
               .fillColor(0x33FF0000)
               .strokeColor(Color.RED)
               .strokeWidth(1));
       // Position the map's camera near Alice Springs in the center of Australia,
       // and set the zoom factor so most of Australia shows on the screen.
       googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.3709817, 39.7932463), 12));
       // Set listeners for click events.
       googleMap.setOnPolylineClickListener(this);
       googleMap.setOnPolygonClickListener(this);

    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }

}