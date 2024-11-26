package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon.R;

import java.util.ArrayList;

public class xacnhan extends AppCompatActivity {
    ImageView thoat, trangchu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xacnhan);
        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        ArrayList<String> selectedProducts = intent.getStringArrayListExtra("selectedProducts");
        ArrayList<Integer> productImages = intent.getIntegerArrayListExtra("productImages");
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);

        // Hiển thị tổng tiền
        TextView textTotalPrice = findViewById(R.id.tongtien);
        textTotalPrice.setText(String.format("Tổng tiền: %.2f VND", totalPrice));

        // Hiển thị danh sách sản phẩm
        LinearLayout productLayout = findViewById(R.id.sp);
        if (selectedProducts != null && productImages != null && selectedProducts.size() == productImages.size()) {
            for (int i = 0; i < selectedProducts.size(); i++) {
                View productView = getLayoutInflater().inflate(R.layout.item_product, null);

                TextView productName = productView.findViewById(R.id.productName);
                ImageView productImage = productView.findViewById(R.id.productImage);

                productName.setText(selectedProducts.get(i));
                productImage.setImageResource(productImages.get(i));

                productLayout.addView(productView);

            }
        }


        thoat = findViewById(R.id.backButton);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(xacnhan.this, thanhtoan.class);
                startActivity(intent1);
            }

        });
        trangchu = findViewById(R.id.imageView8);
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(xacnhan.this, trangchu.class);
                startActivity(intent1);
            }

        });

    }
}

