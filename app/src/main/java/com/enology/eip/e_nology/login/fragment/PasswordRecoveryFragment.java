package com.enology.eip.e_nology.login.fragment;

/**
 * Created by Lolo on 16/04/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enology.eip.e_nology.R;

public class PasswordRecoveryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login_fragment_passwordrecovery, container, false);

        return rootView;
    }

}
