package com.example.baitaplon.drawerLayout.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon.R;

import com.example.baitaplon.drawerLayout.activity1.MainActivity;
import com.example.baitaplon.drawerLayout.activity1.giohang;
import com.example.baitaplon.drawerLayout.model.giohangmodel;


import java.text.BreakIterator;
import java.util.List;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.GiohangViewHolder> {
    private List<giohangmodel> giohangList;
    private Context context;

    public GiohangAdapter(List<giohangmodel> giohangList, Context context) {
        this.giohangList = giohangList;
        this.context = context;
    }

    @NonNull
    @Override
    public GiohangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new GiohangViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GiohangViewHolder holder, int position) {
        giohangmodel item = giohangList.get(position);

        // Set tên sản phẩm, giá và số lượng
        holder.productName.setText(item.getName());
        holder.productPrice.setText(item.getPrice());
        holder.itemGiohangSoluong.setText(String.valueOf(item.getQuantity()));
        // Set hình ảnh sản phẩm
        holder.productImage.setImageResource(item.getImage());

        // Lắng nghe sự thay đổi checkbox
        holder.checkboxSelect.setChecked(item.isSelected());
        holder.checkboxSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
            // Cập nhật tổng tiền khi trạng thái checkbox thay đổi
            if (context instanceof giohang) {
                ((giohang) context).updateTotalPrice();
            }
        });
        // nút cộng
       holder.itemGiohangCong.setOnClickListener(view -> {
          int newQuantity=item.getQuantity()+1;
          item.setQuantity(newQuantity);
           holder.itemGiohangSoluong.setText(String.valueOf(newQuantity));
           ((giohang) context).updateTotalPrice();
       });
       holder.itemGiohangTru.setOnClickListener(view -> {
           int  currentQuantity=item.getQuantity()-1;
           if( currentQuantity>1){
               item.setQuantity( currentQuantity);
               holder.itemGiohangSoluong.setText(String.valueOf( currentQuantity));// cập nhât lại số lương

           }
           else {
               // xóa ra khỏi giỏ hàng
                giohangList.remove(position);
               notifyItemRemoved(position);
               notifyItemRangeChanged(position, giohangList.size());

           }
           ((giohang) context).updateTotalPrice();

       });
        // Set trạng thái checkbox
        holder.checkboxSelect.setChecked(item.isSelected());

       // Lắng nghe sự thay đổi checkbox
        holder.checkboxSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);  // Cập nhật trạng thái selected
            if (context instanceof giohang) {
                ((giohang) context).updateTotalPrice();  // Cập nhật tổng tiền
            }
        });

    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    public static class GiohangViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, itemGiohangTru, itemGiohangCong;
        TextView productName, productPrice, itemGiohangSoluong;
        CheckBox checkboxSelect;

        public GiohangViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            itemGiohangTru = itemView.findViewById(R.id.item_giohang_tru);
            itemGiohangCong = itemView.findViewById(R.id.item_giohang_cong);
            itemGiohangSoluong = itemView.findViewById(R.id.item_giohang_soluong);
         checkboxSelect=itemView.findViewById(R.id.checkboxSelect);
        }
    }
}