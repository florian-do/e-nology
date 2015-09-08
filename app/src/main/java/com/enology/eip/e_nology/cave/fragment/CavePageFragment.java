package com.enology.eip.e_nology.cave.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getCaveResponse;

public class CavePageFragment extends Fragment
{
    private static final String ARG_PARAM1 = "_cave";

    private getCaveResponse     cave;

    public static CavePageFragment newInstance(getCaveResponse cave) {
        CavePageFragment fragment = new CavePageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, cave);
        fragment.setArguments(args);
        return fragment;
    }

    public CavePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cave = (getCaveResponse) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cave_fragment_cave_page, container, false);
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView name = (TextView) getView().findViewById(R.id.cave_page_text);
        TextView desc = (TextView) getView().findViewById(R.id.cave_page_desc);
        TextView city = (TextView) getView().findViewById(R.id.cave_page_city);

        name.setText(cave.getName());
        desc.setText(cave.getDesc());
        city.setText(cave.getCru()+", "+cave.getYear());
    }

}
