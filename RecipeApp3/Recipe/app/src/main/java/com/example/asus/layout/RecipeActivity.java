package com.example.asus.layout;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.support.v4.content.ContextCompat.startActivity;

public class RecipeActivity extends AppCompatActivity  {
    ImageView[] iv;
    ImageView image;
    String ivID[];
    RecipeData recipe;
    int id;
    int resID;
    int i,id1;
    String a;
    TextView tv;
    String urls="http://10.0.2.2/renew/Android-php/RecipeApp3/database/display.php";
    int position=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
//        Notification();
//        iv = new ImageView[4];


        ivID= new String[10];
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<CreateList> createLists = null;
        try {
            createLists = new JSONAsyncTask(getApplicationContext()).execute("http://10.0.2.2/renew/Android-php/RecipeApp3/database/display.php").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("Error in Asynctask!!!!!!!!");
            e.printStackTrace();
        }
        MyAdapter adapter = new MyAdapter(getApplicationContext(), createLists);
        recyclerView.setAdapter(adapter);

//        for(i=1; i<=5; i++)
//            {


//                System.out.println(image);
//                if (image != null)
//        {
//
//            Picasso
//                    .with(this)
//                    .load("http://10.0.2.2/renew/Android-php/RecipeApp3/database/images/jill.jpg")
//                    .into(image);
//        }
//


//                Picasso.with(this).load(recipeList.get(position).getImage()).into(image);

//Picasso.with(this).load(recipeList.get(position).getImage()).into(image);
//                    image.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
////                            a+= ivID[i];
//                    Intent in=new Intent(RecipeActivity.this, Recipe.class);
//                    in.putExtra("ID", String.valueOf(ivID[ex]));
//                    startActivity(in);
//                    }
//                    });

//

//        System.out.println(ivID);
//        System.out.println(image);
//        }

    }




    class JSONAsyncTask extends android.os.AsyncTask<String, Void, ArrayList<CreateList>> {

        android.app.ProgressDialog dialog;

        public JSONAsyncTask(Context applicationContext) {

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog = new ProgressDialog(Recipe.this);
//            dialog.setMessage("Loading, please wait");
//            dialog.setTitle("Connecting tttttt");
//            dialog.show();
//            dialog.setCancelable(true);
        }

        @Override
        protected ArrayList<CreateList> doInBackground(String... urls) {
            ArrayList<CreateList> theimage = new ArrayList<>();
            try {

                //------------------>>

                org.apache.http.client.methods.HttpGet httppost = new org.apache.http.client.methods.HttpGet(urls[0]);
                System.out.println("--------------------------");
                System.out.println(urls[0]);
                org.apache.http.client.HttpClient httpclient = new org.apache.http.impl.client.DefaultHttpClient();
                org.apache.http.HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    org.apache.http.HttpEntity entity = response.getEntity();
                    String data = org.apache.http.util.EntityUtils.toString(entity);

                    org.json.JSONObject jsono = new org.json.JSONObject(data);
                    org.json.JSONArray jarray = jsono.getJSONArray("data");

                    for (int i = 0; i < jarray.length(); i++) {
//                        System.out.println(t);
//                        ivID[i] = "i" +(i+1);
//
//
//                        final int ex = i;
//                        resID = getResources().getIdentifier(ivID[ex], "id", getPackageName());
//                        image = (ImageView) findViewById(resID);
                        org.json.JSONObject object = jarray.getJSONObject(i);
//                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                        System.out.println(object);

                        CreateList createList = new CreateList();
                        createList.setImage_title(object.getString("recipe_name"));
                        createList.setImage_ID(object.getString("image_url"));
                        theimage.add(createList);

                        Intent intent = new Intent(RecipeActivity.this, NotificationView.class);
                        intent.putExtra("title", object.getString("recipe_name"));
                        intent.putExtra("text", object.getString("recipe_description"));
                        PendingIntent pIntent = PendingIntent.getActivity(RecipeActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(RecipeActivity.this)
                // Set Icon
                .setSmallIcon(R.drawable.logosmall)
                // Set Ticker Message
                .setTicker(getString(R.string.notificationticker))
                // Set Title
                .setContentTitle(object.getString("recipe_name"))
                // Set Text
                .setContentText(object.getString("recipe_description"))
                // Add an Action Button below Notification
                .addAction(R.mipmap.ic_launcher, "Show", pIntent)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                // Dismiss Notification
                .setAutoCancel(true);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
// recipe = new RecipeData();
//                        recipe.setName(object.getString("recipe_name"));
//                        recipe.setDescription(object.getString("recipe_description"));
//                        recipe.setImage(object.getString("image_url"));
////                        System.out.println(recipe.getImage());
//
//                        Picasso.with(RecipeActivity.this).load(recipe.getImage()).into(image);


//                        tv1.setText(recipeList.get(position).getName());
//                        tv2.setText(recipeList.get(position).getDescription());
//                        tv.setText(recipe.getImage());

                }



                }

                //------------------>>

            } catch (org.apache.http.ParseException e1) {
                e1.printStackTrace();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } catch (org.json.JSONException e) {
                e.printStackTrace();
            }
            return theimage;
        }

        protected void onPostExecute(ArrayList<CreateList> result) {
//            dialog.cancel();
//            dialog.dismiss();



        }
    }

