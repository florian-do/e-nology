package com.enology.eip.e_nology.catalog.page.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;

public class DescriptionFragment extends Fragment {

    private static final String ARG_BOTTLE = "bottle";

        private getBottleByIdResponse bottle;


    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance(getBottleByIdResponse bottle) {
        DescriptionFragment fragment = new DescriptionFragment();
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
        View view = inflater.inflate(R.layout.catalog_fragment_catalog_page_description, container, false);
        return view;
    }
}
