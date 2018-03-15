package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;

import java.util.Map;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
        }
    }

    public void updateContact(View v){

        String uID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();

        String totalData[] = {name, email};
        String totalDataField[] = {"name", "email"};

        for(int j=0; j<2; j++) {
            appState.firebaseReference.child(uID).child(totalDataField[j]).setValue(totalData[j]);
        }
        finish();

    }

    public void eraseContact(View v)
    {
        String uID = receivedPersonInfo.uid;
        appState.firebaseReference.child(uID).removeValue();
        finish();
    }
}
