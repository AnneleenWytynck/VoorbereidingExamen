package com.hannes.voorbereidingexamen.Domein;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by hannes on 18/08/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
