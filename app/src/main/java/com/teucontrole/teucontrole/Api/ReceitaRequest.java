package com.teucontrole.teucontrole.Api;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReceitaRequest extends ApiRequest
{
    public ReceitaRequest(Context context)
    {
        super(context);
    }

    public JSONArray getReceitas(String endPoint) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = get(endPoint);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public boolean post(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            result = post("api/Receita", jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean put(JSONObject jObject, String id_receita) throws Exception
    {
        boolean result = false;

        try
        {
            result = post("api/Receita?id_receita="+id_receita, jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }
}
