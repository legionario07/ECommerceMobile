package com.example.paulinho.ecommercemobile.adapters;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.adapters.diff.ConfiguracaoDiffCallback;
import com.example.paulinho.ecommercemobile.adapters.viewholders.ConfiguracaoViewHolder;
import com.example.paulinho.ecommercemobile.model.Configuracao;

import java.util.List;

public class ConfiguracoesAdapter extends RecyclerView.Adapter<ConfiguracaoViewHolder> {

    private List<Configuracao> configuracoes;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ConfiguracoesAdapter(Context context, List<Configuracao> configuracoes){
        this.configuracoes = configuracoes;
        this.context = context;
    }

    @Override
    public ConfiguracaoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_configuracao, viewGroup, false);

        ConfiguracaoViewHolder configuracaoViewHolder = new ConfiguracaoViewHolder(view, mListener);

        return configuracaoViewHolder;
    }

    @Override
    public void onBindViewHolder(ConfiguracaoViewHolder configuracaoViewHolder, int i) {

        Configuracao configuracao = configuracoes.get(i);

        configuracaoViewHolder.txtPropriedade.setText(configuracao.getPropriedade());
        configuracaoViewHolder.txtValor.setText(configuracao.getValor());

    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        configuracoes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, configuracoes.size());
    }

    @Override
    public int getItemCount() {
        return configuracoes.size();
    }

    public void updateList(Configuracao configuracao) {
        insertItem(configuracao);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.
    private void insertItem(Configuracao configuracao) {
        configuracoes.add(configuracao);
        notifyItemInserted(getItemCount());
    }

    public void updateList(List<Configuracao> configuracoes) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ConfiguracaoDiffCallback(this.configuracoes, configuracoes));
        this.configuracoes = configuracoes;
        diffResult.dispatchUpdatesTo(this);
    }



}
