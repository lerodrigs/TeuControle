package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teucontrole.teucontrole.Adapters.AdapterListViewItemGeneric;
import com.teucontrole.teucontrole.Controllers.CategoriaController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoadCategoriaDialogTask extends AsyncTask <String, Void, JSONArray>
{
    private JSONArray items;
    private JSONObject categoria;
    private boolean isReceita;

    private View view;
    private Activity context;

    private CategoriaController categoriaController;
    private String id_perfil;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertBuilder;

    private ListView listView;
    private AdapterListViewItemGeneric adapter;
    private TextView txtCategoria;

    public LoadCategoriaDialogTask(Activity _context, TextView _txtCategoria, View _view, boolean _isReceita, String _id_perfil)
    {
        this.context = _context;
        this.view = _view;
        this.isReceita =_isReceita;
        this.id_perfil = _id_perfil;
        this.txtCategoria = _txtCategoria;
        this.categoriaController = new CategoriaController(context, isReceita);

        this.alertBuilder = new AlertDialog.Builder(context);
    }

    @Override
    protected void onPreExecute()
    {
        try
        {
            alertBuilder.setTitle("Categorias");
            alertBuilder.setView(view);

            alertDialog = alertBuilder.create();
            alertDialog.show();
        }
        catch (Exception e){

        }
    }

    @Override
    protected JSONArray doInBackground(String... strings)
    {
        try
        {
            this.items = categoriaController.getCategorias(id_perfil);
        }
        catch (Exception e){ }

        return items;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray)
    {
        try
        {
            listView = view.findViewById(R.id.listview);
            listView.setOnItemClickListener(clickListView);

            TextView semRegistro = view.findViewById(R.id.sem_registros);
            ProgressBar progressBar = view.findViewById(R.id.progressBar1);

            if(adapter == null)
            {
                adapter = new AdapterListViewItemGeneric(context, jsonArray);
                listView.setAdapter(adapter);
            }

            adapter.updateListView(jsonArray);

            progressBar.setVisibility(View.GONE);

            if(jsonArray != null && jsonArray.length() > 0)
            {
                semRegistro.setVisibility(View.GONE);
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                semRegistro.setVisibility(View.VISIBLE);
            }
        }
        catch (Exception e){

        }
    }

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            try
            {
                alertDialog.dismiss();

                categoria = adapter.getItem(position);
                txtCategoria.setText(categoria.getString("nome"));
            }
            catch (Exception e ){ }
        }
    };

    public JSONObject getItemSelected()
    {
        return this.categoria;
    }
}
