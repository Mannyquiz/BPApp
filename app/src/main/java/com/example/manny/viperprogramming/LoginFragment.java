package com.example.manny.viperprogramming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by manny on 6/6/16.
 */
public class LoginFragment extends Fragment {

    private TextView forgotPassword;
    private Button signUpBtn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("flow", "OnCreate LoginFragment");
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.login_fragment_main, container, false);

        forgotPassword = (TextView) layout.findViewById(R.id.forgotPassword);
        signUpBtn = (Button) layout.findViewById(R.id.btnSignUp);

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

    @Override
    public void onResume() {
        super.onResume();
        Log.d("flow", "onResume LoginFragment");
    }
}
