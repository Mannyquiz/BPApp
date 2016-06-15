package com.example.manny.viperprogramming;

import android.content.Context;
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

/**
 * Created by manny on 6/6/16.
 */
public class LoginFragment extends Fragment {

    private EditText userEmail;
    private EditText userPassword;
    private TextView forgotPassword;
    private Button loginBtn;
    private Button signUpBtn;
    private OnLoginListener mListener;

    public LoginFragment(){}

    public interface OnLoginListener{
        void onLogin(String email, String password);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                login();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.replaceFragment(new ForgotPasswordFragment());

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.replaceFragment(new SignUpFragment());

            }
        });



        return layout;
    }

    public void login(){

        if(userEmail.length() == 0 || userPassword.length() == 0){
            return;
        }
        userEmail.setError(null);
        userPassword.setError(null);
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        mListener.onLogin(email, password);

        clearLoginInput();
    }

    public void clearLoginInput(){
        userEmail.getText().clear();
        userPassword.getText().clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (OnLoginListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
            + " must implement OnFragment");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
