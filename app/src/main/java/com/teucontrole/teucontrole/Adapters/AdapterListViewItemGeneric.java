package com.teucontrole.teucontrole.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdapterListViewItemGeneric extends BaseAdapter
{
    private JSONArray items;
    private Activity context;

    private String prName;
    private String prId;

    public AdapterListViewItemGeneric(Activity _context, JSONArray _items, String _prName, String _prId)
    {
        this.context = _context;
        this.items = _items;
        this.prId = _prId;
        this.prName = _prName;
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
        View view = convertView;

        try
        {
            JSONObject categoria = items.getJSONObject(position);

            if(view == null)
                view = context.getLayoutInflater().inflate(R.layout.dialog_item_row, null);

            TextView name = view.findViewById(R.id.name);
            TextView id = view.findViewById(R.id.id);

            if(categoria.getString(prName) != null)
                name.setText(categoria.getString(prName));

            if(categoria.getString(prId) != null)
                id.setText(categoria.getString(prId));
        }
        catch(Exception e ){

        }

        return view;

    }

    public void updateListView(JSONArray _items)
    {
        this.items = _items;
        this.notifyDataSetChanged();
    }
}
