package com.enology.eip.e_nology.cave.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.api.json.getCaveResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lolo on 08/09/2015.
 */
public class CaveListAdapter extends ArrayAdapter<getCaveResponse>
{
    private static final String TAG = "CaveListAdapter";
    private final Random mRandom;

    private Context context;
    private List<getCaveResponse> objects;
    private int resource;


    static class ViewHolder {
        TextView    name;
        TextView    domain;
        TextView    year;
        ImageView   img;
        List<ImageView>     mark = new ArrayList<ImageView>();
    }

    public CaveListAdapter(Context context, int resource, List<getCaveResponse> objects)
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
            holder.name = (TextView) convertView.findViewById(R.id.cave_list_name);
            holder.domain = (TextView) convertView.findViewById(R.id.cave_list_domain);
            holder.img = (ImageView) convertView.findViewById(R.id.cave_list_image);
            holder.year = (TextView) convertView.findViewById(R.id.cave_list_year);
            holder.mark.add((ImageView) convertView.findViewById(R.id.cave_adapter_mark_1));
            holder.mark.add((ImageView) convertView.findViewById(R.id.cave_adapter_mark_2));
            holder.mark.add((ImageView) convertView.findViewById(R.id.cave_adapter_mark_3));
            holder.mark.add((ImageView) convertView.findViewById(R.id.cave_adapter_mark_4));
            holder.mark.add((ImageView) convertView.findViewById(R.id.cave_adapter_mark_5));
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        Drawable tmp = this.context.getResources().getDrawable(getRandom());
        holder.img.setImageDrawable(tmp);
        holder.name.setText(objects.get(position).getName());
        holder.domain.setText(objects.get(position).getCru());
        holder.year.setText(objects.get(position).getYear());

        for (int i = 0; i < objects.get(position).getGrade(); i++)
        {
            holder.mark.get(i).setImageResource(R.drawable.recipes_mark_on);
        }

        return convertView;
    }

    private int getRandom() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(6);
        switch (randomInt)
        {
            case 0:
                return R.drawable.wine_1;
            case 1:
                return R.drawable.wine_4;
            case 2:
                return R.drawable.wine_4;
            case 3:
                return R.drawable.wine_4;
            case 4:
                return R.drawable.wine_5;
            case 5:
                return R.drawable.wine_6;
            default:
                return R.drawable.wine_1;
        }
    }
}
