package com.codepath.apps.twootter;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     TwootterClient client = TwootterApp.getRestClient(Context context);
 *     // use client to send requests to API
 *
 */
public class TwootterApp extends Application {

    MyDatabase myDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        // when upgrading versions, kill the original tables by using
		// fallbackToDestructiveMigration()
        myDatabase = Room.databaseBuilder(this, MyDatabase.class,
                MyDatabase.NAME).fallbackToDestructiveMigration().build();

        // use chrome://inspect to inspect your SQL database
        Stetho.initializeWithDefaults(this);
    }

    public static TwootterClient getRestClient(Context context) {
        return (TwootterClient) TwootterClient.getInstance(TwootterClient.class, context);
    }

    public MyDatabase getMyDatabase() {
        return myDatabase;
    }
}