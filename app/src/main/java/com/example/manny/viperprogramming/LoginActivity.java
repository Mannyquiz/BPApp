package com.example.manny.viperprogramming;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        Log.d("flow", "OnCreate LoginActivity");

        if(findViewById(R.id.container)!= null){

            if(savedInstanceState != null){
                Log.d("flow", "from saveInstance State ");
                return;
            }

            getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), null).commit();
        }
    }

    public void replaceFragment(Fragment frag){

        if(frag.isVisible()){
            Log.d("flow", "isVisible is working");
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, frag, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("flow", "onResume LoginActivity");
    }
}
