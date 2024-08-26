package com.example.swipeablecardstacklibrary;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void removeItem(int position);
}
