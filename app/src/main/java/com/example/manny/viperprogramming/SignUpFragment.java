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

import com.example.manny.viperprogramming.data.User;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by manny on 6/7/16.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    public static final String FIRE_BASE_URL = "https://bpgo.firebaseio.com/";
    public static final String USERS_ROUTE = "users/";

    Firebase mFireBaseRef;

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userConfirmPassword;
    private Button userSignUpBtn;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFireBaseRef = new Firebase(FIRE_BASE_URL + USERS_ROUTE);

        Log.d("flow", "OnCreate SingUpFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.sign_up_fragment, container, false);

        userName = (EditText) layout.findViewById(R.id.userNameET);
        userEmail = (EditText) layout.findViewById(R.id.userEmailET);
        userPassword = (EditText) layout.findViewById(R.id.userPasswordET);
        userConfirmPassword = (EditText) layout.findViewById(R.id.userConfirmPasswordET);
        userSignUpBtn = (Button) layout.findViewById(R.id.userSignUpBtn);

        userSignUpBtn.setOnClickListener(this);

        Log.d("flow", "OnCreateView SingUpFragment");
        return layout;
    }

    public void signUp(){
        if(!loginCredentialsValidated()){
            return;
        }

        mFireBaseRef.createUser(userEmail.getText().toString(), userPassword.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>(){

            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("flow", "Successfully created user account with uid: " + result.get("uid"));

                User user = new User(userEmail.getText().toString(), userName.getText().toString());
                String uid = (String) result.get("uid");

                mFireBaseRef.child(uid).setValue(user);
                clearSignUpText();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("flow", "ERROR: " + firebaseError.toString() + " " + firebaseError.getMessage() + " " + firebaseError.getDetails());
            }
        });
    }

    public boolean loginCredentialsValidated(){
        boolean isValid = true;

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()){
            isValid = false;
        }

        if(userName.length() < 4){
            Log.d("flow", "UserName length is less than 4");
            isValid = false;
        }

        if(userPassword.length() < 5){
            Log.d("flow", "Password length is less than 5");
            isValid = false;
        }

        if (!userPassword.getText().toString().equals(userConfirmPassword.getText().toString())){
            Log.d("flow", "passwords do not the match");
            isValid = false;
        }
        return isValid;
    }

    public void clearSignUpText(){
        Log.d("flow", "Clearing all EditText");

        userName.getText().clear();
        userEmail.getText().clear();
        userPassword.getText().clear();
        userConfirmPassword.getText().clear();
    }

    @Override
    public void onClick(View v) {

        Log.d("flow", "Sign up button clicked");
        signUp();

    }
}
