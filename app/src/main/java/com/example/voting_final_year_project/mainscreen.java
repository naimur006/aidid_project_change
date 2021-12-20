package com.example.voting_final_year_project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

public class mainscreen extends AppCompatActivity implements View.OnClickListener {
    private CardView adminpanel,votingpanel,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_mainscreen );

        adminpanel = findViewById ( R.id.admin_card );
        votingpanel = findViewById ( R.id.voting_card );
        back = findViewById ( R.id. back_card );

        adminpanel.setOnClickListener ( this );
        votingpanel.setOnClickListener (this );
        back.setOnClickListener ( this );
    }



    @Override
    public void onClick(View v) {
        Intent i;
        //use switch case
        switch (v.getId ()){
            case R.id.admin_card:i=new Intent (this,adminpanel.class);startActivity ( i );
                break;
            case R.id.voting_card:i=new Intent (this,votingpanel.class);startActivity ( i );
                break;
            case R.id.back_card:i=new Intent (this,Walkthrogh.class);startActivity ( i );
                break;
            default:break;
        }
    }

}
