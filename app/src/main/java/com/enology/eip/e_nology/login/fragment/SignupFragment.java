package com.enology.eip.e_nology.login.fragment;

/**
 * Created by Lolo on 16/04/2015.
 */

import android.content.Context;
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

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.CreateUserResponse;
import com.enology.eip.e_nology.login.LoginActivity;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class SignupFragment extends Fragment {

    private View        rootView;
    private EditText    email;
    private EditText    firstName;
    private EditText    lastName;
    private EditText    username;
    private EditText    password;

    private ImageView   iconEmail;
    private ImageView   iconFirstName;
    private ImageView   iconLastName;
    private ImageView   iconUserName;
    private ImageView   iconPassword;


    private ProgressBar loading;
    private Animation   fadeInAnimation;

    private static String DEBUG_TAG = "Signup_Fragment";
    public final static int DEFAULT_PAGER = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.login_fragment_signup, container, false);
        ImageButton signup = (ImageButton) rootView.findViewById(R.id.button_signup);

        email = (EditText) rootView.findViewById(R.id.signup_edittext_email_address);
        firstName = (EditText) rootView.findViewById(R.id.signup_edittext_firstname);
        lastName = (EditText) rootView.findViewById(R.id.signup_edittext_lastname);
        username = (EditText) rootView.findViewById(R.id.signup_edittext_username);
        password = (EditText) rootView.findViewById(R.id.signup_edittext_password);

        iconEmail = (ImageView) rootView.findViewById(R.id.icon_email);
        iconFirstName = (ImageView) rootView.findViewById(R.id.icon_firstname);
        iconLastName = (ImageView) rootView.findViewById(R.id.icon_lastname);
        iconUserName = (ImageView) rootView.findViewById(R.id.icon_username);
        iconPassword = (ImageView) rootView.findViewById(R.id.icon_password);

        loading = (ProgressBar) rootView.findViewById(R.id.google_progress);
        loading.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(getActivity()).build());
        fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected())
                {
                    setLoading(true);
                    CreateUserResponse tmp = new CreateUserResponse(email.getText().toString(),
                                firstName.getText().toString(),
                                lastName.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString());
                    Log.d(DEBUG_TAG, "password :"+tmp.getPassword());

                    RestClient.get().createUser(tmp, new Callback<CreateUserResponse>() {
                        @Override
                        public void success(CreateUserResponse createUserResponse, Response response) {
                            setLoading(false);
                            ((LoginActivity)getActivity()).getViewPager().setCurrentItem(DEFAULT_PAGER);
                            Log.d(DEBUG_TAG, "SUCCESS : " + createUserResponse.getUsername() + " | " + createUserResponse.getPassword() + " RESPOSNE : "+response.getStatus());

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            setLoading(false);
                            String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
                            Log.d("failure", json.toString());
                            Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
                        }
                    });
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                if (hasFocus) {
                    if (password.getText().toString().length() < 6)
                        password.setError("Failed");
                    else
                        password.setError(null);
                }
            }
        });

        return rootView;
    }

    private void setLoading(boolean loading)
    {
        if (loading)
        {
            this.loading.setVisibility(View.VISIBLE);

            email.setVisibility(View.INVISIBLE);
            firstName.setVisibility(View.INVISIBLE);
            lastName.setVisibility(View.INVISIBLE);
            username.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);

            iconEmail.setVisibility(View.INVISIBLE);
            iconFirstName.setVisibility(View.INVISIBLE);
            iconLastName.setVisibility(View.INVISIBLE);
            iconUserName.setVisibility(View.INVISIBLE);
            iconPassword.setVisibility(View.INVISIBLE);
        }
        else
        {
            this.loading.setVisibility(View.INVISIBLE);

            email.setVisibility(View.VISIBLE);
            firstName.setVisibility(View.VISIBLE);
            lastName.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);

            iconEmail.setVisibility(View.VISIBLE);
            iconFirstName.setVisibility(View.VISIBLE);
            iconLastName.setVisibility(View.VISIBLE);
            iconUserName.setVisibility(View.VISIBLE);
            iconPassword.setVisibility(View.VISIBLE);
        }
    }
}