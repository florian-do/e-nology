package com.enology.eip.e_nology.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;
import com.enology.eip.e_nology.menu.MenuObject;

import java.util.List;

/**
 * Created by Lolo on 27/02/2015.
 */
public class RecipesListAdapter extends ArrayAdapter<RecipeObject>
{
    private Context context;
    private List<RecipeObject> objects;
    private int resource;

    public RecipesListAdapter(Context context, int resource, List<RecipeObject> objects)
    {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater mInflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rawView = mInflate.inflate(this.resource, parent, false);

        return rawView;
    }
}