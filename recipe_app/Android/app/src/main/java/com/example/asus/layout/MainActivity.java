package com.example.asus.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        b1=(Button)findViewById(R.id.bLogin);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                Intent in= new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(in);
//
//            }
//        });

    }
}
