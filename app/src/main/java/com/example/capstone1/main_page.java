package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_page extends AppCompatActivity {
    private Button create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        create_account = (Button) findViewById(R.id.createAccount_btn);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencreate_account();
            }
        });
    }

    public void opencreate_account() {
        Intent intent = new Intent(this, create_account.class);
        startActivity(intent);
    }
}
