package com.enology.eip.e_nology.login.fragment;

/**
 * Created by Lolo on 16/04/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.enology.eip.e_nology.MainActivity;
import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.SplashActivity;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.CreateUserResponse;
import com.enology.eip.e_nology.api.json.LoginResponse;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class SigninFragment extends Fragment {

    private static String DEBUG_TAG = "Signin_Fragment";
    private static final String ARG_TOKEN = "token";

    private EditText    username;
    private EditText    password;
    private ImageButton signin;

    private ImageView   logo;
    private ProgressBar loading;
    private Animation   fadeInAnimation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d(DEBUG_TAG, "OnCreateView");
        View rootView = inflater.inflate(R.layout.login_fragment_signin, container, false);

        username = (EditText) rootView.findViewById(R.id.edittext_email_address);
        password = (EditText) rootView.findViewById(R.id.edittext_password);

        /* TEMP */
        username.setText("chat");
        password.setText("pusheen016");
        /* TEMP */

        loading = (ProgressBar) rootView.findViewById(R.id.google_progress);
        logo = (ImageView) rootView.findViewById(R.id.logo);
        loading.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(getActivity()).build());
        fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);

        signin = (ImageButton) rootView.findViewById(R.id.button_signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected())
                {
                    signin.setClickable(false);
                    Log.d(DEBUG_TAG, "Network Info");
                    LoginResponse tmp = new LoginResponse(username.getText().toString(),
                            password.getText().toString());

                    logo.setVisibility(View.INVISIBLE);
                    loading.setVisibility(View.VISIBLE);

                    RestClient.get().login(tmp, new Callback<LoginResponse>() {
                        @Override
                        public void success(LoginResponse loginResponse, Response response) {
                            logo.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.INVISIBLE);

                            Log.d(DEBUG_TAG, "SUCCESS : " + loginResponse.getToken() + " RESPOSNE : " + response.getStatus());

                            signin.setClickable(true);
                            Intent intent = new Intent(getActivity(), SplashActivity.class);
                            intent.putExtra(ARG_TOKEN, loginResponse.getToken());
                            startActivity(intent);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            signin.setClickable(true);
                            logo.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.INVISIBLE);
                            String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
                            Log.d("failure", json.toString());
                            Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
                        }
                    });
                }
            }
        });
        return rootView;
    }
}