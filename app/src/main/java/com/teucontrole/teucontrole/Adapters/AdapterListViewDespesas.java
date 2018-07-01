package com.teucontrole.teucontrole.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdapterListViewDespesas extends BaseAdapter
{
    private JSONArray despesas;
    private Activity context;

    public AdapterListViewDespesas(JSONArray _list, Activity _context)
    {
        this.despesas = _list;
        this.context = _context;
    }

    @Override
    public int getCount()
    {
        return despesas.length();
    }

    @Override
    public JSONObject getItem(int position)
    {
        JSONObject item = null;

        try
        {
            item = despesas.getJSONObject(position);
        }
        catch (JSONException e) {
        }

        return item;
    }

    @Override
    public long getItemId(int position)
    {
        long id = 0;

        try
        {
            id = despesas.getInt(position);
        }
        catch (Exception e) {
        }

        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        try
        {
            View view = convertView;
            JSONObject despesa = despesas.getJSONObject(position);

            if(view == null)
                view = context.getLayoutInflater().inflate(R.layout.receita_row, null);

            TextView nome = view.findViewById(R.id.nome_row);
            TextView dataVencimento = view.findViewById(R.id.data_vencimento_dow);
            TextView status = view.findViewById(R.id.status_row);
            TextView valor = view.findViewById(R.id.valor_row);
            TextView categoria = view.findViewById(R.id.categoria_row);

            if(Utils.getValueJObject(despesa, "nome") != null)
                nome.setText(despesa.getString("nome"));

            if(Utils.getDateFromJObject(despesa, "data_vencimento") != null)
            {
                String dataStr = Utils.getDateFromJObject(despesa, "data_vencimento");
                String _mes;

                int ano = Integer.parseInt(dataStr.substring(0,4));
                int mes = Integer.parseInt(dataStr.substring(5,7));
                int dia = Integer.parseInt(dataStr.substring(8,10));

                if(mes < 9 )
                    _mes = "0"+mes;
                else
                    _mes = String.valueOf(mes);

                dataVencimento.setText(dia + "/" + _mes + "/" + ano);
            }

            if(Utils.getValueJObject(despesa, "valor") != null)
                valor.setText(despesa.getString("valor"));

            if(Utils.getValueJObject(despesa, "categoria_despesa_nome") != null)
                categoria.setText(despesa.getString("categoria_despesa_nome"));

            return view;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void updateListView(JSONArray _despesas)
    {
        try
        {
            this.despesas = _despesas;
            this.notifyDataSetChanged();
        }
        catch (Exception e){

        }
    }
}
