package com.example.pictza;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    ImageView user;
    TextView usertxt;
    int count=0;

    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView user = findViewById(R.id.imageView42);
        TextView usertxt = findViewById(R.id.textView70);


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, ManageFriends.class);
                    startActivity(intent);
                    count++;
            }
        });

        usertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    public void logout(View view) {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void opencart(View view) {
        Intent intent = new Intent(HomeActivity.this, AddOrCart.class);
        startActivity(intent);
    }

    public void shows(View view) {
        Intent intent = new Intent(HomeActivity.this, viewOrEdit.class);
        startActivity(intent);
    }

    public void opensearch(View view) {
        Intent intent = new Intent(HomeActivity.this, ManagePainting.class);
        startActivity(intent);
    }

    public void openpaint(View view) {
        Intent intent = new Intent(HomeActivity.this, paintings.class);
        startActivity(intent);
    }

}
