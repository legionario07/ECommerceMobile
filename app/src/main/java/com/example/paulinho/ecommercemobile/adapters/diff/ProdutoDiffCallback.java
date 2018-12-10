package com.example.paulinho.ecommercemobile.adapters.diff;

import android.support.v7.util.DiffUtil;

import com.example.paulinho.ecommercemobile.model.Produto;

import java.util.List;

public class ProdutoDiffCallback extends DiffUtil.Callback{

    List<Produto> oldProdutos;
    List<Produto> newProdutos;

    public ProdutoDiffCallback(List<Produto> newProdutos, List<Produto> oldProdutos) {
        this.newProdutos = newProdutos;
        this.oldProdutos = oldProdutos;
    }

    @Override
    public int getOldListSize() {
        return oldProdutos.size();
    }

    @Override
    public int getNewListSize() {
        return newProdutos.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProdutos.get(oldItemPosition).getId() == newProdutos.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProdutos.get(oldItemPosition).equals(newProdutos.get(newItemPosition));
    }

}