package com.hfad.ljusjakten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {
    EditText name;
    EditText price;
    EditText size;
    EditText Lat;
    EditText Long;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.storeName);
       price = (EditText) findViewById(R.id.Price);
        size = (EditText) findViewById(R.id.Size);
        Lat = (EditText) findViewById(R.id.PlaceLat);
        Long = (EditText) findViewById(R.id.PlaceLong);

    }





    public void handleAddBtn(View view) throws IOException {
        String tempStoreName = name.getText().toString();
        int tempPrice = Integer.parseInt(price.getText().toString());
        int tempSize = Integer.parseInt(size.getText().toString());
        double tempLat =Double.parseDouble(Lat.getText().toString());
        double tempLong = Double.parseDouble(Long.getText().toString());


        float burnTime = tempSize * 4;
        float BPK = burnTime / tempPrice;

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        dbHelper.addData(tempStoreName,tempPrice,tempSize,tempLat,tempLong,BPK);




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