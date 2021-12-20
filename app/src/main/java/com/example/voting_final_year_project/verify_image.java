package com.example.voting_final_year_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class verify_image extends AppCompatActivity {
    FirebaseUser user;
    Button Logout;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_image);
        Logout=findViewById(R.id.Logout);
        user=userLoggedin();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });
    }

    private FirebaseUser userLoggedin(){
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            Toast.makeText(verify_image.this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return null;
        }else{
            mAuth = FirebaseAuth.getInstance();
            return user;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(verify_image.this, "User loggedout", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(verify_image.this,votingpanel.class);
        startActivity(i);
        finish();
    }
}