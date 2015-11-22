package com.enology.eip.e_nology.catalog.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.catalog.adapter.CatalogListAdapter;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatalogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatalogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogFragment extends Fragment
{
    private View rootView;
    private static final String ARG_TOKEN = "token";
    
    private List<getBottlesResponse> bottles;

    private CatalogListAdapter rAdapter;
    private ListView                listView;
    private ImageView               recipes_ic_sync;
    private TextView                recipes_ic_text;
    private PtrClassicFrameLayout   mPtrFrame;

    public static final String DEBUG_TAG = "CatalogFragment";

    private OnFragmentInteractionListener mListener;
    private String token;

    public static CatalogFragment newInstance(String token) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_fragment_bottles, container, false);
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

        RestClient.getToken(token).getBottles(new Callback<List<getBottlesResponse>>() {
            @Override
            public void success(List<getBottlesResponse> bottlesResponse, Response response)
            {
                Log.d(DEBUG_TAG, "SUCCESS : RESPOSNE : " + response.getStatus());
                bottles = bottlesResponse;
                if (rAdapter == null) {
                    rAdapter = new CatalogListAdapter(getActivity(), R.layout.list_adapter_catalog, bottles);
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
                    mListener.onBottleSelected(bottles.get(position));
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
                RestClient.getToken(token).getBottles(new Callback<List<getBottlesResponse>>() {
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
        Log.d("CatalogFragment", "onAttach");
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
        Log.d("CatalogFragment", "onDetach");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onBottleSelected(getBottlesResponse bottles);
    }
}
