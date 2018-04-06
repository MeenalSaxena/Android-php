package com.ex.sams.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private TextView textView;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    LoginButton loginButton;
    ImageView imageView;
    FacebookCallback<LoginResult> callback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (LoginButton) findViewById(R.id.loginbutton);
        loginButton.setReadPermissions("email");
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();




        if (AccessToken.getCurrentAccessToken() != null) {
            GraphLoginRequest(AccessToken.getCurrentAccessToken());

            // If already login in then show the Toast.
            Toast.makeText(MainActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();

        } else {

            // If not login in then show the Toast.
            Toast.makeText(MainActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
        }

        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newprofile) {
                displayMessage(newprofile);
            }

            private void displayMessage(Profile newprofile) {
            }
        };


    private void GraphLoginRequest(AccessToken currentAccessToken) {
    }

    callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            GraphLoginRequest(loginResult.getAccessToken());
        }



        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this,"Login Canceled",Toast.LENGTH_SHORT).show();
        }


    public void onError(FacebookException error) {
        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
    }
}
};loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager,callback);
        accessTokenTracker=new AccessTokenTracker() {


protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newToken) {
        if (accessToken2 == null) {

        // Clear the TextView after logout.
        FacebookDataTextView.setText("");

        }
        }};
        }

        }

protected void GraphLoginRequest(AccessToken accessToken){
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
        new GraphRequest.GraphJSONObjectCallback() {
@Override
public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

        try {

        // Adding all user info one by one into TextView.
        FacebookDataTextView.setText("ID: " + jsonObject.getString("id"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nName : " + jsonObject.getString("name"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nFirst name : " + jsonObject.getString("first_name"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nLast name : " + jsonObject.getString("last_name"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nEmail : " + jsonObject.getString("email"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nGender : " + jsonObject.getString("gender"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nLink : " + jsonObject.getString("link"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nTime zone : " + jsonObject.getString("timezone"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nLocale : " + jsonObject.getString("locale"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nUpdated time : " + jsonObject.getString("updated_time"));

        FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nVerified : " + jsonObject.getString("verified"));
        }
        catch (JSONException e) {
        e.printStackTrace();
        }
        }
        });

        Bundle bundle = new Bundle();
        bundle.putString(
        "fields",
        "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified"
        );
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

        }


protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        }
private void displayMessage(Profile profile) {
        if (profile != null) {
        textView.setText(profile.getName());
        String url=profile.getProfilePictureUri(150,150).toString();
        Glide.with(getApplicationContext()).load(url).error(R.mipmap.ic_launcher).into((imageView));
        }
        }
public void onStop(){
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
        }


public void onResume(){
        super.onResume();
        Profile profile=Profile.getCurrentProfile();

        displayMessage(profile);
        AppEventsLogger.activateApp(MainActivity.this);


        }
        }

        }