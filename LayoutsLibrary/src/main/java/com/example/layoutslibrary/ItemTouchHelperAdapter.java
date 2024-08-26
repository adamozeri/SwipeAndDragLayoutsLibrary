package com.example.layoutslibrary;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void removeItem(int position);
}
