package com.example.swipeablecardstacklibrary;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeCardCallback extends ItemTouchHelper.Callback {

    private final CustomizableCardAdapter adapter;
    private final SwipeListener swipeListener;
    private final boolean enableLeftSwipe;
    private final boolean enableRightSwipe;
    private final boolean enableUptSwipe;
    private final boolean enableDownSwipe;

    public SwipeCardCallback(CustomizableCardAdapter adapter, SwipeListener swipeListener,
                             boolean enableLeftSwipe, boolean enableRightSwipe, boolean enableUpSwipe, boolean enableDownSwipe) {
        this.adapter = adapter;
        this.swipeListener = swipeListener;
        this.enableLeftSwipe = enableLeftSwipe;
        this.enableRightSwipe = enableRightSwipe;
        this.enableUptSwipe = enableUpSwipe;
        this.enableDownSwipe = enableDownSwipe;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = 0;
        if (enableLeftSwipe) {
            swipeFlags |= ItemTouchHelper.LEFT;
        }
        if (enableRightSwipe) {
            swipeFlags |= ItemTouchHelper.RIGHT;
        }
        if (enableUptSwipe) {
            swipeFlags |= ItemTouchHelper.UP;
        }
        if (enableDownSwipe) {
            swipeFlags |= ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        adapter.removeItem(position);

        if (swipeListener != null) {
            swipeListener.onSwiped(position, direction);
        }
    }
}
