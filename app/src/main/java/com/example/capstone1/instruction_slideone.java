package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class instruction_slideone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_slideone);
    }
    public void One_To_Two (View view){
        Intent intent = new Intent(instruction_slideone.this, instruction_slidetwo.class);
        startActivity(intent);
    }
}