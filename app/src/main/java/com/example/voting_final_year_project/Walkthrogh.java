package com.example.voting_final_year_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Walkthrogh extends AppCompatActivity {

    ViewPager nSLideViewPager;
    LinearLayout nDotLayout;
    Button backbtn,nextbtn,skipbtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_walkthrogh );

        backbtn = findViewById ( R.id.backbtn );
        nextbtn = findViewById ( R.id.nextbtn );
        skipbtn = findViewById ( R.id.skipButton );

        backbtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                if (getItem ( 0 ) > 0){
                    nSLideViewPager.setCurrentItem ( getItem ( -1 ),true );
                }

            }
        } );

        nextbtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (getItem ( 0 ) < 3)
                    nSLideViewPager.setCurrentItem ( getItem ( 1 ),true );
                else {
                    Intent i = new Intent (Walkthrogh.this,mainscreen.class);
                    startActivity ( i );
                    finish ();
                }
            }
        } );

        skipbtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (Walkthrogh.this,mainscreen.class);
                startActivity ( i );
                finish ();

            }
        } );

        nSLideViewPager = (ViewPager) findViewById ( R.id.slideViewPager );
        nDotLayout = (LinearLayout) findViewById ( R.id.indicator_layout );

        viewPagerAdapter = new ViewPagerAdapter ( this );
        nSLideViewPager.setAdapter ( viewPagerAdapter );

        setupindicator ( 0 );
        nSLideViewPager.addOnPageChangeListener ( viewListener );


    }

    @SuppressLint("NewApi")
    public void setupindicator(int position){

        dots = new TextView[4];
        nDotLayout.removeAllViews ();

        for(int i=0; i< dots.length; i++){

            dots[i] = new TextView ( this );
            dots[i].setText ( Html.fromHtml ("&#8226") );
            dots[i].setTextSize ( 35 );
            dots[i].setTextColor ( getResources ().getColor ( R.color.inactive,getApplicationContext ().getTheme () ) );
            nDotLayout.addView ( dots[i] );

        }

        dots[position].setTextColor ( getResources ().getColor ( R.color.active,getApplicationContext ().getTheme () ) );


    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener () {
        @Override
        public void onPageScrolled(int position , float positionOffset , int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setupindicator ( position );

            if (position > 0){
                backbtn.setVisibility ( View.VISIBLE );
            }else {
                backbtn.setVisibility ( View.INVISIBLE );
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private int getItem(int i){

        return nSLideViewPager.getCurrentItem ()+i;
    }

}