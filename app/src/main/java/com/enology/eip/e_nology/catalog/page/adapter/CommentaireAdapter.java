package com.enology.eip.e_nology.catalog.page.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottleByIdResponse;
import com.enology.eip.e_nology.api.json.getNewsResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Random;

/**
 * Created by do_f on 21/01/16.
 */
public class CommentaireAdapter extends ArrayAdapter<getBottleByIdResponse.Comment>
{

    private static final String TAG = "NewsListAdapter";
    private final Random mRandom;

    private Context context;
    private List<getBottleByIdResponse.Comment> objects;
    private int resource;


    static class ViewHolder {
        TextView            title;
        TextView            body;
        TextView            date;
    }

    public CommentaireAdapter(Context context, int resource, List<getBottleByIdResponse.Comment> objects)
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
            holder.title = (TextView) convertView.findViewById(R.id.comment_title);
            holder.body = (TextView) convertView.findViewById(R.id.comment_body);
            holder.date = (TextView) convertView.findViewById(R.id.comment_date);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();


        holder.title.setText(objects.get(position).getTitle());
        holder.body.setText(objects.get(position).getBody());
        holder.date.setText(objects.get(position).getCreated().split("T", 2)[0]);
        return convertView;
    }
}