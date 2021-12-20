package com.example.voting_final_year_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class forgetpassword extends AppCompatActivity {

    private Button resetPasswordBtn;
    private ImageView forgetBackBtn;
    private EditText txtEmail;
    private String email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_forgetpassword );

        mAuth = FirebaseAuth.getInstance ();
        txtEmail = findViewById ( R.id. forget_email );
        resetPasswordBtn = findViewById ( R.id.resetPasswordBtn );
        forgetBackBtn = findViewById ( R.id.forget_password_back_button );

        forgetBackBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent (forgetpassword.this,votingpanel.class) );
            }
        } );



        resetPasswordBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                validateData();
            }
        } );
    }

    private void validateData() {
         email =txtEmail.getText ().toString ().trim ();
        if (email.isEmpty () && !Patterns.EMAIL_ADDRESS.matcher ( email ).matches ()){
            txtEmail.setError ( "Valid Email is required" );
        }

        else {
            forgetPass();
        }
    }

    private void forgetPass() {
        mAuth.sendPasswordResetEmail ( email ).addOnCompleteListener ( new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful ()){
                    Toast.makeText ( forgetpassword.this,"Check your Email",Toast.LENGTH_SHORT ).show ();
                    startActivity ( new Intent (forgetpassword.this,votingpanel.class) );
                    finish ();
                }else{
                    Toast.makeText ( forgetpassword.this,"Error:"+task.getException (),Toast.LENGTH_SHORT ).show ();
                }
            }
        } );
    }
}