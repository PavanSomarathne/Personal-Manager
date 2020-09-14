package com.example.pictza;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pictza.Database.DatabaseHelper;

public class AddFriend extends AppCompatActivity {
    EditText fname,lname,address,age;
    Spinner gender;
    Button add, add_showImage;
    final int IMAGE_REQUEST_CODE = 999;
    public static String imgPath;

    private DatabaseHelper dbHelper=new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        fname=findViewById(R.id.f_name);
        lname=findViewById(R.id.l_name);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);
        add = findViewById(R.id.btn_Add);
        gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        add_showImage = findViewById(R.id.btn_showImg);

        add_showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(AddFriend.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f_fname=fname.getText().toString();
                String f_lname=lname.getText().toString();
                String f_address=address.getText().toString();
                String f_gender=gender.getSelectedItem().toString();
                int f_age=Integer.parseInt(age.getText().toString());



                if(f_fname.equals("")|| f_lname.equals("")|| f_address.equals("")|| f_age==0){
                    Toast.makeText(AddFriend.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(dbHelper.addFriend(f_fname,f_lname, f_gender,f_age,f_address, imgPath)){
                    fname.setText("");
                    lname.setText("");
                    address.setText("");
                    age.setText("");

                    Toast.makeText(AddFriend.this,"Successfully Added",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(AddFriend.this,"Something went wrong",Toast.LENGTH_SHORT).show();
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
}