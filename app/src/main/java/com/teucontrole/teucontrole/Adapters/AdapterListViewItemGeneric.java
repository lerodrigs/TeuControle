package com.teucontrole.teucontrole.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdapterListViewItemGeneric extends BaseAdapter
{
    private JSONArray items;
    private Activity context;

    public AdapterListViewItemGeneric(Activity _context, JSONArray _items)
    {
        this.context = _context;
        this.items = _items;
    }

    @Override
    public int getCount()
    {
        return items.length();
    }

    @Override
    public JSONObject getItem(int position)
    {
        try{
            return items.getJSONObject(position);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

    public void updateListView(JSONArray _items)
    {
        this.items = _items;
        this.notifyDataSetChanged();
    }
}
