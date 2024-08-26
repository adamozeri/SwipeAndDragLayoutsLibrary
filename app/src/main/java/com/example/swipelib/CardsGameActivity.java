package com.example.swipelib;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swipeablecardstacklibrary.DragAndSwipeCallback;
import com.example.swipeablecardstacklibrary.CustomizableCardAdapter;
import com.example.swipeablecardstacklibrary.StackLayoutManager;
import com.example.swipeablecardstacklibrary.SwipeListener;

import java.util.ArrayList;
import java.util.List;

public class CardsGameActivity extends AppCompatActivity implements SwipeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_game);

        RecyclerView recyclerView = findViewById(R.id.cardsRecyclerView);
        recyclerView.setLayoutManager(new StackLayoutManager(100));

        List<String> items = new ArrayList<>();
        items.add("https://www.pngitem.com/pimgs/m/658-6580313_2-svg-card-2-of-diamonds-cards-hd.png");
        items.add("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Poker-sm-212-Ks.png/1200px-Poker-sm-212-Ks.png");
        items.add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Cards-8-Heart.svg/1200px-Cards-8-Heart.svg.png");


        CustomizableCardAdapter<String> adapter = new CustomizableCardAdapter<>(items, R.layout.game_card_item, (view, item) -> {
            ImageView imageView = view.findViewById(R.id.cardImage);
            Glide.with(this).load(item).into(imageView);
        });
        recyclerView.setAdapter(adapter);

        DragAndSwipeCallback callback = new DragAndSwipeCallback(adapter, this, false, true, false, false, true, true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(int position, int direction) {
        if (direction == ItemTouchHelper.UP)
            Toast.makeText(this, "Card up", Toast.LENGTH_SHORT).show();
        else if (direction == ItemTouchHelper.DOWN)
            Toast.makeText(this, "Card down", Toast.LENGTH_SHORT).show();
    }

}