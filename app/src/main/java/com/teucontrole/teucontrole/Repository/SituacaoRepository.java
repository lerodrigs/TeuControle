package com.teucontrole.teucontrole.Repository;

import android.app.Activity;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class SituacaoRepository
{

    private Activity context;
    private MyDbAdapter myDbAdapter;

    public SituacaoRepository (Activity _context)
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

    public void firstCharge() throws Exception
    {
        StringBuilder command = new StringBuilder();

        try
        {
            JSONArray lists = getList();

            if(lists == null ||  lists.length() == 0)
            {
                command.append("INSERT INTO TITULOS_STATUS (id_titulo_status, nome) VALUES (1,'nome'), ");
                command.append("(2, 'recebido'), ");
                command.append("(3, 'cancelado');");

                myDbAdapter.execCommand(command.toString());
            }
        }
        catch (Exception e){
            throw e;
        }
    }

}
