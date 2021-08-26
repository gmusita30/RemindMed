package com.example.capstone1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class main_page extends AppCompatActivity {

    Button create_Account;
    Button login;
    Button guest;
    FirebaseAuth rootAuthen;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        create_Account = (Button) findViewById(R.id.createacc_button);
        login = (Button) findViewById(R.id.login_button);
        guest = (Button) findViewById(R.id.guest_btn);
        rootAuthen = FirebaseAuth.getInstance();
        currentUser = rootAuthen.getCurrentUser();

        create_Account.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(main_page.this, create_account.class);
                startActivity(intent);
            }
        });

        }

    public void create_account(View view) {
        Intent intent = new Intent(main_page.this, create_account.class);
        startActivity(intent);
    }
}



        //guest
/*
        rootAuthen.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            guest.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    openlanding_page();
                                }
                            });
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = rootAuthen.getCurrentUser();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(main_page.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
*/









   // public void Main_To_Create(View view) {
     //   Intent intent = new Intent(this, create_account.class);
       // startActivity(intent);
    //}
//}

/*
    public void Main_To_Create(){
        Intent intent = new Intent(this, create_account.class);
        startActivity(intent);
    }
/*
    public void openlogin_page(){
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }
    //guest
    public void openlanding_page(){
        Intent intent = new Intent(this, landing_page.class);
        startActivity(intent);
    }



 */