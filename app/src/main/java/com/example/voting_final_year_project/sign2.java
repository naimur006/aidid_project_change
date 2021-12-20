package com.example.voting_final_year_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class sign2 extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button signup_next_button;
    private DatePicker birthday;
    private HashMap<String,String> userdata=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sign2 );
        Intent intent = getIntent();
        userdata = (HashMap<String, String>) intent.getSerializableExtra("userdata");
        radioGroup=findViewById(R.id.radioGroup);
        signup_next_button=findViewById(R.id.signup_next_button);
        birthday=findViewById(R.id.birthday);
        //birthday.setSpinnersShown(false);
        signup_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date;
                if(2021-birthday.getYear()<18){
                    Toast.makeText(sign2.this, "User must be 18+",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    date=birthday.getDayOfMonth()+"-"+(birthday.getMonth()+1)+"-"+birthday.getYear();
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId==-1){
                    Toast.makeText(sign2.this, "Please select your gender",Toast.LENGTH_SHORT).show();
                    return;
                }
                radioButton = (RadioButton) findViewById(selectedId);
                userdata.put("gender",radioButton.getText().toString());
                userdata.put("birthday",date);
                Intent i=new Intent(sign2.this,sign3.class);
                i.putExtra("userdata", userdata);
                startActivity(i);
            }
        });

    }


}