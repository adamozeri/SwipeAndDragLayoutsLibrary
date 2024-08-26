package com.example.layoutslibrary;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class StackLayoutManager extends RecyclerView.LayoutManager {

    private final int overlap;

    public StackLayoutManager(int overlap) {
        this.overlap = overlap;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();

        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            int left = (getWidth() - width) / 2;
            int top = (getHeight() - height) / 2;

            layoutDecoratedWithMargins(view, left, top, left + width, top + height);

            view.setTranslationY(i * overlap);
        }
    }
}
