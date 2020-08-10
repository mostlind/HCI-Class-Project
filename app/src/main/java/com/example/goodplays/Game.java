package com.example.goodplays;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

public class Game {

    public enum Status {
        Queued,
        PlayingNow,
        Played
    }

    public UUID id;
    public String title;
    public String description;
    public Date dateAdded;
    public String platform;
    public Status status;
    public Float rating;
    public URL imageUrl;

    //game edit values
    public boolean favorite;
    public String difficulty = "0";
    public String review = "Your Review";

    public Game(String title, String description, String platform, URL imageUrl) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.dateAdded = new Date();
        this.platform = platform;
        this.status = Status.Queued;
        this.rating = 0.0f;
        this.imageUrl = imageUrl;
    }

    public void progressStatus() {
        switch (this.status) {
            case Queued:
                this.status = Status.PlayingNow;
                return;
            case PlayingNow:
                this.status = Status.Played;
                return;
            case Played:
                this.status =  Status.Played;
        }

    }
}
