package com.app.torch.utils.recyclerview.alphabet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.torch.R;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> implements AlphabetScrollRecyclerViewInterface {
    private static final int VIEW_TYPE_HEAD = 1;
    private static final int VIEW_TYPE_NORMAL = 2;
    private List<AlphabetModel> dataset;
    private Map<String, Integer> map;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlphabetAdapter(List<AlphabetModel> dataset, Map<String, Integer> map) {
        this.dataset = dataset;
        this.map = map;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataset.get(position).isTitle()) {
            return VIEW_TYPE_HEAD;
        }
        return VIEW_TYPE_NORMAL;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = null;
        switch (viewType) {
            case VIEW_TYPE_HEAD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alphabet_head, parent, false);
                return new ItemHeadViewHolder(view);
            case VIEW_TYPE_NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alphabet, parent, false);
                return new ItemViewHolder(view);
        }
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTitle(dataset.get(position).getTitle());
        holder.bindImage(dataset.get(position).getImageUrl());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public Map<String, Integer> getMapIndex() {
        return this.map;
    }

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bindTitle(String title);

        abstract void bindImage(String url);
    }

    public static class ItemHeadViewHolder extends ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public ItemHeadViewHolder(View view) {
            super(view);
            textView = (TextView) view;
        }

        @Override
        void bindTitle(String title) {
            textView.setText(title);
        }

        @Override
        void bindImage(String url) {

        }
    }

    public static class ItemViewHolder extends ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView image;

        public ItemViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
        }

        @Override
        void bindTitle(String title) {
            textView.setText(title);
        }

        @Override
        void bindImage(String url) {
            Glide.with(image.getContext()).load(url).into(image);
        }
    }
}