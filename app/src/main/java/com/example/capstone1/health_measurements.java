package com.example.capstone1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class health_measurements extends AppCompatActivity {
    EditText bloodpressure, cholesterol, sugar, heartrate, pulserate, sleep;
    Button buttonsavehealth;
    FirebaseAuth rootAuthen;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_measurements);

        bloodpressure = findViewById(R.id.bloodpressureinput);
        cholesterol = findViewById(R.id.cholesterolinput);
        sugar = findViewById(R.id.sugarinput);
        heartrate = findViewById(R.id.heartinput);
        pulserate = findViewById(R.id.pulseinput);
        sleep = findViewById(R.id.sleepinput);
        buttonsavehealth = findViewById(R.id.btnsavehealth);

        rootAuthen = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = rootAuthen.getCurrentUser().getUid();

        buttonsavehealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bloodpressure = bloodpressure.getText().toString().trim();
                String Cholesterol = cholesterol.getText().toString().trim();
                String Sugar = sugar.getText().toString().trim();
                String Heartrate = heartrate.getText().toString().trim();
                String Pulserate = pulserate.getText().toString().trim();
                String Sleep = sleep.getText().toString().trim();

                Map<String,Object> user = new HashMap<>();
                user.put("bloodpressure",Bloodpressure);
                user.put("Cholesterol",Cholesterol);
                user.put("Sugar",Sugar);
                user.put("Heartrate",Heartrate);
                user.put("Pulserate",Pulserate);
                user.put("Sleep",Sleep);

                fstore.collection("users").document(userId).set(user, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(health_measurements.this, "Health Measurements added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}