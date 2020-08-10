package com.example.goodplays;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class addGame extends AppCompatActivity {

    ListView list;
    String titles[] = {"Cuphead", "nba2k", "spider-man", "The Witcher"};
    String descriptions [] = {"Cuphead description...", "nba2k description...", "spider-man description...", "The Witcher description..."};
    int imgs[] = {R.drawable.cuphead, R.drawable.nba2k, R.drawable.spiderman, R.drawable.thewitcher};
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);

        list = findViewById(R.id.list1);

        MyAdapter adapter = new MyAdapter(this, titles, imgs, descriptions);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position ==0) {
                    Toast.makeText(addGame.this, "Adding game...", Toast.LENGTH_SHORT).show();
                }
                if(position ==1) {
                    Toast.makeText(addGame.this, "Adding game...", Toast.LENGTH_SHORT).show();
                }
                if(position ==2) {
                    Toast.makeText(addGame.this, "Adding game...", Toast.LENGTH_SHORT).show();
                }
                if(position ==3) {
                    Toast.makeText(addGame.this, "Adding game...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String myTitles[];
        String myDescriptions[];
        int[] imgs;

        MyAdapter(Context c, String[] titles, int[] imgs, String[] descriptions) {
            super(c, R.layout.row, R.id.text1, titles);
            this.context = c;
            this.imgs = imgs;
            this.myTitles = titles;
            this.myDescriptions = descriptions;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.logo);
            TextView myTilte = row.findViewById(R.id.text1);
            TextView myDescription = row.findViewById(R.id.text2);
            images.setImageResource(imgs[position]);
            myDescription.setText(descriptions[position]);

            return row;
        }
    }
}