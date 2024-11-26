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
    TextView txtFullName, txtPhoneNumber, tensp, tongtien;
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
                // Nhận dữ liệu từ Intent


                Intent intent = getIntent();
                ArrayList<String> selectedProducts = intent.getStringArrayListExtra("selectedProducts");
                ArrayList<Integer> productImages = intent.getIntegerArrayListExtra("productImages");
                double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);

                String customerName = intent.getStringExtra("customerName");
                String customerPhone = intent.getStringExtra("customerPhone");
                String customerAddress = intent.getStringExtra("customerAddress");
                Intent intent1 = new Intent(thanhtoan.this, xacnhan.class);
                // Truyền dữ liệu qua Intent
                intent1.putStringArrayListExtra("selectedProducts", selectedProducts);
                intent1.putIntegerArrayListExtra("productImages", productImages);
                intent1.putExtra("totalPrice", totalPrice);
                intent1.putExtra("customerName", customerName);
                intent1.putExtra("customerPhone", customerPhone);
                intent1.putExtra("customerAddress", customerAddress);
                startActivity(intent1);

            }

        });


        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        ArrayList<String> selectedProducts = intent.getStringArrayListExtra("selectedProducts");
        ArrayList<Integer> productImages = intent.getIntegerArrayListExtra("productImages");
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);

        String customerName = intent.getStringExtra("customerName");
        String customerPhone = intent.getStringExtra("customerPhone");
        String customerAddress = intent.getStringExtra("customerAddress");

        // Hiển thị thông tin khách hàng
        TextView textCustomerName = findViewById(R.id.name);
        TextView textCustomerPhone = findViewById(R.id.phone);
        textCustomerName.setText(customerName);
        textCustomerPhone.setText(customerPhone);


        // Hiển thị tổng giá tiền
        TextView textTotalPrice = findViewById(R.id.tongtien);
        textTotalPrice.setText(String.format("Tổng tiền: %.2f VND", totalPrice));

        // Hiển thị sản phẩm
        LinearLayout productLayout = findViewById(R.id.sp);
        if (selectedProducts != null && productImages != null && selectedProducts.size() == productImages.size()) {
            for (int i = 0; i < selectedProducts.size(); i++) {
                View productView = getLayoutInflater().inflate(R.layout.item_product, null);

                TextView productName = productView.findViewById(R.id.productName);
                TextView productprice=productView.findViewById(R.id.productPrice);
                ImageView productImage = productView.findViewById(R.id.productImage);

                productName.setText(selectedProducts.get(i));
                productprice.setText(selectedProducts.get(i));
                productImage.setImageResource(productImages.get(i));

                productLayout.addView(productView);
            }
       /*List<giohangmodel> giohangList = (List<giohangmodel>) getIntent().getSerializableExtra("giohangList");
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
}



