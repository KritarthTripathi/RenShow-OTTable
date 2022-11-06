package com.example.ottable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SignUpActivity extends AppCompatActivity {

    Button backtolbutton;
    Button supbutton;
    TextInputLayout regname, regusername, regpass, regphno, regupi;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backtolbutton = (Button) findViewById(R.id.backtologButton);
        supbutton = (Button) findViewById(R.id.SignUpButton);
        regname = findViewById(R.id.name);
        regusername = findViewById(R.id.username);
        regpass = findViewById(R.id.pass);
        regphno = findViewById(R.id.phno);
        regupi = findViewById(R.id.upi);





        backtolbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(SignUpActivity.this,loginActivity.class);
                startActivity(k);
            }
        });

        supbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");

                if(!validateName() | !validateNumber() | !validatepass() | !validateupi() | !validateusername()){
                    return;
                }

                String name = regname.getEditText().getText().toString();
                String username = regusername.getEditText().getText().toString();
                String phnom = regphno.getEditText().getText().toString();
                String upi_id = regupi.getEditText().getText().toString();
                String password = regpass.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, phnom, upi_id, password);

                reference.child(username).setValue(helperClass);

                Intent y = new Intent(SignUpActivity.this,OttInfoActivity.class);
                startActivity(y);

            }
        });


    }

    private boolean validateName(){
        String val = regname.getEditText().getText().toString();

        if(val.isEmpty()){
            regname.setError("Field cannot be empty");
            return false;
        }
        else{
            regname.setError(null);
            return true;
        }
    }

    private boolean validateusername(){
        String val = regusername.getEditText().getText().toString();
        String nowhitespaces = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regusername.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>15){
            regusername.setError("Username too long");
            return false;
        }

        else if(!val.matches(nowhitespaces)){
            regusername.setError("white spaces are not allowed");
            return false;
        }

        else{
            regusername.setError(null);
            regusername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateNumber(){
        String val = regphno.getEditText().getText().toString();

        if(val.isEmpty()){
            regphno.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()<10){
            regphno.setError("phone number Invalid");
            return false;
        }
        else if(val.length()>10){
            regphno.setError("phone number Invalid");
            return false;
        }

        else{
            regphno.setError(null);
            return true;
        }
    }

    private boolean validateupi(){
        String val = regupi.getEditText().getText().toString();

        if(val.isEmpty()){
            regupi.setError("Field cannot be empty");
            return false;
        }
        else{
            regupi.setError(null);
            return true;
        }
    }

    private boolean validatepass(){
        String val = regpass.getEditText().getText().toString();

        if(val.isEmpty()){
            regpass.setError("Field cannot be empty");
            return false;
        }
        else{
            regpass.setError(null);
            return true;
        }
    }


}