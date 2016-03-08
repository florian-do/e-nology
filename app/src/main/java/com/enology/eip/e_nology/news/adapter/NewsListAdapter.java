package com.enology.eip.e_nology.news.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.api.json.getNewsResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Random;

/**
 * Created by do_f on 19/01/16.
 */
public class NewsListAdapter extends ArrayAdapter<getNewsResponse>
{

    private static final String TAG = "NewsListAdapter";
    private final Random mRandom;

    private Context context;
    private List<getNewsResponse> objects;
    private int resource;


    static class ViewHolder {
        TextView            title;
        TextView            author;
        TextView            date;
        SimpleDraweeView    img;
    }

    public NewsListAdapter(Context context, int resource, List<getNewsResponse> objects)
    {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.objects = objects;
        this.mRandom = new Random();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater mInflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder      holder;

        if (convertView == null)
        {
            convertView = mInflate.inflate(this.resource, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.news_name);
            holder.author = (TextView) convertView.findViewById(R.id.news_author);
            holder.img = (SimpleDraweeView) convertView.findViewById(R.id.recipes_list_image);
            holder.date = (TextView) convertView.findViewById(R.id.news_date);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();


        Uri uri = Uri.parse(objects.get(position).getImgurl());
        //Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
        holder.img.setImageURI(uri);
        holder.title.setText(objects.get(position).getTitle());
        holder.author.setText("E-nology");
        holder.date.setText(objects.get(position).getCreated().split("T", 2)[0]);
        return convertView;
    }

    public synchronized void refreshAdapter(List<getNewsResponse> items) {
        objects.clear();
        objects.addAll(items);
        notifyDataSetChanged();
    }

}
