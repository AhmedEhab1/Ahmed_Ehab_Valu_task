package com.valu.ahmedehabtask.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.valu.ahmedehabtask.databinding.ProductItemBinding;
import com.valu.ahmedehabtask.interfaces.ProductsListener;
import com.valu.ahmedehabtask.model.ProductsResponse;
import com.valu.ahmedehabtask.utilities.ImageHelper;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ImagesViewHolder> {
    private List<ProductsResponse> model;
    private Context context;
    private ProductsListener listener;

    public ProductsAdapter(List<ProductsResponse> images, Context context, ProductsListener listener) {
        this.model = images;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductsAdapter.ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsAdapter.ImagesViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ImagesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.productTitle.setText(model.get(position).getTitle());
        holder.binding.productPrice.setText(String.valueOf(model.get(position).getPrice()).concat(" EGP"));
        ImageHelper.loadImage(context, model.get(position).getImage(), holder.binding.productImage);
        holder.itemView.setOnClickListener(view -> listener.onProductClicked(model.get(position)));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        private ProductItemBinding binding;

        public ImagesViewHolder(@NonNull ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
