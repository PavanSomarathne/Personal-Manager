package com.example.pictza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.EventModel;
import com.example.pictza.Database.PaintingModel;

import java.util.ArrayList;

public class PaintingEdit extends AppCompatActivity {

    EditText edeventName,eddate,edtime,edlocation_event;
    Button btnUpdate,btnRemove;


    String eventid;
    ArrayList<EventModel> eventArray;
    DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_edit);

        edeventName =findViewById(R.id.edeventname);
        eddate = findViewById(R.id.eddate);
        edtime = findViewById(R.id.edtime);
        edlocation_event = findViewById(R.id.edlocationevent);


        btnUpdate=findViewById(R.id.updateevent);
        btnRemove=findViewById(R.id.remove);


        eventid=getIntent().getStringExtra("event_id");
        eventArray= dbHelper.getEvent(eventid);

        final int eventid=eventArray.get(0).getEventId();
        String eventname=eventArray.get(0).getEventName();
        String eventdate=eventArray.get(0).getDate();
        String eventtime=eventArray.get(0).getTime();
        String eventlocation=eventArray.get(0).getLocation_event();


        edeventName.setText(""+eventname);
        eddate.setText(""+eventdate);
        edtime.setText(""+eventtime);
        edlocation_event.setText(""+eventlocation);



        final String new_eventname=edeventName.getText().toString();
        final String new_eventdate=eddate.getText().toString();
        final String new_eventtime=edtime.getText().toString();
        final String new_eventlocation =edlocation_event.getText().toString();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.updateEvent(eventid, edeventName.getText().toString(),eddate.getText().toString(), edtime.getText().toString(), edlocation_event.getText().toString())){
                    Toast.makeText(PaintingEdit.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(PaintingEdit.this,ManagePainting.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(PaintingEdit.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(PaintingEdit.this);
                alert_box.setMessage("Do You Really Want To Remove This Painting ?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.deleteEvent(eventid)){
                                    Toast.makeText(PaintingEdit.this,"Successfully Removed",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(PaintingEdit.this,ManagePainting.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(PaintingEdit.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
                AlertDialog alert = alert_box.create();
                alert.setTitle("Alert !!!");
                alert.show();

            }
        });


    }

}
