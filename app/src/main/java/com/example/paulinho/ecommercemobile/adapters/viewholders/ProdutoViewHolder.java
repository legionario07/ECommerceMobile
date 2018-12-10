package com.example.paulinho.ecommercemobile.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulinho.ecommercemobile.R;

public class ProdutoViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTitle;
    public TextView txtDescription;
    public ImageView imgProduct;
    public TextView txtPrice;
    public TextView txtQty;


    public ProdutoViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        //txtQty = itemView.findViewById(R.id.txtQty);
        //txtPrice = itemView.findViewById(R.id.txtPrice);
        txtDescription = itemView.findViewById(R.id.txtDescripton);
        imgProduct = itemView.findViewById(R.id.imgProduct);
        //txtPrice = itemView.findViewById(R.id.txtPrice);
        //txtPrice = itemView.findViewById(R.id.txtPrice);



    }

}
