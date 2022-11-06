package com.example.ottable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class ManageOttActivity extends AppCompatActivity {

    TextView type, u_id, u_pass;
    MaterialCardView Ott1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ott);


        type = findViewById(R.id.ott1text1);
        u_id = findViewById(R.id.ott1text2);
        u_pass = findViewById(R.id.ott1text3);

        Showdatanow();

        Ott1 = findViewById(R.id.ott1);
        Ott1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent n = new Intent (ManageOttActivity.this, OTTselect.class);
                startActivity(n);

            }


        });


    }


    private void Showdatanow() {

        Intent i = getIntent();
        String user_Type = i.getStringExtra("buy_type");
        String user_USname = i.getStringExtra("der_username");
        String user_PassWord = i.getStringExtra("der_pass");

        type.setText(user_Type);
        u_id.setText(user_USname);
        u_pass.setText(user_PassWord);


    }
}