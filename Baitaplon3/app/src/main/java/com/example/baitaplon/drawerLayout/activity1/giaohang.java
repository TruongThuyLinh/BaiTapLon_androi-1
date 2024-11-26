package com.example.baitaplon.drawerLayout.activity1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplon.R;
import com.example.baitaplon.drawerLayout.model.giohangmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class giaohang extends AppCompatActivity {

    private ImageView trangchu, giohang, thoat;
    private Button btnGiaoHang;
    private TextView textTinh, textHuyen,textXa;
    private EditText editFullName, editPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_giao_hang);

        initViews();

        // Nhận dữ liệu từ Intent (giỏ hàng, tổng tiền, hình ảnh sản phẩm)
        ArrayList<String> selectedProducts = getIntent().getStringArrayListExtra("selectedProducts");
        ArrayList<Integer> productImages = getIntent().getIntegerArrayListExtra("productImages");
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);

        // Khi nhấn nút "Tiếp tục"
        btnGiaoHang.setOnClickListener(v -> handleGiaoHang(selectedProducts, productImages, totalPrice));
    }

    private void initViews() {
        trangchu = findViewById(R.id.imageView8);
        giohang = findViewById(R.id.imageView12);
        thoat = findViewById(R.id.btn_close);
        btnGiaoHang = findViewById(R.id.btmua);
        textTinh = findViewById(R.id.textTinh);
        textHuyen = findViewById(R.id.textHuyen);
        textXa = findViewById(R.id.textXa);
        editFullName = findViewById(R.id.editFullName);
        editPhoneNumber = findViewById(R.id.edtPhoneNumber);

        // Sự kiện quay lại trang chủ, giỏ hàng và đóng màn hình
        trangchu.setOnClickListener(v -> startActivity(new Intent(this, trangchu.class)));
        giohang.setOnClickListener(v -> startActivity(new Intent(this, giohang.class)));
        thoat.setOnClickListener(v -> finish());

        setupAddressSelection();
    }

    private void setupAddressSelection() {
        Map<String, Map<String, String[]>> data = new HashMap<String, Map<String, String[]>>() {{
            put("Hà Nội", new HashMap<String, String[]>() {{
                put("Ba Đình", new String[]{"Phúc Xá", "Trúc Bạch", "Vĩnh Phúc"});
                put("Đống Đa", new String[]{"Cát Linh", "Hàng Bột", "Khâm Thiên"});
            }});
            put("Hồ Chí Minh", new HashMap<String, String[]>() {{
                put("Quận 1", new String[]{"Bến Nghé", "Bến Thành", "Cầu Kho"});
                put("Quận 3", new String[]{"Phường 1", "Phường 2", "Phường 3"});
            }});
        }};

        // Sự kiện chọn tỉnh, huyện, xã
        textTinh.setOnClickListener(v -> showSelectionDialog("Chọn Tỉnh/Thành phố", data.keySet().toArray(new String[0]), (dialog, which) -> {
            String selectedTinh = data.keySet().toArray(new String[0])[which];
            textTinh.setText(selectedTinh);
            textHuyen.setText("Huyện, Quận >");
            textXa.setText("Xã, Phường >");
            textHuyen.setTag(data.get(selectedTinh));
        }));

        textHuyen.setOnClickListener(v -> {
            Map<String, String[]> huyenData = (Map<String, String[]>) textHuyen.getTag();
            if (huyenData != null) {
                showSelectionDialog("Chọn Huyện/Quận", huyenData.keySet().toArray(new String[0]), (dialog, which) -> {
                    String selectedHuyen = huyenData.keySet().toArray(new String[0])[which];
                    textHuyen.setText(selectedHuyen);
                    textXa.setText("Xã, Phường >");
                    textXa.setTag(huyenData.get(selectedHuyen));
                });
            }
        });

        textXa.setOnClickListener(v -> {
            String[] xaData = (String[]) textXa.getTag();
            if (xaData != null) {
                showSelectionDialog("Chọn Xã/Phường", xaData, (dialog, which) -> textXa.setText(xaData[which]));
            }
        });
    }

    private void showSelectionDialog(String title, String[] items, AlertDialog.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(items, listener)
                .show();
    }

    private void handleGiaoHang(ArrayList<String> selectedProducts, ArrayList<Integer> productImages, int totalPrice) {
        String fullName = editFullName.getText().toString().trim();
        String phoneNumber = editPhoneNumber.getText().toString().trim();
        String tinh = textTinh.getText().toString().trim();
        String huyen = textHuyen.getText().toString().trim();
        String xa = textXa.getText().toString().trim();

        // Kiểm tra thông tin nhập
        if (fullName.isEmpty() || phoneNumber.isEmpty() || tinh.equals("Chọn Tỉnh/Thành phố") ||
                huyen.equals("Huyện, Quận >") || xa.equals("Xã, Phường >")) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển dữ liệu sang màn hình thanh toán
        Intent intent = new Intent(this, thanhtoan.class);
        intent.putStringArrayListExtra("selectedProducts", selectedProducts);
        intent.putIntegerArrayListExtra("productImages", productImages);
        intent.putExtra("totalPrice", totalPrice);

        // Thêm thông tin khách hàng
        intent.putExtra("customerName", fullName);
        intent.putExtra("customerPhone", phoneNumber);
        intent.putExtra("customerAddress", tinh + ", " + huyen + ", " + xa);
        startActivity(intent);
    }
}
