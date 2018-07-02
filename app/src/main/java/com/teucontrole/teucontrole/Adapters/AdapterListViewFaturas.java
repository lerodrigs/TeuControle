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

public class AdapterListViewFaturas extends BaseAdapter
{
    private JSONArray faturas;
    private Activity context;

    public AdapterListViewFaturas(JSONArray _faturas, Activity _context)
    {
        this.faturas = _faturas;
        this.context = _context;
    }

    @Override
    public int getCount()
    {
        return faturas.length();
    }

    @Override
    public JSONObject getItem(int position)
    {
        JSONObject item = null;

        try
        {
            item = faturas.getJSONObject(position);
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
            id = faturas.getInt(position);
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
            JSONObject receita = faturas.getJSONObject(position);

            if(view == null)
                view = context.getLayoutInflater().inflate(R.layout.receita_row, null);

            TextView nome = view.findViewById(R.id.nome_row);
            TextView dataVencimento = view.findViewById(R.id.data_vencimento_dow);
            TextView status = view.findViewById(R.id.status_row);
            TextView valor = view.findViewById(R.id.valor_row);
            TextView categoria = view.findViewById(R.id.categoria_row);

            if(Utils.getValueJObject(receita, "id_titulo_status") != null)
            {
                switch (receita.getString("id_titulo_status"))
                {
                    case "1":
                        break;

                    case "2":
                        break;

                    case "3":
                        break;

                    case "0":
                        break;
                }
            }

            if(Utils.getValueJObject(receita, "nome") != null)
                nome.setText(receita.getString("nome"));

            if(Utils.getDateFromJObject(receita, "data_vencimento") != null)
            {
                String dataStr = Utils.getDateFromJObject(receita, "data_vencimento");
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

            if(Utils.getValueJObject(receita, "valor") != null)
                valor.setText(receita.getString("valor"));

            if(Utils.getValueJObject(receita, "categoria_receita_nome") != null)
                categoria.setText(receita.getString("categoria_receita_nome"));

            return view;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void updateListView(JSONArray _faturas)
    {
        try
        {
            this.faturas = _faturas;
            this.notifyDataSetChanged();
        }
        catch (Exception e){

        }
    }
}
