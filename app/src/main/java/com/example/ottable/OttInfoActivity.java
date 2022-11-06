package com.example.ottable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OttInfoActivity extends AppCompatActivity {

    Button ottbtn, rentbtn;
    TextInputLayout ottusrn, ottpwrd, ottype;

    FirebaseDatabase rootNode;
    DatabaseReference myref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ott_info);

        ottbtn = (Button) findViewById(R.id.validate);
        rentbtn = (Button) findViewById(R.id.rentott);
        ottusrn = findViewById(R.id.usern);
        ottpwrd = findViewById(R.id.userpass);
        ottype = findViewById(R.id.otttype);


        rentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent post = new Intent(OttInfoActivity.this,loginActivity.class);
                startActivity(post);

            }
        });


        ottbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                myref = rootNode.getReference("ottinfo");

                String ott_username = ottusrn.getEditText().getText().toString();
                String ott_pass = ottpwrd.getEditText().getText().toString();
                String ott_type = ottype.getEditText().getText().toString();

                OttHelperClass otthelperClass = new OttHelperClass(ott_username, ott_pass, ott_type);

                myref.child(ott_type).setValue(otthelperClass);

                Intent b = new Intent(OttInfoActivity.this, loginActivity.class);
                startActivity(b);

            }
        });



    }
}