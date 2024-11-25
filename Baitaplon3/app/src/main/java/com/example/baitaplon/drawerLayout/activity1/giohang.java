package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon.R;
import com.example.baitaplon.drawerLayout.adapter.GiohangAdapter;
import com.example.baitaplon.drawerLayout.model.giohangmodel;

import java.util.ArrayList;
import java.util.List;


public class giohang extends AppCompatActivity {

    private RecyclerView recyclergiohang;
    ImageView back,giohang,trangchu;
    private TextView tongtien;
    Button muahang,bttieptuc;
    private GiohangAdapter giohangAdapter;
    static private List<giohangmodel> giohangList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);

        recyclergiohang = findViewById(R.id.recyclergiohang);
        tongtien = findViewById(R.id.tongtien);
        //khoi tao danh sach nêu null
        if (giohangList == null) {
            giohangList = new ArrayList<>();
        }
        Intent intent = getIntent();
        int imageResource = intent.getIntExtra("imgResource", R.drawable.bell);
        String name = intent.getStringExtra("name");
        String priceStr = intent.getStringExtra("price");
        // Chuyển đổi sang số nguyên
        int price = convertPriceToInteger(priceStr);
        int quantity = intent.getIntExtra("quantity", 1);
        if (imageResource != 1 && name != null && priceStr != null) {
            // kiểm tra xem sản  phẩm đã có trong giỏ hàng chưa
               boolean isProdutInCart=false;
            for (giohangmodel item : giohangList) {
                if (item.getName().equals(name)) { // So sánh dựa trên tên (hoặc ID sản phẩm nếu có)
                    // Nếu sản phẩm đã có, tăng số lượng
                    item.setQuantity(item.getQuantity() + quantity);
                    isProdutInCart = true;
                    break;
                }
            }
                if(!isProdutInCart){
                    giohangList.add(new giohangmodel(imageResource, name, priceStr, quantity));

                }


        } else {
            Log.e("ERROR", "RecyclerView is still null!");
        }

        // Thiết lập RecyclerView
        giohangAdapter = new GiohangAdapter(giohangList, this);
        recyclergiohang.setAdapter(giohangAdapter);
        recyclergiohang.setLayoutManager(new LinearLayoutManager(this));

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(giohang.this, trangchu.class);
                startActivity(intent1);
            }
        });

        trangchu=findViewById(R.id.imageView8);
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Closes the activity and goes back
                Intent intent1 = new Intent(giohang.this, trangchu.class);
                startActivity(intent1);
            }
        });
        muahang = findViewById(R.id.btmua);
        muahang.setOnClickListener(view -> {
            ArrayList<String> selectedProducts = new ArrayList<>();
            ArrayList<Integer> productImages = new ArrayList<>(); // Thay kiểu thành Integer để lưu hình ảnh (int)
            int totalPrice = 0; // Biến tổng tiền phải là số nguyên để tính toán chính xác

            // Duyệt qua danh sách giỏ hàng
            for (giohangmodel item : giohangList) {
                if (item.isSelected()) { // Kiểm tra nếu checkbox được chọn
                    selectedProducts.add(item.getName()); // Lưu tên sản phẩm
                    productImages.add(item.getImage()); // Lưu ảnh (int)

                    // Chuyển đổi giá từ String sang Integer
                    try {
                        int pric = Integer.parseInt(item.getPrice().replaceAll("[^\\d]", "")); // Loại bỏ ký tự không phải số
                        totalPrice += pric * item.getQuantity(); // Nhân giá với số lượng
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        // Nếu chuyển đổi lỗi, bỏ qua sản phẩm này
                        Toast.makeText(this, "Giá sản phẩm không hợp lệ: " + item.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            // Kiểm tra nếu không có sản phẩm được chọn
            if (selectedProducts.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn sản phẩm trước khi mua hàng!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông tin đã chọn vào Intent để truyền sang Activity tiếp theo
            Intent intent1 = new Intent(this, giaohang.class);
            intent1.putStringArrayListExtra("selectedProducts", selectedProducts); // Truyền danh sách sản phẩm
            intent1.putIntegerArrayListExtra("productImages", productImages); // Truyền danh sách ảnh
            intent1.putExtra("totalPrice", totalPrice); // Truyền tổng tiền
            startActivity(intent1);
        });

    }
    public void updateTotalPrice() {
        int totalPrice = 0;

        for (giohangmodel item : giohangList) {
            if (item.isSelected()) { // Chỉ tính các sản phẩm được chọn
                // Chuyển đổi giá sản phẩm từ String sang Integer
                int price = convertPriceToInteger(item.getPrice());
                totalPrice += price * item.getQuantity();
            }
        }

        tongtien.setText( totalPrice + " đ");
    }
    public int convertPriceToInteger(String priceStr) {
        // Loại bỏ các ký tự không phải số
        if (priceStr == null) {
            System.err.println("Giá trị priceStr bị null.");
            return 0; // Trả về 0 nếu chuỗi là null
        }
        String cleanedPrice = priceStr.replaceAll("[^\\d]", "");

        // Chuyển đổi chuỗi thành số nguyên
        try {
            return Integer.parseInt(cleanedPrice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0; // Trả về 0 nếu chuỗi không hợp lệ
        }
    }


}