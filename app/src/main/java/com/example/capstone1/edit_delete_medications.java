package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class edit_delete_medications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_medications);

        Spinner mySpinner = (Spinner) findViewById(R.id.type_spinner_two);
        Spinner mySpinnertwo = (Spinner) findViewById(R.id.frequency_spinner_two);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(edit_delete_medications.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(edit_delete_medications.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.frequency));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnertwo.setAdapter(myAdapter2);

    }
}