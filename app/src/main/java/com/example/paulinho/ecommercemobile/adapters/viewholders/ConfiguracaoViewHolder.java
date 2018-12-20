package com.example.paulinho.ecommercemobile.adapters.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.adapters.ConfiguracoesAdapter;

public class ConfiguracaoViewHolder extends RecyclerView.ViewHolder {

    public TextView txtPropriedade;
    public TextView txtValor;


    public ConfiguracaoViewHolder(@NonNull View itemView,
                                  final ConfiguracoesAdapter.OnItemClickListener listener) {
        super(itemView);


        txtPropriedade = itemView.findViewById(R.id.txtPropriedade);
        txtValor = itemView.findViewById(R.id.txtValor);

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
