package com.enology.eip.e_nology.catalog.page.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;

public class GeneralFragment extends Fragment {

    private static final String ARG_BOTTLE = "bottle";

    private getBottleByIdResponse bottle;


    public GeneralFragment() {
        // Required empty public constructor
    }

    public static GeneralFragment newInstance(getBottleByIdResponse bottle) {
        GeneralFragment fragment = new GeneralFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOTTLE, bottle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bottle = (getBottleByIdResponse) getArguments().getSerializable(ARG_BOTTLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.catalog_fragment_catalog_page_general, container, false);
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView name = (TextView) getView().findViewById(R.id.recipe_page_text);
        TextView desc = (TextView) getView().findViewById(R.id.recipe_page_desc);
        TextView city = (TextView) getView().findViewById(R.id.recipe_page_city);

        String _city = (bottle.getDomain() != null) ? bottle.getDomain().getCity() : "";

        name.setText(bottle.getName());
        desc.setText(bottle.getDesc());
        city.setText(_city+", "+bottle.getYear());
    }
}
