package com.example.pictza;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.FriendModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FriendEdit extends AppCompatActivity {

    EditText fname,lname,address,age;
    Spinner gender;
    Button btnUpdate,btnRemove,imgup;
    ImageView upFriend;
    String id;
    ArrayList<FriendModel> friendArray;
    final int IMAGE_REQUEST_CODE = 999;
    public static String imgPath;
    DatabaseHelper dbHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_edit);

        fname=findViewById(R.id.f_name);
        lname=findViewById(R.id.l_name);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);
        upFriend=findViewById(R.id.up_imageFriend);
        imgup=findViewById(R.id.btn_showImg);

        gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        imgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(FriendEdit.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            }
        });


        btnUpdate=findViewById(R.id.btn_Update);
        btnRemove=findViewById(R.id.btn_Remove);

        id=getIntent().getStringExtra("friend_id");
        friendArray= dbHelper.getFriend(id);

        final int fid=friendArray.get(0).getFid();
        String d_fname=friendArray.get(0).getfName();
        String d_lname=friendArray.get(0).getlName();
        String d_gender=friendArray.get(0).getGender();
        String d_address=friendArray.get(0).getAddress();
        int d_age=friendArray.get(0).getAge();
        final byte[][] d_image = {friendArray.get(0).getImage()};

       fname.setText(d_fname);
        lname.setText(d_lname);

        if (d_gender != null) {
            int spinnerPosition = adapter.getPosition(d_gender);
            gender.setSelection(spinnerPosition);
        }
        address.setText(d_address);
        age.setText(String.valueOf(d_age));

        Bitmap bitmap = BitmapFactory.decodeByteArray(d_image[0],0, d_image[0].length);
        upFriend.setImageBitmap(bitmap);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f_fname=fname.getText().toString();
                String f_lname=lname.getText().toString();
                String f_address=address.getText().toString();
                String f_gender=gender.getSelectedItem().toString();
                int f_age=Integer.parseInt(age.getText().toString());


                if(f_fname.equals("")|| f_lname.equals("")|| f_address.equals("")|| f_age==0){
                    Toast.makeText(FriendEdit.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(imgPath!=null){
                    FileInputStream fs = null;
                    try {
                        fs = new FileInputStream(imgPath);
                        byte[] imgbyte = new byte[fs.available()];
                        fs.read(imgbyte);
                        d_image[0] =imgbyte;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if(dbHelper.updateFriend(fid,f_fname,f_lname, f_gender,f_age,f_address, d_image[0])){
                    fname.setText("");
                    lname.setText("");
                    address.setText("");
                    age.setText("");
                    upFriend.setImageBitmap(null);
                    Toast.makeText(FriendEdit.this,"Successfully Updated",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(FriendEdit.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(FriendEdit.this);
                alert_box.setMessage("Do You Really Want To Remove This Friend ?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.deleteFriend(fid)){
                                    Toast.makeText(FriendEdit.this,"Successfully Removed",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(FriendEdit.this, FriendList.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(FriendEdit.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();

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
                upFriend.setImageBitmap(bitmap);
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
}