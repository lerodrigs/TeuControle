package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReceitaRepository
{
    private Context context;
    private MyDbAdapter myDbAdapter;

    public ReceitaRepository(Context _context)
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
            command.append("INSERT INTO RECEITAS (");
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
            String query = "DELETE FROM RECEITAS WHERE ID_RECEITA='"+jObject.getString("id_receita")+"';";

        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONObject getReceita(String id_receita) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            String query = "SELECT * FROM RECEITAS WHERE ID_RECEITA = '" + id_receita + "';";

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
