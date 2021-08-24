package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class landing_page extends AppCompatActivity {

    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        ImageView button = (ImageView) findViewById(R.id.nextbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome();
            }
        });
    }

    private void openhome() {
        Intent intent = new Intent(this, instruction_slideone.class);
        startActivity(intent);
    }
}