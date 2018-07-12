package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.teucontrole.teucontrole.Adapters.AdapterListViewItemGeneric;
import com.teucontrole.teucontrole.Controllers.SituacaoController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoadSituacaoDialogTask extends AsyncTask<String, Void, JSONArray>
{
    private TextView textView;
    private View view;
    private Activity context;
    private JSONArray items;

    private JSONObject itemSelecionado;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builderDialog;
    private ListView listView;
    private AdapterListViewItemGeneric adapter;

    private SituacaoController situacaoController;

    public LoadSituacaoDialogTask(TextView _textView, Activity _context)
    {
        this.textView = _textView;
        this.context = _context;

        this.situacaoController = new SituacaoController(context);
        this.builderDialog = new AlertDialog.Builder(context);

        this.view = context.getLayoutInflater().inflate(R.layout.dialog_items, null);
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
            items = situacaoController.getList();
        }
        catch (Exception e){

        }

        return items;
    }

    @Override
    protected void onPostExecute(JSONArray jArray)
    {
        try
        {
            TextView semRegistro = view.findViewById(R.id.sem_registros);
            ProgressBar progressBar = view.findViewById(R.id.progressBar1);

            listView = view.findViewById(R.id.listview);
            listView.setOnItemClickListener(clickListView);

            progressBar.setVisibility(View.GONE);

            if(jArray != null && jArray.length() > 0)
               semRegistro.setVisibility(View.GONE);
            else
                semRegistro.setVisibility(View.VISIBLE);

            if(adapter == null)
            {
                adapter = new AdapterListViewItemGeneric(context, jArray, "nome", "id_titulo_status");
                listView.setAdapter(adapter);
            }

            adapter.updateListView(jArray);
        }
        catch (Exception e){

        }
    }

    public JSONObject getSituacaoSelecionada()
    {
        return this.itemSelecionado;
    }

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            try
            {
                alertDialog.dismiss();

                itemSelecionado = adapter.getItem(position);
                textView.setText(itemSelecionado.getString("nome"));
            }
            catch (Exception e ){ }
        }
    };
}
