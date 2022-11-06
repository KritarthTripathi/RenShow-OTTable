package com.example.ottable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ottable.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private Button signin_screen;
    Button login_screen;
    TextInputLayout usrnme, passwordee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usrnme = findViewById(R.id.username);
        passwordee = findViewById(R.id.password);

        signin_screen = (Button) findViewById(R.id.signupb);
        signin_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this,SignUpActivity.class);
                startActivity(i);

            }
        });

        login_screen = (Button) findViewById(R.id.gobtn);
        login_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateusername() | !validatepass()){
                    return;
                }

                else{
                    isUser();

                }

            }
        });


    }

    private void isUser() {

        String userEnteredUsername = usrnme.getEditText().getText().toString().trim();
        String userEnteredPassword = passwordee.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkuser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    usrnme.setError(null);
                    usrnme.setErrorEnabled(false);


                    String passwordfromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordfromDB.equals(userEnteredPassword)){

                        String namefromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String phnomfromDB = snapshot.child(userEnteredUsername).child("phnom").getValue(String.class);
                        String upi_idfromDB = snapshot.child(userEnteredUsername).child("upi_id").getValue(String.class);
                        String usernamefromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);





                        Intent p = new Intent(getApplicationContext(),Userprofile.class);
                        p.putExtra("name",namefromDB);
                        p.putExtra("phoneNo",phnomfromDB);
                        p.putExtra("upi_id",upi_idfromDB);
                        p.putExtra("username",usernamefromDB);
                        p.putExtra("password",passwordfromDB);

                        startActivity(p);



                    }

                    else{
                        passwordee.setError("Wrong Password");
                        passwordee.requestFocus();
                    }
                }
                else{
                    usrnme.setError("No Such User Exists");
                    usrnme.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private boolean validateusername(){
        String val = usrnme.getEditText().getText().toString();
        String nowhitespaces = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            usrnme.setError("Field cannot be empty");
            return false;
        }

        else{
            usrnme.setError(null);
            usrnme.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatepass(){
        String val = passwordee.getEditText().getText().toString();

        if(val.isEmpty()){
            passwordee.setError("Field cannot be empty");
            return false;
        }
        else{
            passwordee.setError(null);
            return true;
        }
    }
}