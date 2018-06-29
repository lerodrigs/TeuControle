package com.teucontrole.teucontrole.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AdapterListViewMenuPerfil extends BaseAdapter
{
    JSONArray items;
    Activity context;

    public AdapterListViewMenuPerfil(JSONArray _items, Activity _context)
    {
        this.items = _items;
        this.context = _context;
    }

    @Override
    public int getCount()
    {
        return items.length();
    }

    @Override
    public JSONObject getItem(int position)
    {
        JSONObject item = null;

        try
        {
            item = items.getJSONObject(position);
        }
        catch (JSONException e){

        }

        return item;
    }

    @Override
    public long getItemId(int position)
    {
        long id = 0;

        try
        {
            id = items.getLong(position);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        try
        {
            View view = convertView;
            JSONObject item = items.getJSONObject(position);

            if(convertView == null)
            {
                view = context.getLayoutInflater().inflate(R.layout.perfil_row, null);
            }

            TextView text_item = view.findViewById(R.id.nome_perfil);

            if(Utils.getValueJObject(item, "nome") !=null)
                text_item.setText(item.getString("nome"));

            return view;
        }
        catch (Exception e )
        {
            return null;
        }

    }
}
