package com.ex.sams.login;
        import android.os.Bundle;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.Toast;
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


public class MainActivity extends AppCompatActivity {

    // Declaring Variables.

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

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


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
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

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
    protected void GraphLoginRequest(AccessToken accessToken){
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
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

                            textView.setText(textView.getText() + "\nEmail : " + jsonObject.getString("email"));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified"
        );
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
}