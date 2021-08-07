package com.example.capstone1;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class create_account extends AppCompatActivity {
    EditText first,last,password,confirm,emailInput;
    Button buttonSignUp;
    //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //FirebaseDatabase root;
    DatabaseReference reference;
    FirebaseAuth rootAuthen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        first =findViewById(R.id.first_nameBox);
        last =findViewById(R.id.last_nameBox);
        password =findViewById(R.id.passwordBox);
        confirm =findViewById(R.id.confimpassword);
        emailInput =findViewById(R.id.emailBox);
        buttonSignUp = findViewById(R.id.btnSign);

        //root = FirebaseDatabase.getInstance();
       // reference = root.getReference("User");
        rootAuthen = FirebaseAuth.getInstance();

        if(rootAuthen.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),main_page.class));
            finish();
        }

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = emailInput.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Confirm_Password = confirm.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    emailInput.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Confirm_Password)){
                    password.setError("Confirm Password is required");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    confirm.setError("Password is required");
                    return;
                }
                if(Password.length() < 6){
                    password.setError("Password must be at least 6 characters");
                    return;
                }

               rootAuthen.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(create_account.this, "User Created", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),main_page.class));
                       }else {
                           Toast.makeText(create_account.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }

                   }
               });

            }
        });
    }


}

