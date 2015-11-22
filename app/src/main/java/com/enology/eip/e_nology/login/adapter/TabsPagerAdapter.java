package com.enology.eip.e_nology.login.adapter;

/**
 * Created by Lolo on 16/04/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.enology.eip.e_nology.login.fragment.PasswordRecoveryFragment;
import com.enology.eip.e_nology.login.fragment.SigninFragment;
import com.enology.eip.e_nology.login.fragment.SignupFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new PasswordRecoveryFragment();
            case 1:
                // Signin fragment activity
                return new SigninFragment();
            case 2:
                // Movies fragment activity
                return new SignupFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}
