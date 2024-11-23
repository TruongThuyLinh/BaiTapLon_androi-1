package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.baitaplon.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout main;
    boolean ischanged=false;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tao handler dể thực hin hành đông sau 30 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!ischanged) {
                    Intent intent = new Intent(MainActivity.this, trangchu.class);
                    startActivity(intent);
                    //kết thúc activity hiên tai
                    finish();
                }
            }
        },3000);
        main=findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischanged=true;
                Intent intent=new Intent(MainActivity.this,trangchu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}