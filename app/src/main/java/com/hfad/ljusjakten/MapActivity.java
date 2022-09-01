package com.hfad.ljusjakten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    SupportMapFragment smp;
    FusedLocationProviderClient client;
    LatLng latlng;
    float minDistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        smp = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            userLocation();
        } else {
            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        StoreFragment storeFragment = new StoreFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.storeinfolayout, storeFragment, storeFragment.getTag()).commit();


    }


    private void userLocation() {
        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(location -> {
                if (location != null) {
                    smp .getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap gm) {
                            latlng = new LatLng((location.getLatitude()), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latlng).title("User");
                            gm.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,17.0f));
                            gm.addMarker(options);
                            getCloseStore();
                        }
                    });


                }
            });
        } else {
            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userLocation();
            }
        }
    }


    private void getCloseStore() {
        TextView information = (TextView) findViewById(R.id.StoreInfo);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<CandleStore> arrayList = new ArrayList<CandleStore>();
        Cursor data = databaseHelper.loadData();


        while (data.moveToNext()) {
            CandleStore temp = new CandleStore(data.getString(1), Integer.parseInt(data.getString(3)), Integer.parseInt(data.getString(2)), Double.parseDouble(data.getString(4)), Double.parseDouble(data.getString(5)), Double.parseDouble(data.getString(6)));
            arrayList.add(temp);
        }

        float minDistance = 0;
        for (CandleStore store : arrayList) {
            LatLng temp = new LatLng(store.getLatLocation(),store.getLongLocation());
          float distance = distanceBetween(temp);

          if (minDistance == 0){minDistance = distance;}

          if (distance< minDistance){
              String text = "Din närmaste butik är " + store.getStoreName() +"!\n"
            + "Butikens mest prisvärda värmeljus säljs i " + store.getSize() +" pack för "+store.getPrice()+"kr och har en BPK på hela " + store.getBPK() +" timmar per krona.";
              information.setText(text);
              minDistance = distance;
          }
        }






    }

    private float distanceBetween(LatLng Store) {
        Location loc1 = new Location(LocationManager.GPS_PROVIDER);
        Location loc2 = new Location(LocationManager.GPS_PROVIDER);
        loc1.setLatitude(latlng.latitude);
        loc1.setLongitude(latlng.longitude);
        loc2.setLatitude(Store.latitude);
        loc2.setLongitude(Store.longitude);
        return loc1.distanceTo(loc2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();

        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.option1:

                Intent addIntent = new Intent(this,AddActivity.class);
                startActivity(addIntent);

                return true;

            case R.id.option2:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;

            case R.id.option3:
                Intent databaseIntent = new Intent(this,DataActivity.class);
                startActivity(databaseIntent);
                return true;

            case R.id.option4:
                turnOff();

        }


        return super.onOptionsItemSelected(item);
    }


    private void turnOff(){
        finishAffinity();
        System.exit(0);
    }
}