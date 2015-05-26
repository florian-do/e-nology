package com.enology.eip.e_nology.recipes.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.api.json.getBottlesResponse;
import com.enology.eip.e_nology.recipes.object.RecipeObject;

import java.util.List;
import java.util.Random;

/**
 * Created by Lolo on 27/02/2015.
 */
public class RecipesListAdapter extends ArrayAdapter<getBottlesResponse>
{
    private static final String TAG = "RecipesListAdapter";
    private final Random mRandom;

    private Context context;
    private List<getBottlesResponse> objects;
    private int resource;


    static class ViewHolder {
        TextView    txt;
        ImageView   img;
    }

    public RecipesListAdapter(Context context, int resource, List<getBottlesResponse> objects)
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
            holder.txt = (TextView) convertView.findViewById(R.id.recipes_grid_text);
            holder.img = (ImageView) convertView.findViewById(R.id.recipes_grid_image);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        Drawable tmp = this.context.getResources().getDrawable(getRandom());
        holder.img.setImageDrawable(tmp);
        holder.txt.setText(objects.get(position).getName());
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