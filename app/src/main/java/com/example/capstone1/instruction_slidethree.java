package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class instruction_slidethree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_slidethree);
    }
    public void Three_To_Home (View view){
        Intent intent = new Intent(instruction_slidethree.this, main_page.class);
        startActivity(intent);
    }
}