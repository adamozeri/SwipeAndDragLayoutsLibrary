package com.example.swipeanddraglayoutslibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class CustomizableCardAdapter<T> extends RecyclerView.Adapter<CustomizableCardAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private final List<T> items;
    private final int cardLayoutResId;
    private final OnBindViewCallback<T> onBindViewCallback;

    public CustomizableCardAdapter(List<T> items, int cardLayoutResId, OnBindViewCallback<T> onBindViewCallback) {
        this.items = items;
        this.cardLayoutResId = cardLayoutResId;
        this.onBindViewCallback = onBindViewCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(cardLayoutResId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T item = items.get(position);
        onBindViewCallback.onBindView(holder.itemView, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnBindViewCallback<T> {
        void onBindView(View itemView, T item);
    }
}
