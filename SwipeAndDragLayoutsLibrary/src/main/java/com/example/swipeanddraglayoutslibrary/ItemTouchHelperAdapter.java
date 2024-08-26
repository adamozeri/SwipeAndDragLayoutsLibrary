package com.example.swipeanddraglayoutslibrary;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void removeItem(int position);
}
