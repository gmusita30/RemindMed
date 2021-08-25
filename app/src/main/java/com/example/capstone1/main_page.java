package com.example.capstone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class main_page extends AppCompatActivity {

    private Button create_Account;
    private Button login;
    private Button guest;
    FirebaseAuth rootAuthen;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        create_Account = (Button) findViewById(R.id.Create_btn);
        login = (Button) findViewById(R.id.login_button);
        guest = (Button) findViewById(R.id.guest_btn);
        rootAuthen = FirebaseAuth.getInstance();
        currentUser = rootAuthen.getCurrentUser();

        //guest
/*
        rootAuthen.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
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
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInstructione_slideone();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin_page();
            }
        });

        create_Account.setOnClickListener(new View.OnClickListener() {
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

    public void openlogin_page(){
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);
    }
    //guest
    public void openInstructione_slideone(){
        Intent intent = new Intent(this, Instruction_slideone.class);
        startActivity(intent);
    }

    //public void Main_To_Create (View view){
      //  Intent intent = new Intent(main_page.this, create_account.class);
        //startActivity(intent);
    //}
}