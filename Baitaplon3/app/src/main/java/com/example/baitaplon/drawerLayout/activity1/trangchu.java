package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon.drawerLayout.adapter.ProductAdapter;
import com.example.baitaplon.R;
import com.example.baitaplon.drawerLayout.model.product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class trangchu extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView giohang,menuIcon;
    ProductAdapter productAdapter;
    List<product> productList;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Thiết lập giao diện là trangchu.xml
        setContentView(R.layout.drawerlayout);
        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//hiên thi hàng
        // Tạo danh sách sản phẩm
        productList = new ArrayList<>();
        productList.add(new product("iPhone 16 Pro Max 256 GB", "34.990.000 đ ", R.drawable.iphone16));
        productList.add(new product("Samsung Galaxy Z Flip4 5G 128GB", "11.9900.000 đ", R.drawable.samsungz));
        productList.add(new product("Xiaomi Poco M6 Pro 8GB 256GB", "5.990.000 đ", R.drawable.xiaomi));
        productList.add(new product("Honor X9B 5G 12GB-256GB","7.990.000 đ", R.drawable.honor));
        productList.add(new product("OPPO A3 6GB 128GB","4.690.000 đ", R.drawable.oppo));
        // mỗi list sẽ dc hiển thi như 1 item_sản phẩm
        productAdapter = new ProductAdapter(productList,this);
        recyclerView.setAdapter(productAdapter);

        giohang=findViewById(R.id.imageView12);
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(trangchu.this, giohang.class);
                startActivity(intent1);
            }
        });

        // Khởi tạo DrawerLayout
        drawerLayout = findViewById(R.id.drawerlayout);
        // Tìm kiếm icon menu
        menuIcon = findViewById(R.id.home);
        // Thiết lập sự kiện khi bấm vào icon menu để mở ngăn kéo (Drawer)
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {drawerLayout.openDrawer(GravityCompat.START); // Mở menu ở bên trái
            }
        });
        // Đặt sự kiện lắng nghe đóng ngăn kéo khi chạm vào bên ngoài hoặc kéo
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Xử lý khi ngăn kéo trượt
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Xử lý khi ngăn kéo được mở
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // Xử lý khi ngăn kéo được đóng
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Xử lý khi trạng thái ngăn kéo thay đổi
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Đóng Drawer nếu đang mở, nếu không thì hành vi mặc định là thoát ứng dụng
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START); // Đóng menu bên trái
        } else {
            super.onBackPressed(); // Hành vi mặc định của nút Back
        }

    }

}