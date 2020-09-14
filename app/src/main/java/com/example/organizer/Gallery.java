package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.organizer.Database.DatabaseHelper;
import com.example.organizer.Database.PhotoModel;

import java.util.ArrayList;


public class Gallery extends AppCompatActivity {
    private GridView imageGrid;
    private ArrayList<Bitmap> bitmapList;
    int sid=0;
    DatabaseHelper dbHelper;
    ArrayList<PhotoModel> photoArray;
    ArrayList<Integer> idArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        this.imageGrid = (GridView) findViewById(R.id.gridview);
        this.bitmapList = new ArrayList<Bitmap>();
        idArray=new ArrayList<Integer>();
        dbHelper=new DatabaseHelper(this);

        photoArray= dbHelper.getPhotos();

       // Log.d("list", friendArray.get(0).getImage().toString());
        //Bitmap bitmap = BitmapFactory.decodeByteArray(d_image, 0, d_image.length);

            if(photoArray!=null) {
                for (int i = 0; i < photoArray.size(); i++) {
                    byte[] d_image = photoArray.get(i).getImage();
                    sid=photoArray.get(i).getSid();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(d_image, 0, d_image.length);
                    idArray.add(sid);
                    this.bitmapList.add(bitmap);
                }
            }


        this.imageGrid.setAdapter(new ImageAdapter(this, this.bitmapList));
        // Set an item click listener for grid view
        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String val=String.valueOf(idArray.get(position));
                Intent intent=new Intent(Gallery.this,GalleryEdit.class);
                intent.putExtra("id",val);
                startActivity(intent);

            }
        });
    }
}