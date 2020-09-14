package com.example.pictza;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.pictza.Database.DatabaseHelper;
import com.example.pictza.Database.FriendModel;

import java.util.ArrayList;

public class FriendList extends AppCompatActivity {

    TableLayout table_tb;
    SearchView sv_search;
    private DatabaseHelper dbHelper;
    private ArrayList<FriendModel> friendModelArrylist;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        dbHelper=new DatabaseHelper(this);
        friendModelArrylist=dbHelper.getAllFriends();

        sv_search=findViewById(R.id.view_painting_search1);
        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent=new Intent(FriendList.this,FriendListSearch.class);
                intent.putExtra("friend_name",sv_search.getQuery().toString());
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
        if(friendModelArrylist!=null) {
            for (int i = 0; i < friendModelArrylist.size(); i++) {


                TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.parseColor("#FFFFFF"));
                final String id = Integer.toString(friendModelArrylist.get(i).getFid());
                String fName = friendModelArrylist.get(i).getfName();
                String lName = friendModelArrylist.get(i).getlName();
                String gender = friendModelArrylist.get(i).getGender();

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FriendList.this, FriendEdit.class);
                        intent.putExtra("friend_id", id);
                        startActivity(intent);
                    }
                });

                TextView tvid = new TextView(this);
                tvid.setText("    " + fName);
                tvid.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
                TextView tvname = new TextView(this);
                tvname.setText("" + lName);
                tvname.setTextAppearance(getApplicationContext(), R.style.table_row_tView2);
                TextView tvemail = new TextView(this);
                tvemail.setText("" + gender);
                tvemail.setTextAppearance(getApplicationContext(), R.style.table_row_tView3);



                row.setBottom(2);

                row.addView(tvid);
                row.addView(tvname);
                row.addView(tvemail);
                table_tb.addView(row);
            }
        }else{
            TableRow rowMsg = new TableRow(this);
            rowMsg.setBackgroundColor(Color.parseColor("#FFFFFF"));
            TextView tvmsg = new TextView(this);
            tvmsg.setText("No Friends");
            tvmsg.setTextAppearance(getApplicationContext(), R.style.table_row_tView1);
            tvmsg.setGravity(Gravity.CENTER);
            rowMsg.addView(tvmsg);
            table_tb.addView(rowMsg);
        }
    }


}
