package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class new_measurements_pulserate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measurements_pulserate);

        Spinner my_spinner = (Spinner) findViewById(R.id.frequency_spinner_eight);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(new_measurements_pulserate.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.frequency));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        my_spinner.setAdapter(myAdapter2);
    }
}