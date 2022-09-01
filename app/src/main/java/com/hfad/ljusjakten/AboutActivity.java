package com.hfad.ljusjakten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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