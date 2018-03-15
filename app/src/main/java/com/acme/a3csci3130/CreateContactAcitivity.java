package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import com.acme.a3csci3130.R;

import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, userNumber, userAddress;
    private MyApplicationData appState;
    Contact receivedPersonInfo;
    private Spinner dropdown1, dropdown2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables

        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        userNumber = (EditText) findViewById(R.id.userNumber);
        userAddress = (EditText) findViewById(R.id.userAddress);

        /**
         * 2 spinners are used
         * 1st > dropdown1 is for BusinessType
         * 2nd > dropdown2 is for Province
         */
        dropdown1 = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"Fisher", "Distributor", "Processor", "Fish Monger" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown1.setAdapter(adapter);
        dropdown1.setSelection(0);

        dropdown2 = (Spinner) findViewById(R.id.spinner2);
        String[] items2 = new String[]{" ", "AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"} ;
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setSelection(0);
    }

    /**
     *
     * @param v: onclick listener for submit button
     *         this will take user's name, email, address, businesstype, province and upload it to the firebase
     *         and further this details gets populated in the list.
     *
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String userNum = userNumber.getText().toString();
        String userAdd = userAddress.getText().toString();
        String businessType = dropdown1.getSelectedItem().toString();
        String province = dropdown2.getSelectedItem().toString();

        Contact person = new Contact(personID, name, email, userNum, userAdd, businessType, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
