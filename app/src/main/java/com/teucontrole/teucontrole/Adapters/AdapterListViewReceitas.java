package com.teucontrole.teucontrole.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teucontrole.teucontrole.Models.Item;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterListViewReceitas extends BaseAdapter
{
    private JSONArray receitas;
    private Activity context;

    public AdapterListViewReceitas(JSONArray _receitas, Activity _context)
    {
        this.receitas = _receitas;
        this.context = _context;
    }

    @Override
    public int getCount()
    {
        return receitas.length();
    }

    @Override
    public JSONObject getItem(int position)
    {
        JSONObject item = null;

        try
        {
            item = receitas.getJSONObject(position);
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
            id = receitas.getInt(position);
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
            JSONObject receita = receitas.getJSONObject(position);

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
                        status.setText("Aguardando");
                        status.setTextColor(context.getResources().getColor(R.color.secondaryText));
                        break;

                    case "2":
                        status.setText("Pago");
                        status.setTextColor(context.getResources().getColor(R.color.verde));
                        break;

                    case "3":
                        status.setText("Cancelado");
                        status.setTextColor(context.getResources().getColor(R.color.vermelho));
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

                dataStr = Utils.genericFormatDate(dataStr, "dd/mm/yyyy");
                dataVencimento.setText(dataStr);
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

    public void updateListView(JSONArray _receitas)
    {
        try
        {
            receitas = _receitas;
            this.notifyDataSetChanged();
        }
        catch (Exception e){

        }
    }

}
