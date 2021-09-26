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

public class AddActivity2 extends AppCompatActivity {

    EditText address,email,name,turl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        address=(EditText) findViewById(R.id.txtAddress);
        email=(EditText) findViewById(R.id.txtEmail);
        name=(EditText) findViewById(R.id.txtName);
        turl=(EditText) findViewById(R.id.txtImageUrl);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnBack=(Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void insertData()

    {
        Map<String,Object> map = new HashMap<>();
        map.put("address",address.getText().toString());
        map.put("email",email.getText().toString());
        map.put("name",name.getText().toString());
        map.put("turl",turl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("delivery").push()
              .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity2.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivity2.this, "Error While Insertion", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void clearAll()
    {
        address.setText("");
        email.setText("");
        name.setText("");
        turl.setText("");
    }
}