package com.example.pictza;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pictza.Database.DatabaseHelper;

public class UploadPainting extends AppCompatActivity {

    EditText  eventName,date,time,location;
    Button btnAdd;



    private DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_painting);

        eventName =findViewById(R.id.eventname);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        location = findViewById(R.id.locationevent);
        btnAdd=findViewById(R.id.addevent);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_eventname=eventName.getText().toString();
                String et_date=date.getText().toString();
                String et_time=time.getText().toString();
                String et_location=time.getText().toString();


               /* if(dbHelper.addEvent(et_eventname,et_date,et_time,et_location)){

                    Toast.makeText(UploadPainting.this,"Successfully Added",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(UploadPainting.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }*/

            }
        });



    }



    public void home(View view){

        Intent intent = new Intent(UploadPainting.this, paintings.class);
        startActivity(intent);

    }


}
