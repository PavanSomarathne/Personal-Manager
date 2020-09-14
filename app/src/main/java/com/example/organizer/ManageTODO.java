package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManageTODO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_todo);
    }

    public void home(View view){

        Intent intent = new Intent(ManageTODO.this, HomeActivity.class);
        startActivity(intent);

    }

    public void addtocart(View view) {
        Intent intent = new Intent(ManageTODO.this, AddTODO.class);
        startActivity(intent);
    }

    public void viewcart(View view) {
        Intent intent = new Intent(ManageTODO.this, TODOList.class);
        startActivity(intent);
    }

}
