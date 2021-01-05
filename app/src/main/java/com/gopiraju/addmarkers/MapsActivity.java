package com.gopiraju.addmarkers;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        arrayList = new ArrayList<LatLng>();
        LatLng me = new LatLng(17.44086888721371, 78.44760406762362);
        LatLng srPStation = new LatLng(17.44141457165864, 78.44773046672344);
        LatLng amoga = new LatLng(17.437546126293327, 78.44843454658985);
        LatLng temple1 = new LatLng(17.44042715669876, 78.44747733324766);
        LatLng temple2 = new LatLng(17.44755772712814, 78.44917986541985);
        LatLng srMetro = new LatLng(17.441650629253733, 78.44163112342358);
        arrayList.add(me);
        arrayList.add(srPStation);
        arrayList.add(srMetro);
        arrayList.add(temple1);
        arrayList.add(temple2);
        arrayList.add(amoga);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i = 0; i < arrayList.size(); i++) {
            MarkerOptions options = new MarkerOptions();
            options.position(arrayList.get(i)).title(arrayList.get(i).toString());
            googleMap.addMarker(options).setIcon(bitmapDescriptor(getApplicationContext(), R.drawable.ic_baseline_flight_24));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arrayList.get(0),15f));
        }

    }


    private BitmapDescriptor bitmapDescriptor(Context context, int value) {
        Drawable drawable = ContextCompat.getDrawable(context, value);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }
}