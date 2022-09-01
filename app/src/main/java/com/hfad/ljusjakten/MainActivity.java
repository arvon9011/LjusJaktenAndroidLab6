package com.hfad.ljusjakten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void handleMapBtn(View view) {
        Intent intent  = new Intent(this, MapActivity.class);
        startActivity(intent);
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