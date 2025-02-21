package com.example.myapplication.hunger_equity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.hunger_equity.model.Charity;
import com.example.myapplication.hunger_equity.model.Donor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorProfileEdit extends AppCompatActivity {
    TextView name;
    EditText email,password,address,organ,phone;
    Button submit;
    DatabaseReference mFirebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile_edit);
        name=(TextView)findViewById(R.id.donor_update_name);
        email=(EditText)findViewById(R.id.donor_update_email);
        password=(EditText)findViewById(R.id.donor_update_password);
        address=(EditText) findViewById(R.id.donor_update_address);
        phone=(EditText)findViewById(R.id.donor_update_phone);
        submit=(Button)findViewById(R.id.donor_update_submit);
        ImageButton back=(ImageButton)findViewById(R.id.donor_profile_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonorProfileEdit.this.finish();
            }
        });

        Intent gIntent=getIntent();
        String userName=gIntent.getStringExtra("name");
        String userEmail=gIntent.getStringExtra("email");
        String userPhone=gIntent.getStringExtra("phone");
        String userAddress=gIntent.getStringExtra("address");
        String userPassword=gIntent.getStringExtra("password");

        name.setText(userName);
        email.setText(userEmail);
        address.setText(userAddress);
        phone.setText(userPhone);

        mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("a_donor");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(userPassword.equals(password.getText().toString())) {
                    String key = userName;
                    Donor donor = new Donor(address.getText().toString(), email.getText().toString(), userName,
                            userPassword,
                            phone.getText().toString()
                    );
                    mFirebaseDatabase.child(key).setValue(donor);
                    DonorProfileEdit.this.finish();
                }
                else
                {
                    password.setError("Wrong Password");
                }
            }
        });



    }
}