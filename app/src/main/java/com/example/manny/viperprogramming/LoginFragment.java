package com.example.manny.viperprogramming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by manny on 6/6/16.
 */
public class LoginFragment extends Fragment {

    public static final String FIRE_BASE_URL = "https://bpgo.firebaseio.com/";
    public static final String USERS_ROUTE = "users/";

    Firebase mFireBaseRef;

    private EditText userEmail;
    private EditText userPassword;
    private TextView forgotPassword;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFireBaseRef = new Firebase(FIRE_BASE_URL + USERS_ROUTE);

        Log.d("flow", "OnCreate LoginFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.login_fragment_main, container, false);

        userEmail = (EditText) layout.findViewById(R.id.emailET);
        userPassword = (EditText) layout.findViewById(R.id.passwordET);
        forgotPassword = (TextView) layout.findViewById(R.id.forgotPassword);
        loginBtn = (Button) layout.findViewById(R.id.loginBtn);
        signUpBtn = (Button) layout.findViewById(R.id.signUpBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("flow", "Login Button Clicked");
                login();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.replaceFragment(new ForgotPasswordFragment());
                Log.d("flow", "Clicked ForgotPasswordFragment");
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.replaceFragment(new SignUpFragment());
                Log.d("flow", "Clicked SignUp Fragment");
            }
        });

        Log.d("flow", "OnCreateView LoginFragment");

        return layout;
    }

    public void login(){

        if(userEmail.length() == 0 || userPassword.length() == 0){
            Log.d("flow", "more: " + userEmail.getText().toString());
            return;
        }

        mFireBaseRef.authWithPassword(userEmail.getText().toString(), userPassword.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d("flow", "User Authentication ID: " + authData.getUid()
                        + " Token: " + authData.getToken()
                        + " expires: " + authData.getExpires());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("flow", "User authentication Failed " + firebaseError.getMessage());
            }
        });

        LoginActivity activity = (LoginActivity) getActivity();
        activity.replaceFragment(new HomeFragment());

        clearLoginInput();
    }

    public void clearLoginInput(){
        userEmail.getText().clear();
        userPassword.getText().clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("flow", "onResume LoginFragment");
    }
}
