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


import com.example.pictza.Database.CartModel;
import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.PaintingModel;
import com.example.pictza.Database.TODOModel;

import java.util.ArrayList;

public class CartEdit extends AppCompatActivity {


    EditText edtask,edstatus,edlocation;
    Button btnUpdate,btnRemove;
    String todoId;

    ArrayList<TODOModel> todoArray;
    DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_edit);

        edtask =findViewById(R.id.edtask);
        edstatus = findViewById(R.id.edstatus);
        edlocation = findViewById(R.id.edlocation);




        btnUpdate=findViewById(R.id.update_todo);
        btnRemove=findViewById(R.id.remove_todo);


        todoId =getIntent().getStringExtra("todo_id");
        todoArray= dbHelper.getTODOtask(todoId);

        final int todoid =todoArray.get(0).getTodoId();
        String task=todoArray.get(0).getTask();
        String status=todoArray.get(0).getStatus();
        String location=todoArray.get(0).getLocation();



        edtask.setText(""+task);
        edstatus.setText(""+status);
        edlocation.setText(""+location);


 final String new_task = edtask.getText().toString();
final String new_status = edstatus.getText().toString();
final String new_location = edlocation.getText().toString();




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.updateTODO(todoId, edtask.getText().toString(),edstatus.getText().toString(), edlocation.getText().toString())){
                    Toast.makeText(CartEdit.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CartEdit.this,CartView.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(CartEdit.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_box=new AlertDialog.Builder(CartEdit.this);
                alert_box.setMessage("Do You Really Want To Remove This Item ?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbHelper.deleteTODO(todoId)){
                                    Toast.makeText(CartEdit.this,"Successfully Removed",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(CartEdit.this,CartView.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(CartEdit.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();

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
