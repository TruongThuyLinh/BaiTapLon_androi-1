package com.example.baitaplon.drawerLayout.adapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon.R;
import com.example.baitaplon.drawerLayout.activity1.chitietgiohang;
import com.example.baitaplon.drawerLayout.activity1.chitietsanpham;

import com.example.baitaplon.drawerLayout.model.giohangmodel;
import com.example.baitaplon.drawerLayout.model.product;

import java.util.ArrayList;
import java.util.List;
// kêt nối dữ liệu từ danh sách (List<product>) đến RecyclerView.
//Hiển thị từng item của danh sách trong RecyclerView theo cấu trúc XML (item_product).
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<giohangmodel> giohangList = new ArrayList<>(); // Danh sách giỏ hàng
    private List<product> productList; // Danh sách hiển thị sản phẩm
    private Context context; // Ngữ cảnh

    // Constructor khởi tạo danh sách sản phẩm
    public ProductAdapter(List<product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho mỗi item trong RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        product product = productList.get(position);

        // Set giá trị cho từng thuộc tính của product
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productImage.setImageResource(product.getImage());

        // Xử lý sự kiện khi người dùng nhấn vào từng sản phẩm để mở chi tiết sản phẩm
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, chitietsanpham.class);
            intent.putExtra("productImage", product.getImage());
            intent.putExtra("productName", product.getName());
            intent.putExtra("productPrice", product.getPrice());
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return productList.size(); // Trả về số lượng item
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;ImageView productImage;
        Button themgiohang;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            themgiohang = itemView.findViewById(R.id.btnAddToCart); // Khai báo Button trong ViewHolder
        }
    }
}