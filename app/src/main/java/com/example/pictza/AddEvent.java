package com.example.pictza;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pictza.Database.DatabaseHelper;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

    EditText  eventName,date,time,location_event;
    Button btnAdd;
    DatePickerDialog picker;

    String amPm;



    private DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_painting);

        eventName =findViewById(R.id.eventname);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        location_event = findViewById(R.id.locationevent);
        btnAdd=findViewById(R.id.addevent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            date.setShowSoftInputOnFocus(false);
            time.setShowSoftInputOnFocus(false);
        }



        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {


                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_eventname=eventName.getText().toString();
                String et_date=date.getText().toString();
                String et_time=time.getText().toString();
                String et_location=time.getText().toString();


               if(dbHelper.addEvent(et_eventname,et_date,et_time,et_location)){

                    Toast.makeText(AddEvent.this,"Successfully Added",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(AddEvent.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddEvent.this,
                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



    }



    public void home(View view){

        Intent intent = new Intent(AddEvent.this, event.class);
        startActivity(intent);

    }


}
