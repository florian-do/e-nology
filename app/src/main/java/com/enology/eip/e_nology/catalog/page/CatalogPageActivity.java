package com.enology.eip.e_nology.catalog.page;


import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.catalog.page.Fragment.CommentaireFragment;
import com.enology.eip.e_nology.catalog.page.Fragment.DescriptionFragment;
import com.enology.eip.e_nology.catalog.page.Fragment.GeneralFragment;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CatalogPageActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static final String ARG_TOKEN = "token";
    private static final String ARG_ID = "bottle_id";

    private getBottleByIdResponse   bottle;
    private String  token;
    private String  id;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_activity_catalog_page);

        Intent intent = getIntent();
        token = intent.getExtras().getString(ARG_TOKEN);
        id = intent.getExtras().getString(ARG_ID);

        loading = (ProgressBar) findViewById(R.id.google_progress);
        loading.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(this).build());
        loading.setVisibility(View.VISIBLE);

        RestClient.getToken(token).getBottleById(id, new Callback<getBottleByIdResponse>() {
            @Override
            public void success(getBottleByIdResponse getBottleByIdResponse, Response response) {
                bottle = getBottleByIdResponse;
                loading.setVisibility(View.INVISIBLE);
                mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
                mViewPager = (ViewPager) findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.catalog_fragment_catalog_page_general, container, false);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // General
                    return GeneralFragment.newInstance(bottle);
                case 1: // Commentaire
                    return CommentaireFragment.newInstance(bottle);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Général";
                case 1:
                    return "Commentaires";
            }
            return null;
        }
    }
}
