package com.example.voting_final_year_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class sign3 extends AppCompatActivity {
    private HashMap<String, String> userdata = new HashMap<> ();
    EditText phone;
    Button signup_next_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sign3 );
        Intent intent = getIntent ();
        userdata = (HashMap<String, String>) intent.getSerializableExtra ( "userdata" );
        phone = findViewById ( R.id.phone );
        signup_next_button = findViewById ( R.id.signup_next_button );


        signup_next_button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (phone.getEditableText ().toString ().length () != 10) {
                    Toast.makeText ( sign3.this , "Phone Number is not valid" , Toast.LENGTH_SHORT ).show ();
                    return;
                } else {
                    userdata.put ( "phone" , "+880" + phone.getEditableText ().toString () );
                    Intent i = new Intent ( sign3.this , otp.class );
                    i.putExtra ( "userdata" , userdata );
                    startActivity ( i );
                }
            }
        } );

    }

}