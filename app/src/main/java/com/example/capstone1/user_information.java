package com.example.capstone1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class user_information extends AppCompatActivity {
    //public static final String TAG1 = "TAG";
    public static final String TAG = "TAG";
    EditText gender, birthdate, height, weight;
    Button buttonSave;
    TextView email, firstname, lastname;
    FirebaseAuth rootAuthen;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        Intent data = getIntent();
        String Gender = data.getStringExtra("Gender");
        String Birthdate = data.getStringExtra("Birthdate");
        String Height = data.getStringExtra("Height");
        String Weight = data.getStringExtra("Weight");

        email = findViewById(R.id.emailview);
        firstname = findViewById(R.id.firstview);
        lastname = findViewById(R.id.lastview);

        gender = findViewById(R.id.editTextgender);
        birthdate = findViewById(R.id.editTextbirth);
        height = findViewById(R.id.editTextheight);
        weight = findViewById(R.id.editTextweight);
        buttonSave = findViewById(R.id.btnSave);

        rootAuthen = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = rootAuthen.getCurrentUser().getUid();

        gender.setText(Gender);
        birthdate.setText(Birthdate);
        gender.setText(Height);
        gender.setText(Weight);


        Log.d(TAG, "onCreate: " + Gender + " " + Birthdate + " " + Height + " " + Weight);

/*
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Gender = gender.getText().toString().trim();
                String Birthdate = birthdate.getText().toString().trim();
                String Height = height.getText().toString().trim();
                String Weight = height.getText().toString().trim();

                if (TextUtils.isEmpty(Gender)) {
                    gender.setError("Gender is required");
                    return;
                }
                if (TextUtils.isEmpty(Birthdate)) {
                    birthdate.setError("Birthdate is required");
                    return;
                }
                if (TextUtils.isEmpty(Height)) {
                    height.setError("Height is required");
                    return;
                }
                if (TextUtils.isEmpty(Weight)) {
                    weight.setError("Confirm Password is required");
                    return;
                }

                rootAuthen.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(user_information.this, "Saved", Toast.LENGTH_SHORT).show();
                            userId = rootAuthen.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Gender",Gender);
                            user.put("YearofBirth",Birthdate);
                            user.put("Weight",Weight);
                            user.put("Height",Height);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSucess: saved " + userId);
                                }
                            });
                    }
                });
            }
        });
*/
        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("email"));
                firstname.setText(value.getString("firstname"));
                lastname.setText(value.getString("lastname"));
            }
        });
    }
    public void User_To_Account (View view){
        Intent intent = new Intent(user_information.this, change_name.class);
        startActivity(intent);
    }
}