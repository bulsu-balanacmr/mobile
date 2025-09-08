package com.example.riderapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class TrackingOrderActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView btnBack, btnCall;
    private TextView txtStatus, txtShopInfo, txtDestination;

    private final String riderPhoneNumber = "09171234567";
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Marker riderMarker;
    private Polyline routeLine;

    private final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    // Order location
    private final LatLng orderLocation = new LatLng(14.8535, 120.8160); // Bulacan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking_order);

        // Bind views
        btnBack = findViewById(R.id.btnBack);
        btnCall = findViewById(R.id.btnCall);
        txtStatus = findViewById(R.id.txtStatus);
        txtShopInfo = findViewById(R.id.txtShopInfo);
        txtDestination = findViewById(R.id.txtDestination);

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Call button
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + riderPhoneNumber));
            startActivity(intent);
        });

        // Initialize fusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Load map
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Example status text
        txtStatus.setText("Delivering Your Order");
        txtShopInfo.setText("Bakery and Patisserie - 8:00 AM");
        txtDestination.setText("Jl. Haji Sidik No.20 - 8:30 AM");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add marker for order location
        mMap.addMarker(new MarkerOptions().position(orderLocation).title("Order Location"));

        // Initial camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(orderLocation, 15));

        // Check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        // Location request
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult.getLocations().size() > 0) {
                    android.location.Location location = locationResult.getLastLocation();
                    LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                    // Update or add rider marker
                    if (riderMarker == null) {
                        riderMarker = mMap.addMarker(new MarkerOptions()
                                .position(userLatLng)
                                .title("Rider"));
                    } else {
                        riderMarker.setPosition(userLatLng);
                    }

                    // Draw route line (straight line example)
                    if (routeLine != null) {
                        routeLine.remove();
                    }
                    routeLine = mMap.addPolyline(new PolylineOptions()
                            .add(userLatLng, orderLocation)
                            .width(8)
                            .color(0xFFFF6600)); // Orange

                    // Move camera smoothly
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 16));
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
}
