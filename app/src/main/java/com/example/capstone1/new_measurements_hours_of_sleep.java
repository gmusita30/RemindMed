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

public class new_measurements_hours_of_sleep extends AppCompatActivity {
    EditText sleep;
    Button buttonsavesleep;
    FirebaseAuth rootAuthen;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_measurements_hours_of_sleep);

        sleep = findViewById(R.id.hours_of_sleep_box);
        buttonsavesleep = findViewById(R.id.btnsavesleep);

        rootAuthen = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = rootAuthen.getCurrentUser().getUid();

        buttonsavesleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sleep = sleep.getText().toString().trim();

                Map<String,Object> user =new HashMap<>();
                user.put("Sleep",Sleep);

                fstore.collection("users").document(userId).set(user, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(new_measurements_hours_of_sleep.this, "New hours of sleep measurement added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                sleep.setText(value.getString("Sleep"));

            }
        });
    }
    public void Sleep_To_Health (View view){
        Intent intent = new Intent(new_measurements_hours_of_sleep.this, health_measurements.class);
        startActivity(intent);
    }
}