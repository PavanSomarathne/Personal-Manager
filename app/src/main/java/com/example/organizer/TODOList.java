package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.organizer.Database.DatabaseHelper;
import com.example.organizer.Database.TODOModel;

import java.util.ArrayList;

public class TODOView extends AppCompatActivity {

    TableLayout table_tb;
    SearchView sv_search;
    private DatabaseHelper dbHelper;
    private ArrayList<TODOModel> todoModelArrayList;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        dbHelper=new DatabaseHelper(this);
        todoModelArrayList=dbHelper.getTODOtasks();

        sv_search=findViewById(R.id.view_painting_search1);
        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent(TODOView.this, TODOSearch.class);
                intent.putExtra("item_title",sv_search.getQuery().toString());
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });




        table_tb=findViewById(R.id.view_table);
        //TableLayout table = (TableLayout) findViewById(R.id.view_table);
        table_tb.setStretchAllColumns(true);
        if( todoModelArrayList!=null) {
            for (int i = 0; i <  todoModelArrayList.size(); i++) {


                TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
                final String todoid = Integer.toString( todoModelArrayList.get(i).getTodoId());
                String task  =  todoModelArrayList.get(i).getTask();
                String location =  todoModelArrayList.get(i).getLocation();
                String status =  todoModelArrayList.get(i).getStatus();

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TODOView.this, UpdateTODO.class);
                        intent.putExtra("todo_id", todoid);
                        startActivity(intent);
                    }
                });

                TextView tvid = new TextView(this);
                tvid.setText("    " + todoid);
                tvid.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
                TextView tvtask = new TextView(this);
                tvtask.setText("" + task);
                tvtask.setTextAppearance(getApplicationContext(), R.style.table_row_tView2);
                TextView tvstatus = new TextView(this);
                tvstatus.setText("" + status);
                tvstatus.setTextAppearance(getApplicationContext(), R.style.table_row_tView3);


                row.setBottom(2);

                row.addView(tvid);
                row.addView(tvtask);
                row.addView(tvstatus);
                table_tb.addView(row);
            }
        }else{
            TableRow rowMsg = new TableRow(this);
            rowMsg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView tvmsg = new TextView(this);
            tvmsg.setText("No Items in Cart");
            tvmsg.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
            tvmsg.setGravity(Gravity.CENTER);
            rowMsg.addView(tvmsg);
            table_tb.addView(rowMsg);
        }
    }

}
