package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.teucontrole.teucontrole.Actitivies.MainActivity;
import com.teucontrole.teucontrole.Adapters.AdapterListViewMenuPerfil;
import com.teucontrole.teucontrole.Controllers.PerfilController;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoadPerfisTask extends AsyncTask<String, Void, JSONArray>
{
    private JSONArray perfis;
    private PerfilController perfilController;
    private Activity context;
    private ListView listView;
    private AdapterListViewMenuPerfil adapter;

    public LoadPerfisTask(Activity _context, ListView _listView, AdapterListViewMenuPerfil _adapter)
    {
        this.context = _context;
        this.perfilController = new PerfilController(context);
        this.listView = _listView;
        this.adapter = _adapter;
    }

    @Override
    protected void onPreExecute()
    {
        try
        {

        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    protected JSONArray doInBackground(String... strings)
    {
        try
        {
            perfis = perfilController.getPerfis();
        }
        catch(Exception e)
        {
            throw e;
        }

        return perfis;
    }

    @Override
    protected void onPostExecute(JSONArray jArray)
    {
        try
        {
            this.perfis = jArray;
            if(perfis != null && perfis.length() > 0)
            {
                if(adapter == null){
                    adapter = new AdapterListViewMenuPerfil(perfis, context);
                    listView.setAdapter(adapter);
                }
            }
        }
        catch(Exception e){
            throw e;
        }
    }

    public AdapterListViewMenuPerfil getAdapter()
    {
        return adapter;
    }
}
