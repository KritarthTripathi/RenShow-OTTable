package com.example.ottable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class OTTselect extends AppCompatActivity {


    MaterialCardView netflix_select, prime_select, hotstar_select, zee5_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ottselect);

        netflix_select = findViewById(R.id.netflixselect);
        prime_select = findViewById(R.id.primeselect);
        hotstar_select = findViewById(R.id.hotstarselect);
        zee5_select = findViewById(R.id.zee5select);


        netflix_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent l = new Intent(OTTselect.this,NetflixActivity.class);
                startActivity(l);

            }
        });


    }
}