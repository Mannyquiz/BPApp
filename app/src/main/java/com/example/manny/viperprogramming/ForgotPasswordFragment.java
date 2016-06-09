package com.example.manny.viperprogramming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by manny on 6/7/16.
 */
public class ForgotPasswordFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("flow", "onCreate ForgotPasswordFragment ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("flow", "onCreateView ForgotPasswordFragment ");
        View layout = inflater.inflate(R.layout.forgot_password_fragment, container, false);
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("flow", "onResume ForgotPasswordFragment");
    }
}
