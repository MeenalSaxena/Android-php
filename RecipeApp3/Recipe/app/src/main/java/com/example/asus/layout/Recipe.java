package com.example.asus.layout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static android.provider.Settings.System.AIRPLANE_MODE_ON;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Recipe extends Activity {

    ArrayList<RecipeData> recipeList;
    TextView tv1,tv2;
    Integer t;
    Integer id;
    ImageView iv1;
    int position;
    Button b1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        iv1=(ImageView) findViewById(R.id.imageview1);
        b1=(Button) findViewById(R.id.bcamera);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1=new Intent(Recipe.this,CameraActivity.class);
                startActivity(in1);
            }
        });

        recipeList = new ArrayList<RecipeData>();
        new JSONAsyncTask().execute("http://10.0.2.2/renew/Android-php/RecipeApp3/database/display.php");

        id = getIntent().getIntExtra("ID",1);
//        System.out.println("**************************");
//        System.out.println(id);


        t=id;

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        System.out.println(t);


//        adapter = new RecipeAdapter(getApplicationContext(), R.layout.activity_image, recipeList);
//        System.out.println(adapter);
//        System.out.println(adapter);

    }
    static boolean isAirplaneModeOn(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return Settings.System.getInt(contentResolver, AIRPLANE_MODE_ON, 0) != 0;
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;



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
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>

                HttpGet httppost = new HttpGet(urls[0]);
                System.out.println("--------------------------");
                System.out.println(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("data");

                    for (int i = 0; i < jarray.length(); i++) {
//                        System.out.println(t);
                    if (t==i) {
                        JSONObject object = jarray.getJSONObject(i);
                        RecipeData recipe = new RecipeData();
//                        System.out.println("#########");
//                        System.out.println(object);
                        recipe.setName(object.getString("recipe_name"));
                        recipe.setDescription(object.getString("recipe_description"));
                        recipe.setImage(object.getString("image_url"));
                        recipeList.add(recipe);
                        tv1.setText(recipeList.get(position).getName());
                        tv2.setText(recipeList.get(position).getDescription());
//                        System.out.println("IMAGE URL: "+ recipeList.get(position).getImage());
                    }
                }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
//            dialog.cancel();
//            dialog.dismiss();
            Picasso.with(Recipe.this).load(recipeList.get(position).getImage()).into(iv1);
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }
}
