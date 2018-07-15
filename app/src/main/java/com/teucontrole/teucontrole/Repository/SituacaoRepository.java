package com.teucontrole.teucontrole.Repository;

import android.app.Activity;
import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class SituacaoRepository
{

    private Context context;
    private MyDbAdapter myDbAdapter;

    public SituacaoRepository (Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public JSONArray getList() throws Exception
    {
        JSONArray lista = null;

        try
        {
            lista = myDbAdapter.get("SELECT * FROM TITULOS_STATUS;");
        }
        catch (Exception e){
            throw e;
        }

        return lista;
    }

    public JSONObject get(int _id_titulo_status) throws Exception
    {
        JSONObject item = null;

        try
        {
            JSONArray items = myDbAdapter.get("SELECT * FROM TITULOS_STATUS where id_titulo_status = '"+_id_titulo_status+"';");

            if(items != null && items.length() > 0 )
            {
                for (int i=0; i < items.length(); i++)
                {
                    item = items.getJSONObject(i);
                }
            }
        }
        catch (Exception e){
            throw e;
    }

        return item;
    }

    public boolean firstCharge() throws Exception
    {
        boolean result = false;
        StringBuilder builder = new StringBuilder();

        try
        {
            JSONArray jArray = getList();

            if(jArray != null && jArray.length() ==0)
            {
                builder.append("INSERT INTO TITULOS_STATUS (id_titulo_status, nome) ");
                builder.append("values ");
                builder.append("(1, 'Aguardando'), ");
                builder.append("(2, 'Pago'), ");
                builder.append("(3, 'Cancelado'); ");
            }

            result = myDbAdapter.execCommand(builder.toString());
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }


}
