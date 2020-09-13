package com.example.pictza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pictza.Database.CustomerModel;
import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.FriendModel;

import java.util.ArrayList;

public class FriendEditDelete extends AppCompatActivity {

    EditText fname,lname,address,age;
    Spinner gender;
    Button btnUpdate,btnRemove;
    ImageView upFriend;
    String id;
    ArrayList<FriendModel> friendArray;
    DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_edit_delete);

        fname=findViewById(R.id.f_name);
        lname=findViewById(R.id.l_name);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);
        upFriend=findViewById(R.id.up_imageFriend);

        gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        btnUpdate=findViewById(R.id.btn_Update);
        btnRemove=findViewById(R.id.btn_Remove);


        id=getIntent().getStringExtra("friend_id");

        //friendArray= dbHelper.getFriend(id);

//        final int id=friendArray.get(0).getFid();
//        String d_fname=friendArray.get(0).getfName();
//        String d_lname=friendArray.get(0).getlName();
//        String d_gender=friendArray.get(0).getGender();
//        String d_address=friendArray.get(0).getAddress();
//        int d_age=friendArray.get(0).getAge();
        //byte[] d_image=friendArray.get(0).getImage();

        //fname.setText(id);
//        lname.setText(d_lname);
//        //gender.setSelectedI(d_fname);
//        address.setText(d_address);
//        age.setText(d_age);

//        Bitmap bitmap = BitmapFactory.decodeByteArray(d_image,0,d_image.length);
//        upFriend.setImageBitmap(bitmap);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(dbHelper.updateCustomer(id, upName.getText().toString(),upEmail.getText().toString(), upPassword.getText().toString())){
//                    Toast.makeText(CustomerEditDelete.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(CustomerEditDelete.this,auction.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(CustomerEditDelete.this,"Something went wrong",Toast.LENGTH_SHORT).show();
//                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert_box=new AlertDialog.Builder(FriendEditDelete.this);
//                alert_box.setMessage("Do You Really Want To Remove This Customer ?").setCancelable(true)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if(dbHelper.deleteCustomer(id)){
//                                    Toast.makeText(FriendEditDelete.this,"Successfully Removed",Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(FriendEditDelete.this, FriendList.class);
//                                    startActivity(intent);
//                                }else {
//                                    Toast.makeText(FriendEditDelete.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        }).setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                }) ;
//                AlertDialog alert = alert_box.create();
//                alert.setTitle("Alert !!!");
//                alert.show();

            }
        });


    }
}
