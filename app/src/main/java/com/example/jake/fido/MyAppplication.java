package com.example.jake.fido;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyAppplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
