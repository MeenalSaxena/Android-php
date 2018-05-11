package com.example.asus.layout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FacebookActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private TextView textView;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    LoginButton loginButton;
    String name, first_name, last_name, gender;
    ImageView imageView;
    String  email;
    GraphRequest graphRequest;
    WebView display;
    FacebookCallback<LoginResult> callback;
    String URL="http://10.0.2.2/renew/Android-php/RecipeApp3/database/data.php";
    JSONArray jsonArray = new JSONArray();
    String check;
    Bundle bundle = new Bundle();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();
        display=(WebView)findViewById(R.id.webView);
        loginButton= (LoginButton)findViewById(R.id.loginbutton);
        display.loadUrl(URL);

        Log.w("tag", "********************************************************************************");



        loginButton.registerCallback(callbackManager, callback);
        loginButton.setReadPermissions("user_friends");
        if (AccessToken.getCurrentAccessToken() != null) {
            try {
                GraphLoginRequest(AccessToken.getCurrentAccessToken());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            // If already login in then show the Toast.
            Toast.makeText(FacebookActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();

        } else {

            // If not login in then show the Toast.
            Toast.makeText(FacebookActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
        }


        // Adding Click listener to Facebook login button.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(LoginResult loginResult){
                AccessToken accessToken;
                accessToken = loginResult.getAccessToken();
                Profile profile;
                profile= Profile.getCurrentProfile();


                // Calling method to access User Data After successfully login.
                try {
                    GraphLoginRequest(loginResult.getAccessToken());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel(){

                Toast.makeText(FacebookActivity.this,"Login Canceled",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception){

                Toast.makeText(FacebookActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
            }

        });

        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newprofile) {
                displayMessage(newprofile);
            }

            private void displayMessage(Profile newprofile) {
            }
        };


        // Detect user is login or not. If logout then clear the TextView and delete all the user info from TextView.
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                if (accessToken2 == null) {

                    // Clear the TextView after logout.
                    textView.setText("");

                }
            }
        };
    }



    // Method to access Facebook User Data.
    protected String GraphLoginRequest(AccessToken accessToken) throws JSONException {
        graphRequest = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {


                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

                        try {
//                            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//                            System.out.println(jsonObject);
                            // Adding all user info one by one into TextView.
                            textView.setText("ID: " + jsonObject.getString("id"));

                            textView.setText(textView.getText() + "\nName : " + jsonObject.getString("name"));

                            textView.setText(textView.getText() + "\nFirst name : " + jsonObject.getString("first_name"));

                            textView.setText(textView.getText() + "\nLast name : " + jsonObject.getString("last_name"));

                            textView.setText(textView.getText() + "\nGender : " + jsonObject.getString("gender"));

                            textView.setText(textView.getText() + "\nLink : " + jsonObject.getString("link"));

                            textView.setText(textView.getText() + "\nTime zone : " + jsonObject.getString("timezone"));

                            textView.setText(textView.getText() + "\nLocale : " + jsonObject.getString("locale"));

                            textView.setText(textView.getText() + "\nUpdated time : " + jsonObject.getString("updated_time"));

                            textView.setText(textView.getText() + "\nVerified : " + jsonObject.getString("verified"));


//
                            //textView.setText(textView.getText() + "\nEmail : " + jsonObject.getString("email"));


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }



                        check = jsonObject.toString();
                        System.out.println("Hello i am here"+check);

                        AttemptLogin attemptLogin= new AttemptLogin();


                        attemptLogin.execute(check,"","");

                        //  System.out.println(jsonObject);




                    }
                });

        bundle.putString("fields", "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

        return null;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        display.loadUrl(URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(FacebookActivity.this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(FacebookActivity.this);

    }

    class AttemptLogin extends AsyncTask<String, String, String> {




        @Override

        protected void onPreExecute() {

            super.onPreExecute();
            System.out.println("$$$$$$$$$$$$$$$");




        }

        //            @Override
//            protected String doInBackground(Bundle... bundles) {
//
//                HttpPost httpPost = new HttpPost(URL);
//                char[] arr = bundle.getCharArray("name");
//
////                first_name = bundle.getString("fields", "2");
////                last_name = bundle.getString("fields", "3");
////                gender = bundle.getString("fields", "4");
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
////                nameValuePairs.add(new BasicNameValuePair("name", name));
////                nameValuePairs.add(new BasicNameValuePair("first_name", first_name));
////                nameValuePairs.add(new BasicNameValuePair("last_name", last_name));
////                nameValuePairs.add(new BasicNameValuePair("gender", gender));
//
//                try {
//                    JSONArray jsonArray = new JSONArray();
//
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("name", name);
//                    jsonObject.put("first_name", first_name);
//                    jsonObject.put("last_name", last_name);
//                    jsonObject.put("gender", gender);
//                    jsonArray.put(jsonObject);
//                    nameValuePairs.add(new BasicNameValuePair("arr", String.valueOf(jsonArray.toString())));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
        @Override
        protected String doInBackground(String... strings) {

//                String first_name = strings[2];
//                String last_name = strings[3];
//                String gender=strings[4];
            HttpPost httpPost = new HttpPost(URL);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                nameValuePairs.add(new BasicNameValuePair("name", name));
//                nameValuePairs.add(new BasicNameValuePair("first_name", first_name));
//                nameValuePairs.add(new BasicNameValuePair("last_name", last_name));
//                nameValuePairs.add(new BasicNameValuePair("gender", gender));

            try {
//                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//
//                        System.out.println(check);
//                    JSONObject jsonObject = new JSONObject(check);
//                    jsonObject.put("name", name);
//                    jsonObject.put("first_name", first_name);
//                    jsonObject.put("last_name", last_name);
//                    jsonObject.put("gender", gender);
//                    jsonArray.put(jsonObject);
//                    System.out.println("########################################");


                nameValuePairs.add(new BasicNameValuePair("arr", check));
//                        System.out.println(nameValuePairs);


            } catch (Exception e) {
                e.printStackTrace();
            }


            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
            }
            catch (UnsupportedEncodingException e1)
            {

                e1.printStackTrace();
            }


            HttpClient client = new DefaultHttpClient();

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            try
            {
                HttpResponse response = client.execute(httpPost);

                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                //   System.out.println("Response"+response);
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    builder.append(line);
                }
                System.out.println("httpPost"+builder);
                if (builder.toString().length() > 0)
                {
                    return " ";
                }
            }
            catch (Exception e)
            {
                Log.e("tag", "Error insert valoracion");
                e.printStackTrace();
            }
            return "Error";
        }



        protected void onPostExecute(String result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            if (result != null) {
                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
            }


        }

    }
}