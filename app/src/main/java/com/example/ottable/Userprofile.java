package com.example.ottable;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

public class Userprofile extends AppCompatActivity {

    TextInputLayout full_name, phoneNumber, UPI_ID, PassWord_profile;
    TextView smallusername, bigrealname;
    MaterialCardView renting, managing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        full_name = findViewById(R.id.fname);
        phoneNumber = findViewById(R.id.phone);
        UPI_ID = findViewById(R.id.savedupi);
        PassWord_profile = findViewById(R.id.passshow);
        bigrealname = findViewById(R.id.fullname);
        smallusername = findViewById(R.id.smallusrname);
        renting = findViewById(R.id.gotorentpage);
        managing = findViewById(R.id.mnging);


        showAllUserData();


        managing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent open = new Intent(Userprofile.this,ManageOttActivity.class);
                startActivity(open);
            }
        });

        renting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rent = new Intent(Userprofile.this,OTTselect.class);
                startActivity(rent);

            }
        });


    }

    private void showAllUserData() {
        Intent i = getIntent();
        String user_username = i.getStringExtra("username");
        String user_name = i.getStringExtra("name");
        String user_no = i.getStringExtra("phoneNo");
        String user_upi = i.getStringExtra("upi_id");
        String user_password = i.getStringExtra("password");

        bigrealname.setText(user_name);
        smallusername.setText(user_username);
        full_name.getEditText().setText(user_name);
        phoneNumber.getEditText().setText(user_no);
        UPI_ID.getEditText().setText(user_upi);
        PassWord_profile.getEditText().setText(user_password);

    }


}