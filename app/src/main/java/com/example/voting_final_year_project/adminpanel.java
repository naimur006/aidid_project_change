package com.example.voting_final_year_project;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class adminpanel extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private final String Username = "naimur@123";
    private final String Password = "pass@12345";
    private FirebaseAuth mAuth;

    boolean isValid = false;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_adminpanel );
        mAuth = FirebaseAuth.getInstance();
        eName = findViewById ( R.id.eName );
        ePassword = findViewById ( R.id.ePassword );
        eLogin = findViewById ( R.id.eLogin );
        eAttemptsInfo = findViewById ( R.id.tvAttemptsInfo );

        eLogin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String inputName = eName.getText ().toString ();
                String inputPassword = ePassword.getText ().toString ();

                if (inputName.isEmpty () || inputPassword.isEmpty ())
                {
                    Toast.makeText ( adminpanel.this,"Please fill up all credentials!",Toast.LENGTH_SHORT ).show ();
                }else{

                    isValid = validate ( inputName, inputPassword );

                    if (!isValid){

                        counter--;

                        Toast.makeText ( adminpanel.this,"Incorrect credentials entered!",Toast.LENGTH_SHORT ).show ();

                        eAttemptsInfo.setText ( "No. of Attempts Remaining : " +counter );

                        if (counter == 0){
                            eLogin.setEnabled ( false );
                        }

                    }else {

                        Toast.makeText ( adminpanel.this,"Successfully Logged In!",Toast.LENGTH_SHORT ).show ();

                        //add the code to go to the new activity

                        Intent intent = new Intent (adminpanel.this,inside_adminpanel.class);
                        startActivity ( intent );
                    }
                }
            }
        } );

/**
        mAuth.createUserWithEmailAndPassword("admin@admin.com", "password")

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println ("user created");
                        } else {
                            System.out.println ("error: "+task.getException());
                        }

                        // ...
                    }
                });

 */
    }

    private boolean validate(String name, String password){

        if (name.equals ( Username ) && password.equals ( Password )){

            return true;
        }

        return false;
    }
}