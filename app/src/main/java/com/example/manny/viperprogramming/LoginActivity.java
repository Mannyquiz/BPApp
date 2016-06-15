package com.example.manny.viperprogramming;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnLoginListener{

    public static final String FIRE_BASE_URL = "https://bpgo.firebaseio.com/";
    public static final String USERS_ROUTE = "users/";
    public Firebase mFireBaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(savedInstanceState == null){
            Firebase.setAndroidContext(this);
        }
        mFireBaseRef = new Firebase(FIRE_BASE_URL + USERS_ROUTE);
        if (mFireBaseRef.getAuth() == null || isExpired(mFireBaseRef.getAuth())){
            if(findViewById(R.id.container)!= null){

                if(savedInstanceState != null){
                    return;
                }
                getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), null).commit();
            }
        }else{
            replaceFragment(new HomeFragment());
        }
    }

    public void replaceFragment(Fragment frag){

        if(frag.isVisible()){
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, frag, null)
                .addToBackStack(null)
                .commit();
    }

    private boolean isExpired(AuthData authData){
        return (System.currentTimeMillis()/ 1000) >= authData.getExpires();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onLogin(String email, String password) {

            mFireBaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Log.d("flow", "User Authentication ID: " + authData.getUid()
                        + " Token: " + authData.getToken()
                        + " expires: " + authData.getExpires());

                    homeScreen();
                    //replaceFragment(new HomeFragment());
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    Log.d("flow", "User authentication Failed " + firebaseError.getMessage());
                }
            });
    }

    public void homeScreen(){
        Intent intent = new Intent(this, HomeActivity.class);

        startActivity(intent);
    }
}
