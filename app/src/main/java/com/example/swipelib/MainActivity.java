package com.example.swipelib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        button1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TodoActivity.class)));

        button2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MovieActivity.class)));

        button3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CardsGameActivity.class)));

        button4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BrowserActivity.class)));

        button5.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ImagesActivity.class)));
    }
}