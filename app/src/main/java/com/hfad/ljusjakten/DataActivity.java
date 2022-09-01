package com.hfad.ljusjakten;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DataActivity  extends AppCompatActivity {



    DatabaseHelper databaseHelper;
    private ListView storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataview);
        databaseHelper = new DatabaseHelper(this);
        storeList = findViewById(R.id.dynamic);

        fillList();
    }


    private void fillList() {



        Cursor value = databaseHelper.loadData();
        ArrayList<String> listData = new ArrayList<>();
        while(value.moveToNext()) {
            String store = value.getString(0) + ", " + value.getString(1) + ", " + value.getString(2) +"," + value.getString(3)+", "+ value.getString(4)+", "+ value.getString(5) +", "+ value.getString(6);

            listData.add(store);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        storeList.setAdapter(adapter);
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
