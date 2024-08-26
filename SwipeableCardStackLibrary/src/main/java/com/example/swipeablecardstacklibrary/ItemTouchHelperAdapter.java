package com.example.swipeablecardstacklibrary;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void removeItem(int position);
}
