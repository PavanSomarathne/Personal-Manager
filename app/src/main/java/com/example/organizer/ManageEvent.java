package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paintings);
    }

    public void home(View view){

        Intent intent = new Intent(event.this, HomeActivity.class);
        startActivity(intent);

    }
    public void add(View view){

        Intent intent = new Intent(event.this, AddEvent.class);
        startActivity(intent);

    }
    public void manage(View view){

        Intent intent = new Intent(event.this, ManageEvent.class);
        startActivity(intent);

    }
}