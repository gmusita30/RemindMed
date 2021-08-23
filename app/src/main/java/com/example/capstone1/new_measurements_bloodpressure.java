package com.example.capstone1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class new_measurements_bloodpressure extends AppCompatActivity {
    EditText bloodpressure;
    Button buttonsavesbloodpressure;
    FirebaseAuth rootAuthen;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measurements_bloodpressure);

        bloodpressure = findViewById(R.id.blood_pressure_box);
        buttonsavesbloodpressure = findViewById(R.id.btnsavebloodpressure);

        rootAuthen = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = rootAuthen.getCurrentUser().getUid();

        buttonsavesbloodpressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bloodpressure = bloodpressure.getText().toString().trim();

                Map<String,Object> user =new HashMap<>();
                user.put("Bloodpressure",Bloodpressure);

                fstore.collection("users").document(userId).set(user, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(new_measurements_bloodpressure.this, "New blood pressure measurement added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                bloodpressure.setText(value.getString("Bloodpressure"));

            }
        });
    }
    public void Bloodpressure_To_Health (View view){
        Intent intent = new Intent(new_measurements_bloodpressure.this, health_measurements.class);
        startActivity(intent);
    }
}