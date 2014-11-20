package com.enology.eip.e_nology.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enology.eip.e_nology.R;

import java.util.List;

/**
 * Created by Lolo on 09/11/2014.
 */
public class MenuListAdapter extends ArrayAdapter<MenuObject>
{
    private Context context;
    private List<MenuObject> objects;
    private int resource;

    public MenuListAdapter(Context context, int resource, List<MenuObject> objects)
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
        MenuObject object = (MenuObject)objects.get(position);
        TextView label = (TextView)rawView.findViewById(R.id.menu_menu_categorie);
        label.setText(object.getText());
        ImageView image = (ImageView)rawView.findViewById(R.id.menu_menu_icon);
        image.setImageDrawable(context.getResources().getDrawable(object.getImage()));
        TextView notification = (TextView)rawView.findViewById(R.id.menu_background_notifications_nb);
        ImageView notification_bg = (ImageView)rawView.findViewById(R.id.menu_background_notifications);
        if (object.getNumber() != null)
            notification.setText(object.getNumber());
        else
        {
            notification.setText("");
            notification_bg.setVisibility(View.INVISIBLE);
        }
        return rawView;
    }
}