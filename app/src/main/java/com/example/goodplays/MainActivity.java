package com.example.goodplays;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.goodplays.R.color.startButton;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Game> games = new ArrayList<>();
    LinearLayout queueLayout;
    LinearLayout nowPlayingLayout;
    LinearLayout playedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            games.add(new Game("Title", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
            games.add(new Game("Title 2", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
            games.add(new Game("Title 3", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
            games.add(new Game("Title 3", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
            games.add(new Game("Title 3", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
            games.add(new Game("Title 3", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
        } catch (MalformedURLException e) {
            Log.e("welp", "onCreate: ", e);
        }

        nowPlayingLayout = findViewById(R.id.nowPlayingLinearLayout);
        queueLayout = findViewById(R.id.queueLinearLayout);
        playedLayout = findViewById(R.id.playedLinearLayout);

        renderGames();

    }

    private void renderGames() {
        ArrayList<Game> playingNowGames = gamesByStatus(Game.Status.PlayingNow);
        ArrayList<Game> queuedGames = gamesByStatus(Game.Status.Queued);
        ArrayList<Game> playedGames = gamesByStatus(Game.Status.Played);

        addGamesToLayout(playingNowGames, nowPlayingLayout);
        addGamesToLayout(queuedGames, queueLayout);
        addGamesToLayout(playedGames, playedLayout);
    }

    private class GameButtonListener implements View.OnClickListener {
        Game game;
        GameButtonListener(Game game) {
            this.game = game;
        }
        @Override
        public void onClick(View v) {

            game.progressStatus();
            renderGames();

        }
    }

    private void addGamesToLayout(ArrayList<Game> games, LinearLayout layout) {
        layout.removeAllViews();
        for (Game game : games) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.game_card, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 24);

            view.setLayoutParams(layoutParams);

            TextView titleView = view.findViewById(R.id.gameTitle);
            titleView.setText(game.title);

            TextView descriptionView = view.findViewById(R.id.gameDescription);
            descriptionView.setText(game.description);

            TextView platformView = view.findViewById(R.id.gameCardPlatform);
            platformView.setText(game.platform);

            TextView dateView = view.findViewById(R.id.gameAddedDate);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateView.setText("Added: " + dateFormat.format(game.dateAdded));

            ImageView imageView = view.findViewById((R.id.gameCardImage));
            new DownloadImageTask(imageView).execute(game.imageUrl);

            Button button = view
                    .findViewById(R.id.gameCardActionButton);

            switch (game.status) {
                case PlayingNow:
                    button.setBackgroundColor(Color.RED);
                    button.setText("Done");
                    break;
                case Queued:
                    button.setBackgroundColor(getResources().getColor(startButton));
                    button.setText("Start");
                    break;
                case Played:
                    button.setText("Done");
                    button.setBackgroundColor(Color.GRAY);
                    button.setEnabled(false);
                    break;
            }

            button.setOnClickListener(new GameButtonListener(game));

            layout.addView(view);
        }
    }

    private ArrayList<Game> gamesByStatus(Game.Status status) {
        ArrayList<Game> filteredGames = new ArrayList<>();
        for (Game game : games) {
            if (game.status == status) {
                filteredGames.add(game);
            }
        }

        return filteredGames;
    }

    private class DownloadImageTask extends AsyncTask<URL, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(URL... urls) {
            URL urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = urldisplay.openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
