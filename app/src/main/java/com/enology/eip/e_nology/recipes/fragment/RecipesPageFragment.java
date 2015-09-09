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
import com.enology.eip.e_nology.api.json.getResearchResponse;

import org.w3c.dom.Text;

public class RecipesPageFragment extends Fragment {

    private static final String ARG_PARAM1 = "_research";

    private getResearchResponse research;

    public static RecipesPageFragment newInstance(getResearchResponse research) {
        RecipesPageFragment fragment = new RecipesPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, research);
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
            research = (getResearchResponse)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipes_fragment_recipes_page, container, false);
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView name = (TextView) getView().findViewById(R.id.recipes_page_name);
        TextView desc = (TextView) getView().findViewById(R.id.recipes_page_desc);

        name.setText(research.getName());
        desc.setText(research.getDesc());

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
