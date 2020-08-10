package com.example.goodplays;

import android.arch.core.util.Function;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        favoriteSwitch.setOnCheckedChangeListener(new FavoriteToggledListener());

        save.setOnClickListener(new game_edit.SaveButtonListener(curr_game));


        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.Intent home = new android.content.Intent(getBaseContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }
}