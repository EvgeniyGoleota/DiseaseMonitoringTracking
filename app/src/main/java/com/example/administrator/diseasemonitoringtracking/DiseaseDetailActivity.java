package com.example.administrator.diseasemonitoringtracking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import Adapters.DiseaseRecyclerViewAdapter;
import Adapters.RecyclerItemClickListener;
import data.DiseaseCase;
import data.Disease_outbreak;
import data.Variables;

public class DiseaseDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    Disease_outbreak disease;

    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 0);

        disease = Variables.diseaseList.get(position);
        //tx.setText(disease.getDescription());

        setTitle(disease.getName());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Variables.DISPLAY_WIDTH  = displayMetrics.widthPixels;
        Variables.DISPLAY_HEIGHT = displayMetrics.heightPixels;

        View view = findViewById(R.id.map_fragment);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Variables.DISPLAY_HEIGHT / 2));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DiseaseRecyclerViewAdapter disAdapter = new DiseaseRecyclerViewAdapter(disease.getDc());
        recyclerView.setAdapter(disAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DiseaseCase diseaseCase = disease.getDc().get(position);
                LatLng caseLocation = new LatLng(diseaseCase.getLatitude(), diseaseCase.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(caseLocation, 6));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (DiseaseCase disCase : disease.getDc()) {
            LatLng diseaseLocation = new LatLng(disCase.getLatitude(), disCase.getLongitude());
            mMap.addMarker(new MarkerOptions().position(diseaseLocation).title(disCase.getDescription()));

//            CircleOptions circleOptions = new CircleOptions()
//                    .center(diseaseLocation)
//                    .radius(10000)
//                    .fillColor(Color.parseColor("#F44336"));
//            mMap.addCircle(circleOptions);
        }

        LatLng myCurrentLocation = new LatLng(Variables.Latitude, Variables.Longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myCurrentLocation, 3));
        Marker currentLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(myCurrentLocation)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .alpha((float) 0.5));
        currentLocationMarker.showInfoWindow();

        mProvider = new HeatmapTileProvider.Builder()
                .weightedData(disease.getWeightLatLngCollection())
                //.gradient()
                .opacity(0.4)
                .build();

        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }
}
