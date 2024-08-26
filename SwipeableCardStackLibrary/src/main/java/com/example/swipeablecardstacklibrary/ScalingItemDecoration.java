package com.example.swipeablecardstacklibrary;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScalingItemDecoration extends RecyclerView.ItemDecoration {

    private float scaleFactor;

    public ScalingItemDecoration(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // Determine if the layout is horizontal or vertical
        boolean isHorizontal = parent.getLayoutManager().canScrollHorizontally();

        int centerPosition = isHorizontal ? parent.getWidth() / 2 : parent.getHeight() / 2;
        float d0 = 0.0f;
        float d1 = 0.9f * centerPosition;
        float s0 = 1.0f;
        float s1 = scaleFactor;

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            int childCenterPosition = isHorizontal
                    ? (child.getLeft() + child.getRight()) / 2
                    : (child.getTop() + child.getBottom()) / 2;

            float d = Math.min(d1, Math.abs(centerPosition - childCenterPosition));
            float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);

            child.setScaleX(scale);
            child.setScaleY(scale);
        }
    }
}

