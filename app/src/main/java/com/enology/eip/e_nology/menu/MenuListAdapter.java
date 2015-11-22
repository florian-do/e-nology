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

    static class    ViewHolder
    {
        TextView    label;
        ImageView   image;
        TextView    notification;
        ImageView   notification_bg;
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
            holder.label = (TextView)convertView.findViewById(R.id.menu_menu_categorie);
            holder.image = (ImageView)convertView.findViewById(R.id.menu_menu_icon);
            holder.notification = (TextView) convertView.findViewById(R.id.menu_background_notifications_nb);
            holder.notification_bg = (ImageView)convertView.findViewById(R.id.menu_background_notifications);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        MenuObject object = (MenuObject)objects.get(position);
        holder.label.setText(object.getText());
        holder.image.setImageDrawable(context.getResources().getDrawable(object.getImage()));
        if (object.getNumber() != null)
            holder.notification.setText(object.getNumber());
        else
        {
            holder.notification.setText("");
            holder.notification_bg.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
}