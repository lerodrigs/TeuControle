package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class DespesaRepository
{
    private Context context;
    private MyDbAdapter myDbAdapter;

    public DespesaRepository(Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;
        StringBuilder command = new StringBuilder();

        try
        {
            command.append("INSERT INTO DESPESAS (");
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean update(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {

        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean delete(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            String query = "DELETE FROM DESPESAS WHERE ID_DESPESA='"+jObject.getString("id_despesa")+"';";

        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONObject getDespesa(String id_despesa) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            String query = "SELECT * FROM RECEITAS WHERE ID_RECEITA = '" + id_despesa + "';";

            JSONArray jArray = myDbAdapter.get(query);

            if(jArray != null && jArray.length() > 0)
            {
                for(int i=0; i < jArray.length(); i++)
                {
                    jObject = jArray.getJSONObject(i);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }
}
