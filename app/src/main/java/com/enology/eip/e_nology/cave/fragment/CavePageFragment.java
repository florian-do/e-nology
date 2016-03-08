package com.enology.eip.e_nology.cave.fragment;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.RestClient;
import com.enology.eip.e_nology.api.json.deleteBottleResponse;
import com.enology.eip.e_nology.api.json.getCaveResponse;
import com.enology.eip.e_nology.catalog.fragment.CatalogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CavePageFragment extends Fragment
{
    private static final String ARG_PARAM1 = "_cave";
    private static final String ARG_TOKEN = "_token";
    private static final String CAVE = "562794442b86df0b0020eaf7"; //"55eaf0eab79fae0b00eee97f";

    private getCaveResponse     cave;
    private String              token;

    private TextView            name;
    private TextView            desc;
    private TextView            city;
    private List<ImageView>     mark = new ArrayList<ImageView>();
    private TextView            price;
    private Button              button;

    public static CavePageFragment newInstance(getCaveResponse cave, String token) {
        CavePageFragment fragment = new CavePageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, cave);
        args.putString(ARG_TOKEN, token);
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
            token = getArguments().getString(ARG_TOKEN);
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
        button = (Button) view.findViewById(R.id.cave_page_drink_button);

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
        city.setText(cave.getCru() + ", " + cave.getYear());
        price.setText(cave.getPrice() + " " + getString(R.string.euro));

        for (int i = 0; i < cave.getGrade(); i++)
        {
            mark.get(i).setImageResource(R.drawable.recipes_mark_on);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.getToken(token).deleteBottle(CAVE, cave.getId(), new Callback<deleteBottleResponse>() {
                    @Override
                    public void success(deleteBottleResponse deleteBottleResponse, Response response) {
                        Toast.makeText(getActivity(), "Bouteille bu !", Toast.LENGTH_SHORT).show();
                        closeFragment();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });

    }

    public void closeFragment()
    {
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.container, CaveFragment.newInstance(token))
                .addToBackStack(null)
                .commit();
    }
}
