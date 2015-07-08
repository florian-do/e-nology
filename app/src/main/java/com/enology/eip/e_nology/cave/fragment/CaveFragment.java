package com.enology.eip.e_nology.cave.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.cave.scanner.ScannerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaveFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static CaveFragment newInstance() {
        return new CaveFragment();
    }

    public CaveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cave_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cave, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        FragmentManager fragmentManager = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.menu_add:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ScannerFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void addBottleFromScanner();
    }

}
