package com.enology.eip.e_nology.recipes.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.SplashActivity;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.LoginResponse;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.recipes.adapter.RecipesListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipesFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;

    private List<getBottlesResponse> bottles;

    private RecipesListAdapter      rAdapter;
    private GridView                gridView;
    private ImageView               recipes_ic_sync;
    private TextView                recipes_ic_text;
    private PtrClassicFrameLayout   mPtrFrame;

    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    public static final String DEBUG_TAG = "RecipesFragment";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipesFragment newInstance(String param1, String param2) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_fragment_bottles, container, false);
        gridView = (GridView) view.findViewById(R.id.recipes_grid);
        recipes_ic_sync = (ImageView) view.findViewById(R.id.recipes_ic_sync);
        recipes_ic_text= (TextView) view.findViewById(R.id.recipes_ic_text);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_frame);
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.setupPtr();

        //recipes_ic_sync.setVisibility(View.INVISIBLE);
        //gridView.setVisibility(View.INVISIBLE);

        RestClient.get().getBottles(new Callback<List<getBottlesResponse>>() {
            @Override
            public void success(List<getBottlesResponse> bottlesResponse, Response response)
            {
                Log.d(DEBUG_TAG, "SUCCESS : RESPOSNE : " + response.getStatus());
                bottles = bottlesResponse;
                if (rAdapter == null) {
                    rAdapter = new RecipesListAdapter(getActivity(), R.layout.list_adapter_recipes, bottles);
                }
                gridView.setAdapter(rAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                recipes_ic_sync.setVisibility(View.VISIBLE);
                recipes_ic_text.setVisibility(View.VISIBLE);
                Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
            }
        });
    }

    protected void setupViews(final PtrClassicFrameLayout ptrFrame) {

    }

    private void setupPtr()
    {
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                RestClient.get().getBottles(new Callback<List<getBottlesResponse>>() {
                    @Override
                    public void success(List<getBottlesResponse> bottlesResponse, Response response) {
                        Log.d(DEBUG_TAG, "SUCCESS : RESPOSNE : " + response.getStatus());
                        bottles = bottlesResponse;
                        mPtrFrame.refreshComplete();
                        rAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        recipes_ic_sync.setVisibility(View.VISIBLE);
                        Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
                    }
                });
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        setupViews(mPtrFrame);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("RecipesFragment", "onAttach");
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
        Log.d("RecipesFragment", "onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
