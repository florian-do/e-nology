package com.enology.eip.e_nology;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;

import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.api.json.getCaveResponse;
import com.enology.eip.e_nology.api.json.getResearchResponse;
import com.enology.eip.e_nology.cave.fragment.CaveFragment;
import com.enology.eip.e_nology.cave.fragment.CavePageFragment;
import com.enology.eip.e_nology.cave.nfc.NfcFragment;
import com.enology.eip.e_nology.cave.scanner.ScannerFragment;
import com.enology.eip.e_nology.menu.NavigationDrawerFragment;
import com.enology.eip.e_nology.catalog.fragment.CatalogPageFragment;
import com.enology.eip.e_nology.catalog.fragment.CatalogFragment;
import com.enology.eip.e_nology.recipes.fragment.RecipesFragment;
import com.enology.eip.e_nology.recipes.fragment.RecipesPageFragment;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        CatalogFragment.OnFragmentInteractionListener,
        CaveFragment.OnFragmentInteractionListener,
        ScannerFragment.OnFragmentInteractionListener,
        NfcFragment.OnFragmentInteractionListener,
        RecipesFragment.OnFragmentInteractionListener,
        RecipesPageFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private static final String ARG_TOKEN = "token";

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence    mTitle;
    private String          token = null;

    public final static String DEBUG_TAG = "MainActivity.debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.

        final ActionBar actionBar = getActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(128, 235, 141, 105)));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(190, 50, 53, 66)));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        if (token == null)
        {
            Intent intent = getIntent();
            token = intent.getExtras().getString(ARG_TOKEN);
        }

        Log.d(DEBUG_TAG, "Position :"+position);
        FragmentManager fragmentManager = getFragmentManager();
        switch (position)
        {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, CaveFragment.newInstance(token))
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, CatalogFragment.newInstance(token))
                        .addToBackStack(null)
                        .commit();
            break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RecipesFragment.newInstance(token))
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setIcon(R.drawable.icon_navigation);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = LayoutInflater.from(this);
        View actionBarView = inflator.inflate(R.layout.action_bar_title, null);
        //((TextView)v.findViewById(R.id.title_action_bar)).setText(this.getTitle());

        //assign the view to the actionbar
        this.getActionBar().setCustomView(actionBarView);

        //actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void onRecipesSelected(getResearchResponse research)
    {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, RecipesPageFragment.newInstance(research))
                .addToBackStack(null)
                .commit();
    }

    public void onBottleSelected(getBottlesResponse bottles)
    {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, CatalogPageFragment.newInstance(bottles))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onCaveSelected(getCaveResponse cave) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, CavePageFragment.newInstance(cave))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void addBottleFromScanner(String content, String FormatName) {
        Log.d(DEBUG_TAG, "addBottleFromScanner");
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, CaveFragment.newInstance(token))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onWineSelected(getBottleByIdResponse bottle)
    {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, CatalogPageFragment.newInstanceSommelier(bottle))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}