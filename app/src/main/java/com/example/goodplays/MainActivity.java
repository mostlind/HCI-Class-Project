package com.example.goodplays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Game> games = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Game> queuedGames = gamesByStatus(Game.Status.Queued);
        ArrayList<Game> playingNowGames = gamesByStatus(Game.Status.PlayingNow);
        ArrayList<Game> playedGames = gamesByStatus(Game.Status.Played);


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
}
