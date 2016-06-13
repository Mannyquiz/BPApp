package com.example.manny.viperprogramming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manny.viperprogramming.data.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by manny on 6/12/16.
 */
public class HomeFragment extends Fragment {
    public static final String FIRE_BASE_URL = "https://bpgo.firebaseio.com/";
    public static final String USERS_ROUTE = "users/";

    Firebase mFireBaseRef;

    private Button logOutBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("flow", "onCreate: HomeFragment");

        mFireBaseRef = new Firebase(FIRE_BASE_URL + USERS_ROUTE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        logOutBtn = (Button) view.findViewById(R.id.logOutBtn);


        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("flow", "log out Button Clicked");
                logout();
            }
        });

        return view;
    }

    public void logout(){

        mFireBaseRef.unauth();
        Log.d("flow", "logging out ");

    }
}
