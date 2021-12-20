package com.example.voting_final_year_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class votingpanel extends AppCompatActivity implements View.OnClickListener {
    private Button forget_password, create_account,login_button;
    private ImageView login_back;
    private EditText username,voterid,phone,email,password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_votingpanel );
        mAuth = FirebaseAuth.getInstance();

        forget_password = findViewById ( R.id.forget_password );
        create_account = findViewById ( R.id.create_account );
        login_back = findViewById ( R.id.login_back_button );

        forget_password.setOnClickListener ( this );
        create_account.setOnClickListener ( this );
        login_back.setOnClickListener ( this );
        username=findViewById(R.id.username);
        voterid=findViewById(R.id.voterid);
        login_button=findViewById(R.id.login_button);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(email.getEditableText().toString(),password.getEditableText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            DatabaseReference uidRef=FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());

                            Toast.makeText(votingpanel.this, "Checking User Data", Toast.LENGTH_LONG).show();
                            ValueEventListener valueEventListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user_data = dataSnapshot.getValue(User.class);
                                    if(!user_data.username.equals(username.getEditableText().toString())){
                                        Toast.makeText(votingpanel.this, "User name not matched!", Toast.LENGTH_LONG).show();
                                        return;
                                    }

                                    if(!user_data.phone.equals("+880"+phone.getEditableText().toString())){
                                        Toast.makeText(votingpanel.this, "Phone not matched!", Toast.LENGTH_LONG).show();
                                        return;
                                    }

                                    if(!voterid.getEditableText().toString().equals("1111")){
                                        Toast.makeText(votingpanel.this, "Invalid voter id!", Toast.LENGTH_LONG).show();
                                        return;
                                    }

                                    Intent i=new Intent(votingpanel.this,verify_image.class);
                                    startActivity(i);
                                    finish();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    System.out.println(databaseError.getMessage());
                                }
                            };
                            uidRef.addListenerForSingleValueEvent(valueEventListener);

                        }else{
                            Toast.makeText(votingpanel.this, "invalid Email Password", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(votingpanel.this,verify_image.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;
        //use switch case
        switch (v.getId ()) {
            case R.id.forget_password:
                i = new Intent ( this , forgetpassword.class );
                startActivity ( i );
                break;
            case R.id.create_account:
                i = new Intent ( this , signup_activity.class );
                startActivity ( i );
                break;

            case R.id.login_back_button:
                i = new Intent ( this , mainscreen.class );
                startActivity ( i );
                break;


        }
    }
}





