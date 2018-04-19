package com.example.asus.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import static com.example.asus.layout.R.drawable.icon;

public class RecipeAdapter extends ArrayAdapter<RecipeData> {
    ArrayList<RecipeData> recipeList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public RecipeAdapter(Context context, int resource, ArrayList<RecipeData> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        recipeList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);


            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);

            holder.tvName = (TextView) v.findViewById(R.id.textView1);
            holder.tvDescription = (TextView) v.findViewById(R.id.textView2);
            v.setTag(holder);
            new DownloadImageTask(holder.imageview).execute(recipeList.get(position).getImage());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("IMAGE URL: "+ recipeList.get(position).getImage());
            holder.tvName.setText(recipeList.get(position).getName());
            holder.tvDescription.setText(recipeList.get(position).getDescription());

        } else {
            holder = (ViewHolder) v.getTag();
        }

       return v;

    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;
        }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;

        }

        @SuppressLint("LongLogTag")
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];

//            System.out.println(urls[0]);

//            System.out.println("@@@@@@@@@@@@@@@@@@@");
//            System.out.println(urldisplay);

            Bitmap mIcon11 = null;

            try {
//                System.out.println("@@@@@@@@@@@@@@@@@@@");
//                System.out.println(urldisplay);
                InputStream is = new URL(urldisplay).openStream();
////                BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(urldisplay).openStream()));
//                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//                System.out.println(br);
                System.out.println(urldisplay);
//                mIcon11 = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.e("Error11111111111111111111", e.getMessage());
//                System.out.println(e.getMessage());
                e.printStackTrace();
            }
//            System.out.println("**************************");
//            System.out.println(mIcon11);
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");

            System.out.println(bmImage);
            bmImage.setImageBitmap(result);

        }

    }}
