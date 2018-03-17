package com.teucontrole.teucontrole.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teucontrole.teucontrole.Models.Item;
import com.teucontrole.teucontrole.R;

import java.util.List;

public class AdapterMenuItems extends BaseAdapter
{

    List<Item> items;
    Activity context;

    public AdapterMenuItems(List<Item> _items, Activity _context)
    {
        this.items = _items;
        this.context = _context;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Item getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        try
        {
            View view = convertView;
            Item item = items.get(position);

            if(convertView == null)
            {
                view = context.getLayoutInflater().inflate(R.layout.row_menu_item, null);
            }

            ImageView image_item = view.findViewById(R.id.image_item);
            image_item.setImageResource(item.getImage());

            TextView text_item = view.findViewById(R.id.text_item);
            text_item.setText(item.getDescricao());

            return view;

        }
        catch (Exception e )
        {
            return null;
        }

    }
}
