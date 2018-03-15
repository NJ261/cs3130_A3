package com.acme.a3csci3130;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Franz on 2017-05-31.
 */

public class MyApplicationData extends Application {
    //firebase instances declaration so that we can use it another part of the project.
    public DatabaseReference firebaseReference;
    public FirebaseDatabase firebaseDBInstance;

}
