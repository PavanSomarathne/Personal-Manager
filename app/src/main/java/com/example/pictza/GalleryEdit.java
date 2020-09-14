package com.example.pictza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.PhotoModel;

import java.util.ArrayList;

public class GalleryEdit extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ImageView imageView;
    Button delete;
    ArrayList<PhotoModel> photoArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_edit);
         imageView = findViewById(R.id.full_image_view);
         delete = findViewById(R.id.remove_img);
        dbHelper=new DatabaseHelper(this);


        // get intent data
        final String p_id=getIntent().getStringExtra("id");

        photoArray= dbHelper.getPhoto(p_id);
        if(photoArray!=null){
            byte[] d_image = photoArray.get(0).getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(d_image, 0, d_image.length);

            imageView.setImageBitmap(bitmap);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(GalleryEdit.this);
                alert_box.setMessage("Do You Really Want To Remove This Image ?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.deletePhoto(p_id)){
                                    Toast.makeText(GalleryEdit.this,"Successfully Removed",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(GalleryEdit.this, Gallery.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(GalleryEdit.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();

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