package com.example.pictza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ManageGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_gallery);
    }

    public void openaddshow(View view) {
        Intent intent = new Intent(ManageGallery.this, AddImage.class);
        startActivity(intent);
    }

    public void openmanageshow(View view) {
        Intent intent = new Intent(ManageGallery.this, Gallery.class);
        startActivity(intent);
    }
    public void home(View view){

        Intent intent = new Intent(ManageGallery.this, HomeActivity.class);
        startActivity(intent);

    }

}
