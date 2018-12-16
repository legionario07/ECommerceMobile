package com.example.paulinho.ecommercemobile.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.adapters.ProdutosAdapter;
import com.example.paulinho.ecommercemobile.interfaces.ItemClickListener;

public class ProdutoViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTitle;
    public ImageView imgProduct;
    public TextView txtQuantidadeDisponivel;
    public TextView txtVendidos;
    public TextView txtValorVenda;


    public ProdutoViewHolder(@NonNull View itemView,
                             final ProdutosAdapter.OnItemClickListener listener) {
        super(itemView);


        txtTitle = itemView.findViewById(R.id.txtTitle);
        imgProduct = itemView.findViewById(R.id.imgProduct);
        txtQuantidadeDisponivel = itemView.findViewById(R.id.txtQuantidadeDisponivel);
        txtVendidos = itemView.findViewById(R.id.txtVendido);
        txtValorVenda = itemView.findViewById(R.id.txtValorVenda);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });


    }

}
