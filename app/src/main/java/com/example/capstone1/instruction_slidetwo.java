package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class instruction_slidetwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_slidetwo);
    }
    public void Two_To_Three (View view){
        Intent intent = new Intent(instruction_slidetwo.this, instruction_slidethree.class);
        startActivity(intent);
    }
}