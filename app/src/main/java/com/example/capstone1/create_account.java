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
import com.google.firebase.database.FirebaseDatabase;

public class create_account extends AppCompatActivity {
    EditText first,last,password,confirm,emailInput;
    Button buttonSignUp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase root;
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

        root = FirebaseDatabase.getInstance();
        reference = root.getReference("User");
        rootAuthen = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = emailInput.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Confirm_Password = confirm.getText().toString().trim();

                if (isEmpty(first) && isEmpty(last)  && isEmpty(password) && isEmpty(confirm) && isEmpty(emailInput)) {
                    makeText(create_account.this, "Please enter all the details above.", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(first)) {
                    makeText(create_account.this, "Please enter your first name.", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(last)) {
                    makeText(create_account.this, "Please enter your last name.", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(password) || !Password.equals(Confirm_Password)) {
                    makeText(create_account.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 6) {
                    makeText(create_account.this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(confirm)) {
                    makeText(create_account.this, "Please re-enter your password.", Toast.LENGTH_SHORT).show();
                }
                else if (!isEmail(emailInput) || !Email.matches(emailPattern)) {
                    makeText(create_account.this, "Please enter a valid Email.", Toast.LENGTH_SHORT).show();
                }

                /*
                else {
                    validate();
                }
                */

            }
        });
    }
/*
    //Validate User if they already created the account or not
    private void validate(){
        String UserId = rootAuthen.getUid();
        String First_Name = first.getText().toString().trim();
        String Last_Name = last.getText().toString().trim();
        String Email = emailInput.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Confirm_Password = confirm.getText().toString().trim();

        final UserData user_data = new UserData(UserId, First_Name, Last_Name, Email, Password, Confirm_Password);

        rootAuthen.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String UserId = rootAuthen.getCurrentUser().getUid();
                    reference.child(UserId).setValue(user_data);
                    rootAuthen.signOut();
                    makeText(create_account.this, "You have sign up successfully!", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(create_account.this, login_page.class);
                    startActivity(intent);
                }
                else if (task.getException() instanceof FirebaseAuthUserCollisionException){
                    makeText(create_account.this, "Email already in used.", Toast.LENGTH_LONG).show();
                }
                else {
                    makeText(create_account.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
*/


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text) {
        CharSequence emailInput = text.getText().toString();
        return (!TextUtils.isEmpty(emailInput) && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches());
    }


}

