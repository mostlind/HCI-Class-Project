package com.example.goodplays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


public class GameCardListAdapter extends BaseAdapter {
    private Context context;
    private Game[] data;
    private static LayoutInflater inflater = null;

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

    public GameCardListAdapter(Context context, Game[] data) {
        this.context = context;
        this.data = data;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ? inflater.inflate(R.layout.game_card, null) : convertView;

        TextView titleView = view.findViewById(R.id.gameTitle);
        titleView.setText(data[position].title);

        ImageView imageView = view.findViewById((R.id.gameCardImage));
        new DownloadImageTask(imageView).execute(data[position].imageUrl);

        return view;
    }
}
