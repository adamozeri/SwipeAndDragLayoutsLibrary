package com.example.swipeanddraglayoutslibrary;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class ScalingItemDecoration extends RecyclerView.ItemDecoration {

    private final float scaleFactor;

    public ScalingItemDecoration(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        boolean isHorizontal = Objects.requireNonNull(parent.getLayoutManager()).canScrollHorizontally();

        int centerPosition = isHorizontal ? parent.getWidth() / 2 : parent.getHeight() / 2;
        float d0 = 0.0f;
        float d1 = 0.9f * centerPosition;
        float s0 = 1.0f;

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            int childCenterPosition = isHorizontal
                    ? (child.getLeft() + child.getRight()) / 2
                    : (child.getTop() + child.getBottom()) / 2;

            float d = Math.min(d1, Math.abs(centerPosition - childCenterPosition));
            float scale = s0 + (scaleFactor - s0) * (d - d0) / (d1 - d0);

            child.setScaleX(scale);
            child.setScaleY(scale);
        }
    }
}

