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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    private View rootView;

    private List<getBottlesResponse> bottles;

    private RecipesListAdapter      rAdapter;
    private ListView                listView;
    private ImageView               recipes_ic_sync;
    private TextView                recipes_ic_text;
    private PtrClassicFrameLayout   mPtrFrame;

    public static final String DEBUG_TAG = "RecipesFragment";

    private OnFragmentInteractionListener mListener;

    public static RecipesFragment newInstance() {
        //RecipesFragment fragment = new RecipesFragment();
        return new RecipesFragment();
    }

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_fragment_bottles, container, false);
        listView = (ListView) view.findViewById(R.id.recipes_list);
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
                listView.setAdapter(rAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                recipes_ic_sync.setVisibility(View.VISIBLE);
                recipes_ic_text.setVisibility(View.VISIBLE);
                Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(DEBUG_TAG, bottles.get(position).getName());
                if (mListener != null) {
                    mListener.onBottleSelected(bottles.get(position).getDesc(), bottles.get(position).getName());
                }
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

    public interface OnFragmentInteractionListener {
        public void onBottleSelected(String id, String name);
    }
}
