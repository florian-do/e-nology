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
import com.enology.eip.e_nology.api.json.getBottlesResponse;

import org.w3c.dom.Text;

import java.io.Serializable;

public class RecipePageFragment extends Fragment {

    private static final String ARG_PARAM1 = "_bottles";

    private getBottlesResponse  bottles;

    public static RecipePageFragment newInstance(getBottlesResponse bottles) {
        RecipePageFragment fragment = new RecipePageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, bottles);
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
            bottles = (getBottlesResponse) getArguments().getSerializable(ARG_PARAM1);
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
        TextView name = (TextView) getView().findViewById(R.id.recipe_page_text);
        TextView desc = (TextView) getView().findViewById(R.id.recipe_page_desc);
        TextView city = (TextView) getView().findViewById(R.id.recipe_page_city);

        name.setText(bottles.getName());
        desc.setText(bottles.getDesc());
        city.setText(bottles.getDomain().getCity()+", "+bottles.getYear());
    }
}
