package com.example.swipelib;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swipeablecardstacklibrary.CombinedCallback;
import com.example.swipeablecardstacklibrary.CustomizableCardAdapter;
import com.example.swipeablecardstacklibrary.StackLayoutManager;
import com.example.swipeablecardstacklibrary.SwipeCardCallback;
import com.example.swipeablecardstacklibrary.SwipeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements SwipeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        RecyclerView recyclerView = findViewById(R.id.moviesRecyclerView);
        recyclerView.setLayoutManager(new StackLayoutManager(0));

        List<Movie> items = new ArrayList<>();
        items.add(new Movie("Deadpool & Wolverine", "2h 8m",
                "Deadpool is offered a place in the Marvel Cinematic Universe by the Time Variance Authority, but instead recruits a variant of Wolverine to save his universe from extinction.",
                new ArrayList<>(Arrays.asList("Ryan Reynolds", "Hugh Jackman", "Emma Corrin")),
                "Shawn Levy",
                "https://m.media-amazon.com/images/M/MV5BNzRiMjg0MzUtNTQ1Mi00Y2Q5LWEwM2MtMzUwZDU5NmVjN2NkXkEyXkFqcGc@._V1_.jpg"));
        items.add(new Movie("Dune: Part Two", "2h 46m",
                "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
                new ArrayList<>(Arrays.asList("Timothée Chalamet", "Zendaya", "Rebecca Ferguson")),
                "Denis Villeneuve",
                "https://m.media-amazon.com/images/M/MV5BN2QyZGU4ZDctOWMzMy00NTc5LThlOGQtODhmNDI1NmY5YzAwXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_.jpg"));
        items.add(new Movie("Inside Out 2", "1h 36m",
                "A sequel that features Riley entering puberty and experiencing brand new, more complex emotions as a result. As Riley tries to adapt to her teenage years, her old emotions try to adapt to the possibility of being replaced.",
                new ArrayList<>(Arrays.asList("Amy Poehler", "Maya Hawke", "Kensington Tallman")),
                "Kelsey Mann",
                "https://m.media-amazon.com/images/M/MV5BYTc1MDQ3NjAtOWEzMi00YzE1LWI2OWUtNjQ0OWJkMzI3MDhmXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_FMjpg_UX1000_.jpg"));


        CustomizableCardAdapter<Movie> adapter = new CustomizableCardAdapter<>(
                items,
                R.layout.movie_card_item,
                (view, movie) -> {
                    ImageView imageView = view.findViewById(R.id.movieImage);
                    TextView titleTextView = view.findViewById(R.id.movieTitle);
                    TextView descriptionTextView = view.findViewById(R.id.movieDescription);
                    TextView directorTextView = view.findViewById(R.id.movieDirector);
                    TextView actorsTextView = view.findViewById(R.id.movieActors);

                    titleTextView.setText(movie.getTitle());
                    descriptionTextView.setText(movie.getDescription());
                    directorTextView.append(movie.getDirector());
                    actorsTextView.append(String.join(", ", movie.getActors()));
                    Glide.with(this)
                            .load(movie.getImgUrl())
                            .into(imageView);
                    titleTextView.setText(movie.getTitle());
                }
        );
        recyclerView.setAdapter(adapter);

        CombinedCallback callback = new CombinedCallback(adapter, this, false, true, true, true, true, true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(int position, int direction) {
        if (direction == ItemTouchHelper.LEFT)
            Toast.makeText(this, "Voted No", Toast.LENGTH_SHORT).show();
        else if (direction == ItemTouchHelper.RIGHT)
            Toast.makeText(this, "Voted Yes", Toast.LENGTH_SHORT).show();
        else if (direction == ItemTouchHelper.UP)
            Toast.makeText(this, "Super yes", Toast.LENGTH_SHORT).show();
        else if (direction == ItemTouchHelper.DOWN)
            Toast.makeText(this, "Show again later", Toast.LENGTH_SHORT).show();
    }
}

