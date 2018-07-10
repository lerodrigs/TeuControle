package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoadSituacaoDialogTask extends AsyncTask<String, Void, JSONArray>
{
    View view;
    Activity context;
    JSONArray items;
    JSONObject itemSelecionado;


    public LoadSituacaoDialogTask(View _view, Activity _context, JSONArray _items)
    {
        this.view = view;
        this.context = _context;
        this.items = _items;
    }

    @Override
    protected JSONArray doInBackground(String... strings)
    {
        try
        {

        }
        catch (Exception e){

        }

        return items;
    }

    public JSONObject getCategoriaSelecionada()
    {
        return this.itemSelecionado;
    }
}
