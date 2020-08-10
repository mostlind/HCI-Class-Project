package com.example.goodplays;

import android.arch.core.util.Function;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class game_edit extends AppCompatActivity {
    ArrayList<Function<Game, Game>> changes = new ArrayList<>();

    private class SaveButtonListener implements View.OnClickListener {
        Game game;

        public SaveButtonListener(Game game) {
            this.game = game;
        }
        @Override
        public void onClick(View v) {
            for (Function<Game, Game> change : changes) {
                change.apply(game);
            }
            String difficulty = ((EditText) findViewById(R.id.difficultyNum)).getText().toString();
            String review = ((EditText) findViewById(R.id.reviewText)).getText().toString();

            game.review = review;
            game.difficulty = difficulty;

            android.content.Intent home = new android.content.Intent(getBaseContext(), MainActivity.class);
            startActivity(home);
        }
    }

    private class FavoriteToggledListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
            changes.add(new Function<Game, Game>() {
                @Override
                public Game apply(Game input) {
                    input.favorite = isChecked;
                    return input;
                }
            });
        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
            changes.add(new Function<Game, Game>() {
                @Override
                public Game apply(Game input) {

                   input.rating = rating;

                    return input;
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game_edit);

        Button discard = (Button) findViewById(R.id.discardButton);
        Button save = (Button) findViewById(R.id.saveButton);
        Switch favoriteSwitch = (Switch) findViewById(R.id.switch1);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView gameTitle = (TextView) findViewById(R.id.gameTitle);
        TextView gameDescription = (TextView) findViewById(R.id.gameDescription);
        TextView gamePlatform = (TextView) findViewById(R.id.gameCardPlatform);
        TextView gameDate = (TextView) findViewById(R.id.gameAddedDate);
        ImageView gameImage = (ImageView) findViewById(R.id.gameCardImage);
        EditText gameDifficulty = (EditText) findViewById(R.id.difficultyNum);
        EditText gameReview = (EditText) findViewById(R.id.reviewText);

        Bundle gameData = this.getIntent().getExtras();
        int index = gameData.getInt("index");
        super.onCreate(savedInstanceState);

        Game curr_game = GameList.getInstance().games.get(index);

        gameTitle.setText(curr_game.title);
        gameDescription.setText(curr_game.description);
        favoriteSwitch.setChecked(curr_game.favorite);
        Uri uri = Uri.parse(curr_game.imageUrl.toString());
        gameImage.setImageURI(uri);
        gamePlatform.setText(curr_game.platform);
        gameDate.setText(curr_game.dateAdded.toString());
        gameDifficulty.setText(curr_game.difficulty);
        gameReview.setText(curr_game.review);
        ratingBar.setRating(curr_game.rating);

        favoriteSwitch.setOnCheckedChangeListener(new FavoriteToggledListener());
        ratingBar.setOnRatingBarChangeListener(new RatingChangedListener());

        save.setOnClickListener(new game_edit.SaveButtonListener(
                curr_game));

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.Intent home = new android.content.Intent(getBaseContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }
}