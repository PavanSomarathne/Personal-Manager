package com.example.pictza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFriend extends AppCompatActivity {
    EditText fname,lname,address,age,gender;

    Button add, add_showImage;
    final int IMAGE_REQUEST_CODE = 999;
    public static String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        fname=findViewById(R.id.f_name);
        lname=findViewById(R.id.l_name);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);

        add_showImage = findViewById(R.id.btn_showImg);

        add_showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(AddFriend.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            }
        });

    }
}