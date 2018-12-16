package com.example.paulinho.ecommercemobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulinho.ecommercemobile.R;
import com.example.paulinho.ecommercemobile.adapters.diff.ProdutoDiffCallback;
import com.example.paulinho.ecommercemobile.adapters.viewholders.ProdutoViewHolder;
import com.example.paulinho.ecommercemobile.interfaces.ItemClickListener;
import com.example.paulinho.ecommercemobile.model.Produto;
import com.example.paulinho.ecommercemobile.utils.ImagemUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {

    private List<Produto> produtos;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ProdutosAdapter(Context context, List<Produto> produtos){
        this.produtos = produtos;
        this.context = context;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_produtos, viewGroup, false);

        ProdutoViewHolder produtoViewHolder = new ProdutoViewHolder(view, mListener);

        return produtoViewHolder;
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder produtoViewHolder, int i) {

        Produto produto = produtos.get(i);

        produtoViewHolder.txtTitle.setText(produto.getTitle());
        produtoViewHolder.txtVendidos.setText(String.valueOf(produto.getSoldQuantity().intValue()));
        BigDecimal qtdeTotal = new BigDecimal(produto.getItem().getInitial_quantity());
        produtoViewHolder.txtQuantidadeDisponivel.setText(String.valueOf(qtdeTotal.intValue()));

        if(produtoViewHolder.imgProduct.getDrawable()==null) {
            produtoViewHolder.imgProduct.setImageBitmap(BitmapFactory.decodeStream(produto.getThumbnailIS()));
        }else{
            produtoViewHolder.imgProduct.setImageBitmap(BitmapFactory.decodeStream(ImagemUtils.getInputFromString(produto.getThumbnail())));
        }

        produtoViewHolder.txtValorVenda.setText(produto.getPrice().toString());

    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        produtos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, produtos.size());
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void updateList(Produto produto) {
        insertItem(produto);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.
    private void insertItem(Produto produto) {
        produtos.add(produto);
        notifyItemInserted(getItemCount());
    }

    public void updateList(List<Produto> produtos) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ProdutoDiffCallback(this.produtos, produtos));
        this.produtos = produtos;
        diffResult.dispatchUpdatesTo(this);
    }



}
