package com.example.ottable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class NetflixActivity extends AppCompatActivity implements PaymentResultListener {


    MaterialCardView offerone, offertwo, offerthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix);


        offerone = findViewById(R.id.offer1);
        offertwo = findViewById(R.id.offer2);
        offerthree = findViewById(R.id.offer3);

        offerone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offerone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startPayment();
                    }
                });

            }
        });



    }


    public void startPayment() {

        Checkout checkout = new Checkout();

        checkout.setImage(R.mipmap.ic_launcher);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();
            options.put("name", R.string.app_name);
            options.put("description", "Payment for Netflix rental");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", false);


            options.put("currency", "INR");
            options.put("amount", "2000");

            JSONObject preFill = new JSONObject();
            preFill.put("contact", "");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

    }


    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(this,"Payment Success! "+s, Toast.LENGTH_SHORT).show();

        //new

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ottinfo");
        Query checkott = reference.orderByChild("ott_type").equalTo("Netflix");

        checkott.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String rentOTTUsername = snapshot.child("Netflix").child("ott_username").getValue(String.class);
                    String rentOTTPassword = snapshot.child("Netflix").child("ott_pass").getValue(String.class);
                    String rentOTTType = "Netflix";


                    Intent got_ott = new Intent(NetflixActivity.this,ManageOttActivity.class);
                    got_ott.putExtra("der_username",rentOTTUsername);
                    got_ott.putExtra("der_password",rentOTTPassword);
                    got_ott.putExtra("buy_type",rentOTTType);

                    startActivity(got_ott);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this,"Payment Error ! "+s, Toast.LENGTH_SHORT).show();

    }
}