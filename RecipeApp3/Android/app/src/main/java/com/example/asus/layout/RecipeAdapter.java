package com.example.asus.layout;

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

import java.io.InputStream;
import java.util.ArrayList;

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
//            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvName = (TextView) v.findViewById(R.id.textView1);
            holder.tvDescription = (TextView) v.findViewById(R.id.textView2);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        //holder.imageview.setImageResource(R.drawable.ic_launcher);
        //new DownloadImageTask(holder.imageview).execute(recipeList.get(position).getImage());
        holder.tvName.setText(recipeList.get(position).getName());
        holder.tvDescription.setText(recipeList.get(position).getDescription());
       return v;

    }

    static class ViewHolder {
        //public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;
        }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }

//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }

//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }

    }
