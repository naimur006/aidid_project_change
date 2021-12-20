package com.example.voting_final_year_project;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //variables
    Animation topAnim, bottomAnim;
    ImageView imageView3;
    TextView textView6,textView7,textView8,textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_main );

        //Animations
        topAnim = AnimationUtils.loadAnimation (this, R.anim.top_animation );
        bottomAnim = AnimationUtils.loadAnimation ( this,R.anim.bottom_animation );

        //Hooks
        imageView3 = findViewById ( R.id. imageView3);
        textView6 = findViewById ( R.id. textView6 );
        textView7 = findViewById ( R.id. textView7 );
        textView8 = findViewById ( R.id. textView8 );
        textView9 = findViewById ( R.id. textView9 );

        textView6.setAnimation ( topAnim );
        textView7.setAnimation ( topAnim );
        imageView3.setAnimation ( topAnim );
        textView8.setAnimation ( bottomAnim );
        textView9.setAnimation ( bottomAnim );

        new Handler ().postDelayed ( new Runnable () {
            @Override
            public void run() {
                Intent intent = new Intent (MainActivity.this,Walkthrogh.class);
                startActivity ( intent );
                finish ();
            }
        },SPLASH_SCREEN );
    }
}