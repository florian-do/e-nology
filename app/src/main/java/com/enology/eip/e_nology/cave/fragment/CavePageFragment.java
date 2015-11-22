package com.enology.eip.e_nology.cave.fragment;

import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getCaveResponse;

import java.util.ArrayList;
import java.util.List;

public class CavePageFragment extends Fragment
{
    private static final String ARG_PARAM1 = "_cave";

    private getCaveResponse     cave;
    private TextView            name;
    private TextView            desc;
    private TextView            city;
    private List<ImageView>     mark = new ArrayList<ImageView>();
    private TextView            price;

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
        View view = inflater.inflate(R.layout.cave_fragment_cave_page, container, false);
        name = (TextView) view.findViewById(R.id.cave_page_text);
        desc = (TextView) view.findViewById(R.id.cave_page_desc);
        city = (TextView) view.findViewById(R.id.cave_page_city);
        price = (TextView) view.findViewById(R.id.cave_page_price);

        mark.add((ImageView) view.findViewById(R.id.cave_page_mark_1));
        mark.add((ImageView) view.findViewById(R.id.cave_page_mark_2));
        mark.add((ImageView) view.findViewById(R.id.cave_page_mark_3));
        mark.add((ImageView) view.findViewById(R.id.cave_page_mark_4));
        mark.add((ImageView) view.findViewById(R.id.cave_page_mark_5));
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name.setText(cave.getName());
        desc.setText(cave.getDesc());
        city.setText(cave.getCru()+", "+cave.getYear());
        price.setText(cave.getPrice()+" "+getString(R.string.euro));

        for (int i = 0; i < cave.getGrade(); i++)
        {
            mark.get(i).setImageResource(R.drawable.recipes_mark_on);
        }

    }

}
