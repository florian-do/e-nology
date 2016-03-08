package com.enology.eip.e_nology.stats;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.getStatsResponse;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StatsFragment extends Fragment {
    private static final String ARG_TOKEN = "TOKEN";
    private static final String DEBUG_TAG = "StatsFragment";
    private static final String CAVE = "562794442b86df0b0020eaf7"; //"55eaf0eab79fae0b00eee97f";

    private String token;
    private getStatsResponse stats;

    private LinearLayout    content;

    private TextView    number_bottles;
    private TextView    number_bottles_open;
    private TextView    price_total_cave;
    private TextView    average_bottle_price;

    private TextView    favorite_wine;
    private TextView    nb_bottles_rose;
    private TextView    nb_bottles_red;
    private TextView    nb_bottles_white;
    private TextView    past_most_expensive_bottle;
    private TextView    past_oldest_bottle;
    private TextView    current_most_expensive_bottle;
    private TextView    current_oldest_bottle;


    private ProgressBar loading;

    public StatsFragment() {
        // Required empty public constructor
    }

    public static StatsFragment newInstance(String token) {
        StatsFragment fragment = new StatsFragment();
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
        View view = inflater.inflate(R.layout.stats_fragment_stats, container, false);
        content = (LinearLayout) view.findViewById(R.id.stats_content);
        number_bottles = (TextView) view.findViewById(R.id.stats_nb_bottle);
        number_bottles_open = (TextView) view.findViewById(R.id.stats_nb_bottles_open);
        price_total_cave = (TextView) view.findViewById(R.id.stats_price_total_cave);
        average_bottle_price = (TextView) view.findViewById(R.id.stats_average_bottle_price);

        favorite_wine = (TextView) view.findViewById(R.id.stats_favorite_wine);
        nb_bottles_rose = (TextView) view.findViewById(R.id.stats_nb_bottles_rose);
        nb_bottles_red = (TextView) view.findViewById(R.id.stats_nb_bottles_red);
        nb_bottles_white = (TextView) view.findViewById(R.id.stats_nb_bottles_white);
        past_most_expensive_bottle = (TextView) view.findViewById(R.id.stats_past_most_expensive_bottle);
        past_oldest_bottle = (TextView) view.findViewById(R.id.stats_past_oldest_bottle);
        current_most_expensive_bottle = (TextView) view.findViewById(R.id.stats_current_most_expensive_bottle);
        current_oldest_bottle = (TextView) view.findViewById(R.id.stats_current_oldest_bottle);

        loading = (ProgressBar) view.findViewById(R.id.google_progress);
        loading.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(getActivity()).build());
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loading.setVisibility(View.VISIBLE);

        RestClient.getToken(token).getStats(CAVE, new Callback<getStatsResponse>() {
            @Override
            public void success(getStatsResponse getStatsResponse, Response response) {
                Log.d(DEBUG_TAG, getStatsResponse.getPreferred_type());
                stats = getStatsResponse;
                setStats();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
            }
        });
    }

    private void setStats()
    {
        number_bottles.setText(stats.getNbr_bottle());
        number_bottles_open.setText(stats.getNbr_bottle_open());
        price_total_cave.setText(stats.getCave_price());
        average_bottle_price.setText(stats.getBottle_price_average());

        favorite_wine.setText(stats.getPreferred_type());
        nb_bottles_rose.setText(stats.getNbr_type_rose());
        nb_bottles_red.setText(stats.getNbr_type_red());
        nb_bottles_white.setText(stats.getNbr_type_white());
        past_most_expensive_bottle.setText(stats.getPast_expensive().getName());
        past_oldest_bottle.setText(stats.getPast_oldest().getName());
        current_most_expensive_bottle.setText(stats.getCurrent_expensive().getName());
        current_oldest_bottle.setText(stats.getCurrent_oldest().getName());

        loading.setVisibility(View.INVISIBLE);
        content.setVisibility(View.VISIBLE);
    }

}
