package com.enology.eip.e_nology.cave.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.enology.eip.e_nology.MainActivity;
import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.getCaveResponse;
import com.enology.eip.e_nology.cave.AddBottleActivity;
import com.enology.eip.e_nology.cave.adapter.CaveListAdapter;
import com.enology.eip.e_nology.cave.nfc.NfcActivity;
import com.enology.eip.e_nology.cave.scanner.ScannerFragment;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

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
 * {@link CaveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaveFragment extends Fragment {

    private static final String ARG_TOKEN = "token";
    private static final String ARG_CAVE = "cave";
    private static final String CAVE = "562794442b86df0b0020eaf7"; //"55eaf0eab79fae0b00eee97f";

    private List<getCaveResponse> cave;

    private OnFragmentInteractionListener   mListener;
    private CaveListAdapter                 rAdapter;
    private ListView                        listView;
    private ImageView                       recipes_ic_sync;
    private TextView                        recipes_ic_text;
    private PtrClassicFrameLayout           mPtrFrame;
    private String                          token;
    private ProgressBar                     loading;

    public static final String DEBUG_TAG = "CaveFragment";


    public static CaveFragment newInstance(String token) {
        CaveFragment fragment = new CaveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    public CaveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_TOKEN);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cave_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.cave_list);
        recipes_ic_sync = (ImageView) view.findViewById(R.id.recipes_ic_sync);
        recipes_ic_text= (TextView) view.findViewById(R.id.recipes_ic_text);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_frame);
        loading = (ProgressBar) view.findViewById(R.id.google_progress);
        loading.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(getActivity()).build());
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.setupPtr();
        loading.setVisibility(View.VISIBLE);
        getCave();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(DEBUG_TAG, cave.get(position).getName());
                if (mListener != null) {
                    mListener.onCaveSelected(cave.get(position));
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
                RestClient.getToken(token).getCave(CAVE, new Callback<List<getCaveResponse>>() {
                    @Override
                    public void success(List<getCaveResponse> caveResponse, Response response)
                    {
                        Log.d(DEBUG_TAG, "[MPTR]SUCCESS : RESPOSNE : " + response.getStatus() + " Size : "+caveResponse.size());
                        cave = caveResponse;
                        rAdapter.refreshAdapter(caveResponse);
                        mPtrFrame.refreshComplete();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        recipes_ic_sync.setVisibility(View.VISIBLE);
                        recipes_ic_text.setVisibility(View.VISIBLE);
                        Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
                        mPtrFrame.refreshComplete();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cave, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        FragmentManager fragmentManager = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent t = new Intent(getActivity(), AddBottleActivity.class);
                t.putExtra(ARG_TOKEN, token);
                t.putExtra(ARG_CAVE, CAVE);
                startActivity(t);
                return true;
            case R.id.menu_add_scanner:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ScannerFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.menu_add_nfc:
                Intent i = new Intent(getActivity(), NfcActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void    getCave()
    {
        RestClient.getToken(token).getCave(CAVE, new Callback<List<getCaveResponse>>() {
            @Override
            public void success(List<getCaveResponse> caveResponse, Response response) {
                Log.d(DEBUG_TAG, "[getCave] SUCCESS : RESPONSE : " + response.getStatus() + " Size : "+caveResponse.size());
                cave = caveResponse;
                if (rAdapter == null) {
                    rAdapter = new CaveListAdapter(getActivity(), R.layout.list_adapter_cave, cave);
                }
                if (listView.getAdapter() == null)
                {
                    listView.setAdapter(rAdapter);
                    Log.d(DEBUG_TAG, "INIT LIST");
                }
                else
                {
                    rAdapter.refreshAdapter(caveResponse);
                }
                loading.setVisibility(View.INVISIBLE);

            }

            @Override
            public void failure(RetrofitError error) {
                recipes_ic_sync.setVisibility(View.VISIBLE);
                recipes_ic_text.setVisibility(View.VISIBLE);
                Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
            }
        });
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

    @Override
    public void onResume() {
        Log.e(DEBUG_TAG, "onResume of CaveFragment");
        getCave();
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(DEBUG_TAG, "OnPause of CaveFragment");
        super.onPause();
    }

    public interface OnFragmentInteractionListener {
        public void onCaveSelected(getCaveResponse cave);
    }
}
