package com.enology.eip.e_nology.catalog.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottlesResponse;

public class CatalogPageFragment extends Fragment {

    private static final String ARG_PARAM1 = "_bottles";

    private getBottlesResponse  bottles;

    public static CatalogPageFragment newInstance(getBottlesResponse bottles) {
        CatalogPageFragment fragment = new CatalogPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, bottles);
        fragment.setArguments(args);
        return fragment;
    }

    public CatalogPageFragment() {
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
        return inflater.inflate(R.layout.catalog_fragment_catalog_page, container, false);
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
