package com.example.swipeablecardstacklibrary;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class CombinedCallback extends ItemTouchHelper.Callback {

    private final CustomizableCardAdapter adapter;
    private final SwipeListener swipeListener;
    private final boolean dragEnabled;
    private final boolean swipeEnabled;
    private final boolean enableLeftSwipe;
    private final boolean enableRightSwipe;
    private final boolean enableUpSwipe;
    private final boolean enableDownSwipe;

    public CombinedCallback(CustomizableCardAdapter adapter, SwipeListener swipeListener,
                            boolean dragEnabled, boolean swipeEnabled,
                            boolean enableLeftSwipe, boolean enableRightSwipe,
                            boolean enableUpSwipe, boolean enableDownSwipe) {
        this.adapter = adapter;
        this.swipeListener = swipeListener;
        this.dragEnabled = dragEnabled;
        this.swipeEnabled = swipeEnabled;
        this.enableLeftSwipe = enableLeftSwipe;
        this.enableRightSwipe = enableRightSwipe;
        this.enableUpSwipe = enableUpSwipe;
        this.enableDownSwipe = enableDownSwipe;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = dragEnabled ? ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT : 0;
        int swipeFlags = swipeEnabled ?
                (enableLeftSwipe ? ItemTouchHelper.LEFT : 0) |
                        (enableRightSwipe ? ItemTouchHelper.RIGHT : 0) |
                        (enableUpSwipe ? ItemTouchHelper.UP : 0) |
                        (enableDownSwipe ? ItemTouchHelper.DOWN : 0) : 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (dragEnabled) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            adapter.onItemMove(fromPosition, toPosition);
            return true;
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (swipeEnabled) {
            int position = viewHolder.getAdapterPosition();
            adapter.removeItem(position);

            if (swipeListener != null) {
                swipeListener.onSwiped(position, direction);
            }
        }
    }
}


