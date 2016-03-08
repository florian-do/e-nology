package com.enology.eip.e_nology.catalog.page.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.catalog.page.adapter.CommentaireAdapter;

import java.util.List;

public class CommentaireFragment extends Fragment {

    private static final String ARG_BOTTLE = "bottle";
    private static final String DEBUG_TAG = "CommentaireFragment";

    private getBottleByIdResponse bottle;
    private List<getBottleByIdResponse.Comment> comments;

    private ListView            listView;
    private CommentaireAdapter  rAdapter;

    public CommentaireFragment() {
        // Required empty public constructor
    }

    public static CommentaireFragment newInstance(getBottleByIdResponse bottle) {
        CommentaireFragment fragment = new CommentaireFragment();
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
        View view = inflater.inflate(R.layout.catalog_fragment_catalog_page_commentaire, container, false);
        listView = (ListView) view.findViewById(R.id.comments_list);
        return view;
    }

    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comments = bottle.getComments();
        rAdapter = new CommentaireAdapter(getActivity(), R.layout.list_adapter_comments, comments);
        listView.setAdapter(rAdapter);
    }
}
