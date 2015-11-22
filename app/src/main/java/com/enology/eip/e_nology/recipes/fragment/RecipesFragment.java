package com.enology.eip.e_nology.recipes.fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.getResearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RecipesFragment extends Fragment {

    private static final String ARG_TOKEN = "token";
    public static final String DEBUG_TAG = "RecipesFragment";

    private OnFragmentInteractionListener   mListener;
    private List<getResearchResponse>       getResearchResponses;
    private List<getResearchResponse>       research;
    private ArrayAdapter<String>            adapter;
    private ArrayList<String>               simpleList;
    private EditText                        recipesSearch;
    private ListView                        recipesList;
    private ImageView                       arrowImage;
    private TextView                        arrowText;
    private String                          token;

    public static RecipesFragment newInstance(String token) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            token = getArguments().getString(ARG_TOKEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recipes_fragment, container, false);
        recipesList = (ListView) view.findViewById(R.id.recettes_list);
        recipesSearch = (EditText) view.findViewById(R.id.recipes_search);
        arrowImage = (ImageView) view.findViewById(R.id.recipes_arrow_r);
        arrowText = (TextView) view.findViewById(R.id.recipes_hint_text);
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        simpleList = new ArrayList<String>();
        if (research != null)
        {
            fillList();
            arrowImage.setVisibility(View.INVISIBLE);
            arrowText.setVisibility(View.INVISIBLE);
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, simpleList);
        recipesList.setAdapter(adapter);
        recipesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                if (mListener != null) {
                    mListener.onRecipesSelected(research.get(position));
                }
            }

        });

        recipesSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    RestClient.getToken(token).getResearch(recipesSearch.getText().toString(), new Callback<List<getResearchResponse>>() {
                        @Override
                        public void success(List<getResearchResponse> getResearchResponses, Response response) {
                            research = getResearchResponses;
                            simpleList.clear();
                            fillList();
                            adapter.notifyDataSetChanged();
                            if (getResearchResponses.size() > 0) {
                                arrowImage.setVisibility(View.INVISIBLE);
                                arrowText.setVisibility(View.INVISIBLE);
                            }
                            else
                                Toast.makeText(getActivity(), "No result found", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d(DEBUG_TAG, "FAIL : " + error.getMessage());
                        }
                    });
                    return true;
                }
                return false;
            }
        });

    }

    public void fillList()
    {
        for (getResearchResponse tmp : research)
        {
            simpleList.add(tmp.getName());
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
        public void onRecipesSelected(getResearchResponse research);
    }

}
