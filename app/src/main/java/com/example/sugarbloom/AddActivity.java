package com.example.sugarbloom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name,type,purl,owner,age,phone,email,city,date;
    Button btnAdd,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.txtName);
        type = (EditText)findViewById(R.id.txtType);
        purl = (EditText)findViewById(R.id.txtImageUrl);
        owner = (EditText)findViewById(R.id.txtOwner);
        age = (EditText)findViewById(R.id.txtAge);
        phone = (EditText)findViewById(R.id.txtPhone);
        email = (EditText)findViewById(R.id.txtEmail);
        city = (EditText)findViewById(R.id.txtCity);
        date = (EditText)findViewById(R.id.txtDate);



        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();


            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("type",type.getText().toString());
        map.put("purl",purl.getText().toString());
        map.put("owner",owner.getText().toString());
        map.put("age",age.getText().toString());
        map.put("phone",phone.getText().toString());
        map.put("email",email.getText().toString());
        map.put("city",city.getText().toString());
        map.put("date",date.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Orders").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error While Inserting Data", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void clearAll()
    {
        name.setText("");
        type.setText("");
        purl.setText("");
        owner.setText("");
        age.setText("");
        phone.setText("");
        email.setText("");
        city.setText("");
        date.setText("");
    }

}