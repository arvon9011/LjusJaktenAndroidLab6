package com.hfad.ljusjakten;

import java.util.ArrayList;

public class CandleStore {

    String storeName;
    int price;
    int size;
    double latLocation;
    double longLocation;
    double BPK;


    public CandleStore(String storeName, int price, int size, double latLocation, double longLocation, double BPK) {
        this.storeName = storeName;
        this.price = price;
        this.size = size;
        this.latLocation = latLocation;
        this.longLocation = longLocation;
        this.BPK = BPK;

    }










    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getLatLocation() {
        return latLocation;
    }

    public void setLatLocation(float latLocation) {
        this.latLocation = latLocation;
    }

    public double getLongLocation() {
        return longLocation;
    }

    public void setLongLocation(float longLocation) {
        this.longLocation = longLocation;
    }




    public double getBPK() {
        return BPK;
    }

    public void setBPK(double BPK) {
        this.BPK = BPK;
    }
}
