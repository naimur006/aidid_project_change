package com.example.voting_final_year_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.HashMap;
import java.util.regex.Pattern;

public class signup_activity extends AppCompatActivity {

    private Button signup_next_button;
    private ImageView signup_back_button;
    private Context context;
    private FirebaseAuth mAuth;
    private EditText full_name;
    private EditText user_name;
    private EditText email;
    private EditText password;
    private HashMap<String, String> userdata = new HashMap<> ();

    Pattern PASSWORD_PATTERN =
            Pattern.compile ( "^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    "(?=.*[a-z])(?=.*[A-Z])" + //Uppercase and lowercase
                    ".{8,}" +                // at least 8 characters
                    "$" );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_signup_activity );
        signup_next_button = findViewById ( R.id.signup_next_button );
        mAuth = FirebaseAuth.getInstance ();
        full_name = findViewById ( R.id.full_name );
        user_name = findViewById ( R.id.user_name );
        email = findViewById ( R.id.email );
        password = findViewById ( R.id.password );
        signup_back_button = findViewById ( R.id.signup_back_button );

        signup_back_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (signup_activity.this,votingpanel.class);
                startActivity ( intent );
            }
        } );

        signup_next_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if (full_name.getEditableText ().toString ().length () < 2) {
                    Toast.makeText ( getApplicationContext () , "Full name is too short" , Toast.LENGTH_SHORT ).show ();
                    return;
                }

                if (user_name.getEditableText ().toString ().length () < 3) {
                    Toast.makeText ( getApplicationContext () , "User Name is too short" , Toast.LENGTH_SHORT ).show ();
                    return;
                }


                if (!android.util.Patterns.EMAIL_ADDRESS.matcher ( email.getEditableText ().toString () ).matches ()) {
                    Toast.makeText ( getApplicationContext () , "Please enter a valid email address" , Toast.LENGTH_SHORT ).show ();
                    return;
                }

                if (!validatePassword ()) {
                    return;
                }
                Toast.makeText ( getApplicationContext () , "Please Wait" , Toast.LENGTH_LONG ).show ();



                mAuth.fetchSignInMethodsForEmail ( email.getEditableText ().toString () )
                        .addOnCompleteListener ( new OnCompleteListener<SignInMethodQueryResult> () {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean isNewUser = task.getResult ().getSignInMethods ().isEmpty ();




                                    if (!isNewUser) {
                                        Toast.makeText ( getApplicationContext () , "Please try another email.Someone already using this email" , Toast.LENGTH_SHORT ).show ();

                                    }




                                userdata.put ( "email" , email.getEditableText ().toString () );
                                userdata.put ( "password" , password.getEditableText ().toString () );
                                userdata.put ( "name" , full_name.getEditableText ().toString () );
                                userdata.put ( "username" , user_name.getEditableText ().toString () );
                                Intent i = new Intent ( signup_activity.this , sign2.class );
                                i.putExtra ( "userdata" , userdata );
                                startActivity ( i );
                            }
                        } );

            }
        } );

    }


    private boolean validatePassword() {
        String passwordInput = password.getEditableText ().toString ();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty ()) {
            Toast.makeText ( getApplicationContext () , "Field can not be empty" , Toast.LENGTH_SHORT ).show ();
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher ( passwordInput ).matches ()) {
            password.setError ( "Password is too weak" );
            Toast.makeText ( getApplicationContext () , "Password is too weak" , Toast.LENGTH_SHORT ).show ();
            return false;
        } else {
            password.setError ( null );
            return true;
        }
    }

}