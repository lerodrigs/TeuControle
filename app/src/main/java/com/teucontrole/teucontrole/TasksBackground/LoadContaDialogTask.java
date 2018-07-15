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
import com.teucontrole.teucontrole.Controllers.ContaController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoadContaDialogTask extends AsyncTask<String, Void, JSONArray>
{
    private ContaController contaController;
    private Activity context;

    private TextView textView;
    private View view;
    private ListView listView;
    private AdapterListViewItemGeneric adapter;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builderDialog;

    private JSONObject itemSelected;
    private JSONArray items;
    private String idPerfil;

    public LoadContaDialogTask(Activity _context, TextView _textView, String _idPerfil)
    {
        this.context = _context;
        this.view = context.getLayoutInflater().inflate(R.layout.dialog_items, null);
        this.contaController = new ContaController(context);
        this.textView = _textView;
        this.builderDialog = new AlertDialog.Builder(context);
        this.idPerfil = _idPerfil;
    }

    @Override
    protected void onPreExecute()
    {
        try
        {
            builderDialog.setTitle("Situações");
            builderDialog.setView(view);

            alertDialog = builderDialog.create();
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
            items = contaController.getById(idPerfil);
        }
        catch (Exception e){

        }

        return items;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray)
    {
        listView = view.findViewById(R.id.listview);
        listView.setOnItemClickListener(clickListView);

        TextView semRegistro = view.findViewById(R.id.sem_registros);
        ProgressBar progressBar = view.findViewById(R.id.progressBar1);

        if(adapter == null)
        {
            adapter = new AdapterListViewItemGeneric(context, jsonArray, "nome", "id_conta");
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

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            try
            {
                alertDialog.dismiss();

                itemSelected = adapter.getItem(position);
                textView.setText(itemSelected.getString("nome"));
            }
            catch (Exception e ){ }
        }
    };

    public JSONObject getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(JSONObject _itemSelected)
    {
        this.itemSelected = _itemSelected;
    }
}
