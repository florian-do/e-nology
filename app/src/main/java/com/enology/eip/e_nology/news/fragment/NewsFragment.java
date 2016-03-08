package com.enology.eip.e_nology.news.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.api.json.getNewsResponse;
import com.enology.eip.e_nology.catalog.adapter.CatalogListAdapter;
import com.enology.eip.e_nology.news.adapter.NewsListAdapter;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewsFragment extends Fragment {

    private static final String ARG_TOKEN = "TOKEN";

    private List<getNewsResponse> news;

    private NewsListAdapter         rAdapter;
    private ListView                listView;
    private ImageView               recipes_ic_sync;
    private TextView                recipes_ic_text;
    private PtrClassicFrameLayout   mPtrFrame;

    public static final String DEBUG_TAG = "CatalogFragment";

    private OnFragmentInteractionListener mListener;
    private String token;
    private ProgressBar loading;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String token) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news_fragment_news, container, false);
        listView = (ListView) view.findViewById(R.id.recipes_list);
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
        //recipes_ic_sync.setVisibility(View.INVISIBLE);
        //gridView.setVisibility(View.INVISIBLE);

        RestClient.getToken(token).getNews(new Callback<List<getNewsResponse>>() {
            @Override
            public void success(List<getNewsResponse> newsResponses, Response response) {
                Log.d(DEBUG_TAG, "SUCCESS : RESPOSNE : " + response.getStatus());
                news = newsResponses;
                if (rAdapter == null) {
                    rAdapter = new NewsListAdapter(getActivity(), R.layout.list_adapter_news, news);
                }
                listView.setAdapter(rAdapter);
                loading.setVisibility(View.INVISIBLE);
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
                Log.d(DEBUG_TAG, news.get(position).getTitle());
                if (mListener != null) {
                    mListener.onNewsSelected(news.get(position));
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
                RestClient.getToken(token).getNews(new Callback<List<getNewsResponse>>() {
                    @Override
                    public void success(List<getNewsResponse> newsResponses, Response response) {
                        Log.d(DEBUG_TAG, "SUCCESS : RESPOSNE : " + response.getStatus());
                        news = newsResponses;
                        mPtrFrame.refreshComplete();
                        rAdapter.refreshAdapter(newsResponses);
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
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onNewsSelected(getNewsResponse news);
    }
}
