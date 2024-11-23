package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon.R;

public class chitietsanpham extends AppCompatActivity {
    ImageButton back;
    ImageView imageView,giohang,trangchu;
    TextView textViewName, textViewPrice;
    Button themgiohang;

    private ImageView productImage;
    private TextView productName, productPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham);
        back = findViewById(R.id.btnBack);
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        themgiohang = findViewById(R.id.btnAddToCart);

        //Set dữ liệu
        Intent intent = getIntent();
        int imageResource = intent.getIntExtra("productImage", R.drawable.bell);// nêú không có giá tri nào dc gửi thì sả trả về -1
        String name = intent.getStringExtra("productName");
        String price = intent.getStringExtra("productPrice");

        productName.setText(name);
        productImage.setImageResource(imageResource);
        productPrice.setText(price);

        //
        giohang=findViewById(R.id.imageView12);
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(chitietsanpham.this, giohang.class);
                startActivity(intent1);
            }
        });
        trangchu=findViewById(R.id.imageView8);
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(chitietsanpham.this, trangchu.class);
                startActivity(intent1);
            }
        });

        // sử lí sự kiên nút trở về
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tao intent để chuyển đi
                Intent intent = new Intent(chitietsanpham.this, giohang.class);
                intent.putExtra("imgResource", imageResource);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                intent.putExtra("quantity", 1);
                startActivity(intent);
            }
        });
    }

}