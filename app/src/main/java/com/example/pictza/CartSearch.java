package com.example.pictza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.PaintingModel;

import java.util.ArrayList;

public class CartSearch extends AppCompatActivity {




    EditText  task,status,location;
    Button btnAdd;
    String pid;

    DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_search);

        task =findViewById(R.id.task);
        status = findViewById(R.id.status);
        location = findViewById(R.id.location);
        btnAdd=findViewById(R.id.add_todo);





        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_task=task.getText().toString();
                String et_location=location.getText().toString();
                String et_status=status.getText().toString();


                if(dbHelper.addTODO(et_task, et_location, et_status)){

                    Toast.makeText(CartSearch.this,"Successfully Added",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(CartSearch.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    public void back(View view){

        Intent intent = new Intent(CartSearch.this, AddOrCart.class);
        startActivity(intent);

    }

}
