package com.enology.eip.e_nology.catalog.adapter;

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

import java.util.List;
import java.util.Random;

/**
 * Created by Lolo on 27/02/2015.
 */
public class CatalogListAdapter extends ArrayAdapter<getBottlesResponse>
{
    private static final String TAG = "CatalogListAdapter";
    private final Random mRandom;

    private Context context;
    private List<getBottlesResponse> objects;
    private int resource;


    static class ViewHolder {
        TextView    name;
        TextView    domain;
        TextView    year;
        TextView    price;
        ImageView   img;
    }

    public CatalogListAdapter(Context context, int resource, List<getBottlesResponse> objects)
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
        LayoutInflater  mInflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder      holder;

        if (convertView == null)
        {
            convertView = mInflate.inflate(this.resource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.recipes_list_name);
            holder.domain = (TextView) convertView.findViewById(R.id.recipes_list_domain);
            holder.img = (ImageView) convertView.findViewById(R.id.recipes_list_image);
            holder.year = (TextView) convertView.findViewById(R.id.recipes_list_year);
            holder.price = (TextView) convertView.findViewById(R.id.catalog_list_price);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        Drawable tmp = this.context.getResources().getDrawable(getRandom());
        holder.img.setImageDrawable(tmp);
        holder.name.setText(objects.get(position).getName());
        if (objects.get(position).getDomain() != null)
            holder.domain.setText(objects.get(position).getDomain().getCity());
        holder.year.setText(objects.get(position).getYear());
        holder.price.setText(objects.get(position).getPrice()+" "+context.getString(R.string.euro));
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

    public synchronized void refreshAdapter(List<getBottlesResponse> items) {
        objects.clear();
        objects.addAll(items);
        notifyDataSetChanged();
    }
}