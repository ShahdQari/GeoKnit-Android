package com.example.covied;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class map extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener,
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{


    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

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
        mMap = googleMap;
            mMap.addPolygon(new PolygonOptions()
                    .add(
                            new LatLng(21.36887192, 39.80334971),
                            new LatLng(21.3587692, 39.80183488),
                            new LatLng(21.35494637, 39.80247013),
                            new LatLng(21.35267083, 39.8022258),
                            new LatLng(21.35034974, 39.80090643),
                            new LatLng(21.34884784, 39.79885408),
                            new LatLng(21.34789208, 39.79489598),
                            new LatLng(21.34793759, 39.78717523),
                            new LatLng(21.34884784, 39.78409671),
                            new LatLng(21.35071383, 39.78307053),
                            new LatLng(21.35144202, 39.78121364),
                            new LatLng(21.35171509, 39.77657142),
                            new LatLng(21.35480984, 39.77422588),
                            new LatLng(21.35926981, 39.77261332),
                            new LatLng(21.36286498, 39.77124508),
                            new LatLng(21.36696065, 39.76982798),
                            new LatLng(21.37128373, 39.77574071),
                            new LatLng(21.37478761, 39.78145797),
                            new LatLng(21.37542467, 39.78531834),
                            new LatLng(21.37597071, 39.79362548),
                            new LatLng(21.37897395, 39.79518917),
                            new LatLng(21.37920146, 39.79821883),
                            new LatLng(21.37883744, 39.80325197),
                            new LatLng(21.37860992, 39.80496227),
                            new LatLng(21.37228485, 39.80369176),
                            new LatLng(21.36896294, 39.80330084))
                    .fillColor(0x33FF0000)
                    .strokeColor(Color.RED)
                    .strokeWidth(1));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        // Set listeners for click events.
        mMap.setOnPolylineClickListener(this);
        mMap.setOnPolygonClickListener(this);

    }

    public void alert(String bound){
        final AlertDialog.Builder builder = new AlertDialog.Builder(map.this);
        builder.setTitle("bounds");
        builder.setMessage("You are out of bounds" + "\n" + bound);
        builder.setCancelable(true);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
       mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker.

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mCurrLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Current Position")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        if (latLng.latitude < 21.34789207 || latLng.latitude > 21.37920147
                ||latLng.longitude < 39.76982797 || latLng.longitude > 39.80496228) {
            alert(latLng.toString());
        }

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }
}



