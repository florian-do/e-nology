package com.enology.eip.e_nology.news.fragment;


import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getNewsResponse;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPageFragment extends Fragment {

    private static final String ARG_NEWS = "NEWS";

    private SimpleDraweeView    img;
    private TextView            title;
    private TextView            date;
    private TextView            content;
    private getNewsResponse     news;

    public NewsPageFragment() {
        // Required empty public constructor
    }

    public static NewsPageFragment newInstance(getNewsResponse news) {
        NewsPageFragment fragment = new NewsPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = (getNewsResponse)getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news_fragment_news_page, container, false);
        img = (SimpleDraweeView) view.findViewById(R.id.news_page_image);
        title = (TextView) view.findViewById(R.id.news_page_title);
        content = (TextView) view.findViewById(R.id.news_page_content);

        Uri uri = Uri.parse(news.getImgurl());
        img.setImageURI(uri);
        title.setText(news.getTitle());
        content.setText(Html.fromHtml(news.getBody()));
        return view;
    }

}
