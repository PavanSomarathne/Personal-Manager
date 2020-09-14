package com.example.pictza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pictza.Database.DatabaseHelper;

public class AddTODO extends AppCompatActivity {


    Spinner status;

    EditText  task,location;
    Button btnAdd;
    String pid;

    DatabaseHelper dbHelper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_search);
        status = (Spinner) findViewById(R.id.status);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);
        task =findViewById(R.id.task);
       // status = findViewById(R.id.status);

        location = findViewById(R.id.location);
        btnAdd=findViewById(R.id.add_todo);





        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String et_task=task.getText().toString();
                String et_location=location.getText().toString();
                String et_status=status.getSelectedItem().toString();


                if(dbHelper.addTODO(et_task, et_location, et_status)){

                    Toast.makeText(AddTODO.this,"Successfully Added",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(AddTODO.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    public void back(View view){

        Intent intent = new Intent(AddTODO.this, AddOrCart.class);
        startActivity(intent);

    }

}
