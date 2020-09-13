package com.example.pictza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ManageFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);
    }

    public void viewUsers(View view){

        Intent intent = new Intent(ManageFriends.this,AddFriend.class);
        startActivity(intent);

    }

    public void editUsers(View view){

        Intent intent = new Intent(ManageFriends.this, FriendList.class);
        startActivity(intent);

    }

    public void home(View view){

        Intent intent = new Intent(ManageFriends.this, HomeActivity.class);
        startActivity(intent);

    }

}
