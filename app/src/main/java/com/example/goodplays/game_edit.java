package com.example.goodplays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class game_edit extends AppCompatActivity {
    boolean globalFav;

    private class GameButtonListener implements View.OnClickListener {
        Game game;
        GameButtonListener(Game game) {
            this.game = game;
        }
        @Override
        public void onClick(View v) {
            game.favorite = true;
            game.title = "blah";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game_edit);
        Button discard = (Button) findViewById(R.id.discardButton);
        Button save = (Button) findViewById(R.id.saveButton);
        Bundle gameData = this.getIntent().getExtras();
        int index = gameData.getInt("index");
        super.onCreate(savedInstanceState);

        Switch favoriteSwitch = (Switch) findViewById(R.id.switch1);
        TextView gameTitle = (TextView) findViewById(R.id.gameTitle);

        Game curr_game = GameList.getInstance().games.get(index);

        gameTitle.setText(curr_game.title);
        favoriteSwitch.setChecked(curr_game.favorite);


        save.setOnClickListener(new game_edit.GameButtonListener(curr_game));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.Intent home = new android.content.Intent(getBaseContext(), MainActivity.class);
                startActivity(home);
            }
        });

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.Intent home = new android.content.Intent(getBaseContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }
}