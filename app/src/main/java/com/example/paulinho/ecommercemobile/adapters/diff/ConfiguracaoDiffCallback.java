package com.example.paulinho.ecommercemobile.adapters.diff;

import android.support.v7.util.DiffUtil;

import com.example.paulinho.ecommercemobile.model.Configuracao;

import java.util.List;

public class ConfiguracaoDiffCallback extends DiffUtil.Callback{

    List<Configuracao> oldConfiguracoes;
    List<Configuracao> newConfiguracoes;

    public ConfiguracaoDiffCallback(List<Configuracao> newConfiguracoes, List<Configuracao> oldConfiguracoes) {
        this.newConfiguracoes = newConfiguracoes;
        this.oldConfiguracoes = oldConfiguracoes;
    }

    @Override
    public int getOldListSize() {
        return oldConfiguracoes.size();
    }

    @Override
    public int getNewListSize() {
        return newConfiguracoes.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldConfiguracoes.get(oldItemPosition).getId() == newConfiguracoes.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldConfiguracoes.get(oldItemPosition).equals(newConfiguracoes.get(newItemPosition));
    }

}