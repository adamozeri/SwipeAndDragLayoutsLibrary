package com.example.swipelib;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swipeablecardstacklibrary.DragAndSwipeCallback;
import com.example.swipeablecardstacklibrary.CustomizableCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        RecyclerView recyclerView = findViewById(R.id.browserRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<WebPage> items = new ArrayList<>();
        items.add(new WebPage("https://techcrunch.com/wp-content/uploads/2017/05/new-dark-theme.png?w=680", "Youtube"));
        items.add(new WebPage("https://technave.com/data/files/mall/article/202103260302383183.png", "Spotify"));
        items.add(new WebPage("https://engineering.fb.com/wp-content/uploads/2020/05/1.-Home-Setting-Light-Mode.png", "Facebook"));


        CustomizableCardAdapter<WebPage> adapter = new CustomizableCardAdapter<>(
                items,
                R.layout.browser_card_item,
                (view, webPage) -> {
                    ImageView imageView = view.findViewById(R.id.pageImage);
                    TextView titleTextView = view.findViewById(R.id.pageTitle);

                    Glide.with(this)
                            .load(webPage.getImageUrl())
                            .into(imageView);
                    titleTextView.setText(webPage.getTitle());
                }
        );
        recyclerView.setAdapter(adapter);

        DragAndSwipeCallback dragAndSwipeCallback = new DragAndSwipeCallback(adapter, (position, direction) -> {

        }, true, true, true, true, false, false);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(dragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}