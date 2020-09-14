package com.example.pictza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.TODOModel;

import java.util.ArrayList;

public class TODOSearch extends AppCompatActivity {

    TableLayout tb_search;
    SearchView sv_search;
    private DatabaseHelper dbHelper;
    private ArrayList<TODOModel> todoModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view_search);


        dbHelper=new DatabaseHelper(this);
        String i_title=getIntent().getStringExtra("item_title");
        todoModelArrayList=dbHelper.searchTodo(i_title);

        sv_search=findViewById(R.id.view_drug_search2);
        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent(TODOSearch.this, TODOSearch.class);
                intent.putExtra("item_title",sv_search.getQuery().toString());
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        tb_search=findViewById(R.id.view_table);
        //TableLayout table = (TableLayout) findViewById(R.id.view_table);
        tb_search.setStretchAllColumns(true);
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
                        Intent intent = new Intent(TODOSearch.this, UpdateTODO.class);
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
                TextView tvlocation = new TextView(this);
                tvlocation.setText("" + location);
                tvlocation.setTextAppearance(getApplicationContext(), R.style.table_row_tView3);
                TextView tvstatus = new TextView(this);
                tvstatus.setText("" + status);
                tvstatus.setTextAppearance(getApplicationContext(), R.style.table_row_tView3);


                row.setBottom(2);

                row.addView(tvid);
                row.addView(tvtask);
                row.addView(tvlocation);
                row.addView(tvstatus);
                tb_search.addView(row);
            }
        }else{
            TableRow rowMsg = new TableRow(this);
            rowMsg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView tvmsg = new TextView(this);
            tvmsg.setText("No Items in Cart");
            tvmsg.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
            tvmsg.setGravity(Gravity.CENTER);
            rowMsg.addView(tvmsg);
            tb_search.addView(rowMsg);
        }
    }

}
