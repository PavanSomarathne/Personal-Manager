package com.example.pictza;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pictza.Database.DatabaseHelper;

import java.io.FileInputStream;

public class AddImage extends AppCompatActivity {

    EditText imgName;
    Button addImage, add_showImage;
    ImageView imageView;

    final int IMAGE_REQUEST_CODE = 999;
    public static String imgPath;

    private DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        imageView = findViewById(R.id.imageView5);
        imgName=findViewById(R.id.img_name);
        add_showImage = findViewById(R.id.btn_showImg);

        add_showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(AddImage.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            }
        });

        addImage=findViewById(R.id.update);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_name=imgName.getText().toString();

                if(et_name.equals("")){
                    Toast.makeText(AddImage.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(dbHelper.addPhoto(et_name, imgPath)){
                    imgName.setText("");
                    Toast.makeText(AddImage.this,"Successfully Added",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddImage.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == IMAGE_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }else{
                Toast.makeText(getApplicationContext(),"You do not have permission to access gallery",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            String path = getPath(uri);
            imgPath = getPath(uri);
            //   System.out.println(path);
            //Toast.makeText(getApplicationContext(),path,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Image Selected Successfully",Toast.LENGTH_SHORT).show();

            FileInputStream fs = null;
            try {
                fs = new FileInputStream(imgPath);
                byte[] imgbyte = new byte[fs.available()];
                fs.read(imgbyte);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte,0, imgbyte.length);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public String getPath(Uri uri){
        if(uri==null){
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection,null,null,null);
        if(cursor!=null){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

    public void home(View view){

        Intent intent = new Intent(AddImage.this, ManageGallery.class);
        startActivity(intent);

    }
}
