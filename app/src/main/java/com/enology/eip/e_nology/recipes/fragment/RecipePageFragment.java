package com.enology.eip.e_nology.recipes.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enology.eip.e_nology.R;

public class RecipePageFragment extends Fragment {

    private static final String ARG_PARAM1 = "_id";

    private String _id;
    private String _name;

    public static RecipePageFragment newInstance(String _id, String name) {
        RecipePageFragment fragment = new RecipePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, _id);
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _id = getArguments().getString(ARG_PARAM1);
            _name = getArguments().getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipes_fragment_recipe_page, container, false);
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.recipe_page_text);
        textView.setText(_name);
        TextView desc = (TextView) getView().findViewById(R.id.recipe_page_desc);
        desc.setText(_id);
    }


}
