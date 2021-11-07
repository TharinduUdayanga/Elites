package com.example.food_app;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name,type,purl,price,site;
    Button btnAdd,btnBack;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.txtName);
        type = (EditText)findViewById(R.id.txtType);
        purl = (EditText)findViewById(R.id.txtImageUrl);
        price = (EditText)findViewById(R.id.txtOwner);


        btnAdd = (Button)findViewById(R.id.btnAdd);

        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //For Name
        awesomeValidation.addValidation(this,R.id.txtName,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        //For Category
        awesomeValidation.addValidation(this,R.id.txtType,
                RegexTemplate.NOT_EMPTY,R.string.invalid_type);

        //For image
        awesomeValidation.addValidation(this,R.id.txtImageUrl,
                RegexTemplate.NOT_EMPTY,R.string.invalid_image);


        //For price
        awesomeValidation.addValidation(this,R.id.txtOwner,
                "[0-9]{3}$",R.string.invalide_quantity);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check validation
                if (awesomeValidation.validate()) {
                    insertData();
                    clearAll();


                }else{
                    Toast.makeText(getApplicationContext()
                            ,"Validation Failed",Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("type",type.getText().toString());
        map.put("purl",purl.getText().toString());
        map.put("price",price.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Food").push()
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
        price.setText("");


    }

}