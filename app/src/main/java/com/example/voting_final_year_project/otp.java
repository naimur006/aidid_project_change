package com.example.voting_final_year_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private HashMap<String, String> userdata = new HashMap<>();
    Button verifyOTPBtn,resend;
    PinView pinView;
    private String verificationId;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        userdata = (HashMap<String, String>) intent.getSerializableExtra("userdata");
        verifyOTPBtn = findViewById(R.id.verifyOTPBtn);
        pinView = findViewById(R.id.pinView);
        resend=findViewById(R.id.resend);
        StartFirebase();
        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (pinView.getEditableText().toString().length() < 5) {
                    Toast.makeText(otp.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, pinView.getEditableText().toString());
                    if(credential.getSmsCode().toString().equals(pinView.getEditableText().toString())){
                        Toast.makeText(otp.this, "Please Wait", Toast.LENGTH_SHORT).show();
                        mAuth.createUserWithEmailAndPassword(userdata.get("email"), userdata.get("password")).addOnCompleteListener(otp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    System.out.println (user.getUid());

                                    User pojouser=new User(
                                            userdata.get("username"),
                                            userdata.get("name"),
                                            userdata.get("email"),
                                            userdata.get("phone"),
                                            userdata.get("gender"),
                                            userdata.get("birthday")
                                    );

                                    Toast.makeText(otp.this, "Registering User Info", Toast.LENGTH_LONG).show();

                                    FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).setValue(pojouser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent i=new Intent(otp.this,verify_image.class);
                                            startActivity(i);
                                        }
                                    });

                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT);
                                }
                            }
                        });
                    }
                }
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!resend.getText().toString().equals("Resend")){
                    return;
                }
                Intent i=new Intent(otp.this,otp.class);
                i.putExtra("userdata", userdata);
                startActivity(i);
            }
        });
    }



    private void sendVerificationCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                userdata.get("phone"),
                60,
                TimeUnit.SECONDS,
                otp.this,
                mCallback
        );
        resend.setClickable(false);

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                resend.setText("Resend Code After "+(millisUntilFinished / 1000)+"s");
            }

            public void onFinish() {
                resend.setText("Resend");
                resend.setClickable(true);
            }

        }.start();
    }

    private void StartFirebase() {

        mAuth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(otp.this,"verification completed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(otp.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(otp.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };

        sendVerificationCode();

    }
}