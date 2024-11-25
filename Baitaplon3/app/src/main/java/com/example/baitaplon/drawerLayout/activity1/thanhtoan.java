package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon.R;
import com.example.baitaplon.drawerLayout.model.giohangmodel;

import java.util.ArrayList;
import java.util.List;

public class thanhtoan extends AppCompatActivity {
    ImageView trangchu, giohang, thoat;
    TextView txtFullName, txtPhoneNumber;
    Button tieptuc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan);
        //
        thoat = findViewById(R.id.image2);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(thanhtoan.this, giaohang.class);
                startActivity(intent1);
            }

        });
        trangchu = findViewById(R.id.imageView8);
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(thanhtoan.this, trangchu.class);
                startActivity(intent1);
            }

        });
        giohang = findViewById(R.id.imageView12);
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(thanhtoan.this, giohang.class);
                startActivity(intent1);
            }

        });
        tieptuc = findViewById(R.id.btmua);
        tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(thanhtoan.this, xacnhan.class);
                startActivity(intent1);
            }

        });


       // Ánh xạ các TextView
        txtFullName = findViewById(R.id.name);
        txtPhoneNumber = findViewById(R.id.phone);
        //lấy dữ liệu từ ShareParereferences
        SharedPreferences sharedPreferences = getSharedPreferences("giohang_prefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "Không có thông tin");
        String phone = sharedPreferences.getString("phone", "Không có thông tin");
        // Hiển thị thông tin trên UI
        txtFullName.setText(name);
        txtPhoneNumber.setText(phone);

       /* List<giohangmodel> giohangList = (List<giohangmodel>) getIntent().getSerializableExtra("giohangList");
        // Hiển thị sản phẩm trong giỏ hàng
        if (giohangList != null && !giohangList.isEmpty()) {
            showProducts(giohangList);
        }


    }
    private void showSelectionDialog(String title, String[] options, AdapterView.OnItemSelectedListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(options, (dialog, which) -> listener.onItemSelected(options[which]));
        builder.show();
    }

    private void navigateToActivity(Class<?> cls) {
        Intent intent = new Intent(thanhtoan.this, cls);
        startActivity(intent);
    }*/
    }
}
