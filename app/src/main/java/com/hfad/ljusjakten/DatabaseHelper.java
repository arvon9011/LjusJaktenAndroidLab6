package com.hfad.ljusjakten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "candle_db.db";
    private static final String STORE_TABLE = "CandleStore";
    private static final String ID = "ID";
    private static final String STORE_NAME = "Name";
    private static final String PRICE = "PRICE";
    private static final String SIZE = "SIZE";
    private static final String LAT_LOCATION = "LAT";
    private static final String LONG_LOCATION = "LONG";
    private static final String BPK = "BPK";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTable = "CREATE TABLE " + STORE_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STORE_NAME + " TEXT, " + PRICE + " INTEGER, " + SIZE + " INTEGER," + LAT_LOCATION + " DOUBLE, " + LONG_LOCATION + " DOUBLE, " + BPK + " DOUBLE)";

    db.execSQL(createTable);
    }

public void addData(String storeName, int price, int size, double latLo, double longLo, double _BPK){
        SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(STORE_NAME,storeName);
    contentValues.put(PRICE,price);
    contentValues.put(SIZE,size);
    contentValues.put(LAT_LOCATION, latLo);
    contentValues.put(LONG_LOCATION, longLo);
    contentValues.put(BPK, _BPK);


    db.insert(STORE_TABLE,null,contentValues);
}

public Cursor loadData (){
    SQLiteDatabase db = this.getReadableDatabase();
    String query = "SELECT * FROM " + STORE_TABLE;
    Cursor data = db.rawQuery(query, null);
    return data;

}



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
