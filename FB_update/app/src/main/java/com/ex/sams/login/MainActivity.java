package com.ex.sams.login;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.Parcelable;
        import android.webkit.WebView;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.Toast;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
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

        import java.io.Serializable;
        import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // Declaring Variables.

    private CallbackManager callbackManager;
    private TextView textView;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    LoginButton loginButton;
    ImageView imageView;
    String  email;
    GraphRequest graphRequest;
    WebView display;
    FacebookCallback<LoginResult> callback;
    String URL="http://10.0.2.2/data.php";

    Bundle bundle = new Bundle();
    JSONParser jsonParser=new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();
        display=(WebView)findViewById(R.id.webView);
        loginButton= (LoginButton)findViewById(R.id.loginbutton);


        loginButton.registerCallback(callbackManager, callback);
        loginButton.setReadPermissions("user_friends");
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphLoginRequest(AccessToken.getCurrentAccessToken());

            // If already login in then show the Toast.
            Toast.makeText(MainActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();

        } else {

            // If not login in then show the Toast.
            Toast.makeText(MainActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
        }


        // Adding Click listener to Facebook login button.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult){
                AccessToken accessToken;
                accessToken = loginResult.getAccessToken();
                Profile profile;
                profile= Profile.getCurrentProfile();

                // Calling method to access User Data After successfully login.
                GraphLoginRequest(loginResult.getAccessToken());
            }

            @Override
            public void onCancel(){

                Toast.makeText(MainActivity.this,"Login Canceled",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception){

                Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
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
    protected Bundle GraphLoginRequest(AccessToken accessToken){
        graphRequest = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

                        try {

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


                            //textView.setText(textView.getText() + "\nEmail : " + jsonObject.getString("email"));


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        bundle.putString("fields", "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
        return bundle;

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
        AppEventsLogger.activateApp(MainActivity.this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(MainActivity.this);

    }
    private abstract class AttemptLogin extends AsyncTask<Bundle, Void, Void> {



        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }


        protected JSONObject doInBackground(String... args) {

            String name= bundle.getString(args[1]);
            String first_name= bundle.getString(args[2]);
            String last_name= bundle.getString(args[3]);
            String gender= bundle.getString(args[4]);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("first_name", first_name));
            params.add(new BasicNameValuePair("last_name", last_name));
            params.add(new BasicNameValuePair("gender", gender));
            JSONObject js = JSONParser.makeHttpRequest(URL, "POST",params );
            Intent in=new Intent(MainActivity.this, JSONParser.class);
            in.putExtra("Json", (Parcelable) js);
            return js;
        }
//        protected JSONObject doInBackground(String... args) {
//
////            Bundle bundle = new Bundle();
////            bundle.putString("fields", "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified");
////            graphRequest.setParameters(bundle);
////            graphRequest.executeAsync();
//
//            String name= args[1];
//            String first_name= args[2];
//            String last_name= args[3];
//            String gender= args[4];
//
//            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("name", name));
//            params.add(new BasicNameValuePair("first_name", first_name));
//            params.add(new BasicNameValuePair("last_name", last_name));
//            params.add(new BasicNameValuePair("gender", gender));
//
//
////            JSONObject ob = new JSONObject();
//            JSONObject js = JSONParser.makeHttpRequest(URL, "POST", params);
//            return js;
//
//        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }



}