//    public void Notification() {
//        // Set Notification Title
//        String strtitle = getString(R.string.notificationtitle);
//        // Set Notification Text
//        String strtext = getString(R.string.notificationtext);
//
//        // Open NotificationView Class on Notification Click
//        Intent intent = new Intent(this, NotificationView.class);
//        // Send data to NotificationView Class
//        intent.putExtra("title", strtitle);
//        intent.putExtra("text", strtext);
//        // Open NotificationView.java Activity
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        //Create Notification using NotificationCompat.Builder
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                // Set Icon
//                .setSmallIcon(R.drawable.logosmall)
//                // Set Ticker Message
//                .setTicker(getString(R.string.notificationticker))
//                // Set Title
//                .setContentTitle(getString(R.string.notificationtitle))
//                // Set Text
//                .setContentText(getString(R.string.notificationtext))
//                // Add an Action Button below Notification
//                .addAction(R.mipmap.ic_launcher, "Action Button", pIntent)
//                // Set PendingIntent into Notification
//                .setContentIntent(pIntent)
//                // Dismiss Notification
//                .setAutoCancel(true);
//
//        // Create Notification Manager
//        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        // Build Notification with Notification Manager
//        notificationmanager.notify(0, builder.build());
//
//    }

//    public void CustomNotification() {
//        // Using RemoteViews to bind custom layouts into Notification
//        RemoteViews remoteViews = new RemoteViews(getPackageName(),
//                R.layout.customnotification);
//
//        // Set Notification Title
//        String strtitle = getString(R.string.customnotificationtitle);
//        // Set Notification Text
//        String strtext = getString(R.string.customnotificationtext);
//
//        // Open NotificationView Class on Notification Click
//        Intent intent = new Intent(this, NotificationView.class);
//        // Send data to NotificationView Class
//        intent.putExtra("title", strtitle);
//        intent.putExtra("text", strtext);
//        // Open NotificationView.java Activity
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                // Set Icon
//                .setSmallIcon(R.drawable.logosmall)
//                // Set Ticker Message
//                .setTicker(getString(R.string.customnotificationticker))
//                // Dismiss Notification
//                .setAutoCancel(true)
//                // Set PendingIntent into Notification
//                .setContentIntent(pIntent)
//                // Set RemoteViews into Notification
//                .setContent(remoteViews);
//
//        // Locate and set the Image into customnotificationtext.xml ImageViews
//        remoteViews.setImageViewResource(R.id.imagenotileft,R.mipmap.ic_launcher);
//        remoteViews.setImageViewResource(R.id.imagenotiright,R.drawable.androidhappy);
//
//        // Locate and set the Text into customnotificationtext.xml TextViews
//        remoteViews.setTextViewText(R.id.title,getString(R.string.customnotificationtitle));
//        remoteViews.setTextViewText(R.id.text,getString(R.string.customnotificationtext));
//
//        // Create Notification Manager
//        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        // Build Notification with Notification Manager
//        notificationmanager.notify(0, builder.build());
//
//    }

}
