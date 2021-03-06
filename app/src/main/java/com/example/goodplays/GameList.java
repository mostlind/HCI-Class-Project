package com.example.goodplays;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GameList {
    public ArrayList<Game> games;

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    private static GameList gameHolder = new GameList();

    public static GameList getInstance() {
        if (gameHolder.games == null) {
            ArrayList<Game> games = new ArrayList<>();
            try {
                games.add(new Game("Borderlands 3", "Description", "Stadia", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
                games.add(new Game("Orcs Must Die 3", "Description", "Stadia", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
                games.add(new Game("Division 2", "Description", "Stadia", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
                games.add(new Game("PUBG", "Description", "Stadia", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
                games.add(new Game("Title 3", "Description", "PC", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
                games.add(new Game("Title 3", "Description", "XBOX", new URL("https://images.freeimages.com/images/large-previews/754/firework-final-display-1186309.jpg")));
            } catch (MalformedURLException e) { }

            gameHolder.setGames(games);
        }
        return gameHolder;
    }

    public void setFavorite(int index, boolean value) {
        games.get(index).favorite = value;
    }
}
