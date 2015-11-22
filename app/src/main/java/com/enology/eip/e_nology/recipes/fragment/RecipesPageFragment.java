package com.enology.eip.e_nology.recipes.fragment;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.SommelierResponse;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.api.json.getResearchResponse;
import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierBody;
import com.enology.eip.e_nology.api.json.object.Sommelier.SommelierBodyParam;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RecipesPageFragment extends Fragment {

    private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NjI2MWU5YzhiNDJmMTBiMDBjZGJiYWQiLCJzYWx0IjoiJDJhJDEwJGdnL0gvTVQvMGNOeUlWUXNIMnJZdXUiLCJkaXNwbGF5TmFtZSI6ImZsb3JpYW4gZG8iLCJwcm92aWRlciI6ImxvY2FsIiwicGFzc3dvcmQiOiIkMmEkMTAkZ2cvSC9NVC8wY055SVZRc0gycll1dWRKSkhLdFpDMEQ3ekJvcWNhb3haaE9scmNtQmFqdksiLCJ1c2VybmFtZSI6ImNoYXQiLCJfX3YiOjAsImNyZWF0ZWQiOiIyMDE1LTEwLTIwVDEwOjU5OjQwLjQzN1oiLCJyb2xlcyI6WyJhZG1pbiJdLCJlbWFpbCI6ImZsb3JpYW4uZG9AZXBpdGVjaC5ldSIsImxhc3ROYW1lIjoiZG8iLCJmaXJzdE5hbWUiOiJmbG9yaWFuIn0.xomNcLk3GwWAef7g9IxFCA7-zWPEgOFuTzlh0LtgLcE";
    private static final String ARG_PARAM1 = "_research";
    private static final String DEBUG_TAG = "RECIPES_FRAGMENT";

    private getResearchResponse recipe;
    private SommelierResponse sommelierResponse;
    private OnFragmentInteractionListener   mListener;


    private ImageView wine_1;
    private ImageView wine_2;
    private ImageView wine_3;
    private List<String> list;

    public static RecipesPageFragment newInstance(getResearchResponse recipe) {
        RecipesPageFragment fragment = new RecipesPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipesPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipe = (getResearchResponse)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recipes_fragment_recipes_page, container, false);
        SommelierBody   body = new SommelierBody("wine", true, new SommelierBodyParam(recipe.getId()));
        RestClient.getToken(TOKEN).getSommelier(body, new Callback<SommelierResponse>() {
            @Override
            public void success(SommelierResponse _sommelierResponse, Response response) {
                sommelierResponse = _sommelierResponse;
                setSommelier();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView name = (TextView) getView().findViewById(R.id.recipes_page_name);
        TextView desc = (TextView) getView().findViewById(R.id.recipes_page_desc);

        name.setText(recipe.getName());
        desc.setText(recipe.getDesc());
        Log.d(DEBUG_TAG, " id recettes : " + recipe.getId());
    }

    private void    setSommelier()
    {
        list = sommelierResponse.getBottles().getTmp();
        ArrayList<ImageView> img = new ArrayList<ImageView>();
        wine_1 = (ImageView) getView().findViewById(R.id.recipes_page_vin_1);
        wine_2 = (ImageView) getView().findViewById(R.id.recipes_page_vin_2);
        wine_3 = (ImageView) getView().findViewById(R.id.recipes_page_vin_3);

        img.add(0, wine_1);
        img.add(1, wine_2);
        img.add(2, wine_3);

        for (int i = 0; i < list.size(); i++)
        {
            Log.d(DEBUG_TAG, "ID : " + list.get(i));
            img.get(i).setVisibility(View.VISIBLE);
        }

        wine_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBottleById(list.get(0));
            }
        });

        wine_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBottleById(list.get(1));
            }
        });

        wine_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBottleById(list.get(2));
            }
        });
    }

    private void getBottleById(String id)
    {
        RestClient.getToken(TOKEN).getBottleById(id, new Callback<getBottleByIdResponse>() {
            @Override
            public void success(getBottleByIdResponse getBottleByIdResponse, Response response) {
                mListener.onWineSelected(getBottleByIdResponse);
            }

            @Override
            public void failure(RetrofitError error) {

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

    public interface OnFragmentInteractionListener {
        public void onWineSelected(getBottleByIdResponse bottle);
    }
}