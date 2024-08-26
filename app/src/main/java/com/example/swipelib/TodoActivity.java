package com.example.swipelib;

import android.graphics.Paint;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swipeablecardstacklibrary.CustomizableCardAdapter;
import com.example.swipeablecardstacklibrary.SwipeCardCallback;
import com.example.swipeablecardstacklibrary.SwipeListener;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity implements SwipeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        RecyclerView recyclerView = findViewById(R.id.todoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> items = new ArrayList<>();
        items.add("Go to the supermarket");
        items.add("Do the Dishes");
        items.add("Take Out the Trash");
        items.add("Water Plants");

        CustomizableCardAdapter<String> adapter = new CustomizableCardAdapter<>(
                items,
                R.layout.todo_card_item,
                (view, task) -> {
                    TextView todoCardTitle = view.findViewById(R.id.todoCardTitle);
                    CheckBox todoCheckBox = view.findViewById(R.id.todoCheckBox);
                    todoCardTitle.setText(task);

                    todoCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
                        if(b)
                            todoCardTitle.setPaintFlags(todoCardTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        else
                            todoCardTitle.setPaintFlags(todoCardTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    });
                }
        );
        recyclerView.setAdapter(adapter);
        SwipeCardCallback callback = new SwipeCardCallback(adapter, this, true, true, false, false);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(int position, int direction) {
        if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT)
            Toast.makeText(this, "Removed task", Toast.LENGTH_SHORT).show();
    }
}