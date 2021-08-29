package com.example.capstone1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class user_information extends AppCompatActivity {
    public static final String TAG = "TAG";
    //public static final String TAG1 = "TAG";
    //public static final String TAG = "TAG";
    EditText gender, birthyr, height, weight;
    Button buttonSave, buttonLogout, buttonpdf;
    TextView email, firstname, lastname;
    FirebaseAuth rootAuthen;
    FirebaseFirestore fstore;
    String userId;

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        
        email = findViewById(R.id.emailview);
        //firstname = findViewById(R.id.firstnameview);
        //lastname = findViewById(R.id.lastview);

        spinner = findViewById(R.id.gender_spinner);
        birthyr = findViewById(R.id.editTextbirth);
        height = findViewById(R.id.editTextheight);
        weight = findViewById(R.id.editTextweight);
        buttonSave = findViewById(R.id.btnSave);
        buttonLogout = findViewById(R.id.btnLogout);

        buttonpdf = findViewById(R.id.buttontestpdf);

        rootAuthen = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = rootAuthen.getCurrentUser().getUid();

        //added spinner and
        //Spinner mySpinnerone = (Spinner) findViewById(R.id.gender_spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(user_information.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        //para sa pdf
        buttonpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String Firstname = firstname.getText().toString().trim();

                String Gender = spinner.getSelectedItem().toString().trim();
                String Birthyr = birthyr.getText().toString().trim();
                String Height = height.getText().toString().trim();
                String Weight = weight.getText().toString().trim();

                Map<String,Object> user = new HashMap<>();
                //user.put("firstname", Firstname);
                user.put("gender",Gender);
                user.put("birthyr",Birthyr);
                user.put("height",Height);
                user.put("weight",Weight);

                printPDF();
            }
        });
        //hanggang dito pdf

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Gender = spinner.getSelectedItem().toString().trim();
                String Birthyr = birthyr.getText().toString().trim();
                String Height = height.getText().toString().trim();
                String Weight = weight.getText().toString().trim();



                Map<String,Object> user = new HashMap<>();
                user.put("gender",Gender);
                user.put("birthyr",Birthyr);
                user.put("height",Height);
                user.put("weight",Weight);



                fstore.collection("users").document(userId).set(user, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(user_information.this, "User information added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("email"));
                //firstname.setText(value.getString("firstname"));
                //lastname.setText(value.getString("lastname"));
                //gender.setText(value.getString("gender"));
                birthyr.setText(value.getString("birthyr"));
                height.setText(value.getString("height"));
                weight.setText(value.getString("weight"));
                

            }
        });
    }

    //start ng method ng pdf
    private void printPDF() {
        PdfDocument myPdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint forLinePaint = new Paint();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250,350,1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
        Canvas canvas = myPage.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0,50,250));

        canvas.drawText("RemindMed",20,20,paint);
        paint.setTextSize(8.5f);
        canvas.drawText("Testing lang kung gagana",20,40,paint);
        forLinePaint.setStyle(Paint.Style.STROKE);
        forLinePaint.setPathEffect(new DashPathEffect(new float[]{5,5},0));
        forLinePaint.setStrokeWidth(2);
        canvas.drawLine(20,65,230,65,forLinePaint);

        canvas.drawText("Email: " + email.getText(),20,80,paint);
        canvas.drawText("Gender: " + spinner.getSelectedItem(),20,90,paint);
        canvas.drawText("Year of birth: " + birthyr.getText(),20,100,paint);
        canvas.drawText("Height: " + height.getText(),20,110,paint);
        canvas.drawText("Weight: " + weight.getText(),20,120,paint);

        myPdfDocument.finishPage(myPage);
        File file = new File(this.getExternalFilesDir("/"), "Remindmed.pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myPdfDocument.close();

    }
//end ng method ng pdf

    public void User_To_Account (View view){
        Intent intent = new Intent(user_information.this, change_name.class);
        startActivity(intent);
    }

    public void Logout (View view){
        Intent intent = new Intent(user_information.this, main_page.class);
        startActivity(intent);

    }
}
