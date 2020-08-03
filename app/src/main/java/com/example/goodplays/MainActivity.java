package com.example.goodplays;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Game> games = new ArrayList<>();
    LinearLayout queueLayout;

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

        ArrayList<Game> queuedGames = gamesByStatus(Game.Status.Queued);
        ArrayList<Game> playingNowGames = gamesByStatus(Game.Status.PlayingNow);
        ArrayList<Game> playedGames = gamesByStatus(Game.Status.Played);

        queueLayout = findViewById(R.id.gameList);
        for (Game queuedGame : queuedGames) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.game_card, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 24);

            view.setLayoutParams(layoutParams);

            TextView titleView = view.findViewById(R.id.gameTitle);
            titleView.setText(queuedGame.title);

            ImageView imageView = view.findViewById((R.id.gameCardImage));
            new DownloadImageTask(imageView).execute(queuedGame.imageUrl);

            queueLayout.addView(view);
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
