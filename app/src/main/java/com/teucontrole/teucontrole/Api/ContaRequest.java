package com.teucontrole.teucontrole.Api;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContaRequest extends ApiRequest
{
    Context context;

    public ContaRequest(Context _context)
    {
        super(_context);
        this.context = _context;
    }

    public JSONArray requestContas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = get("api/Conta?id_perfil="+id_perfil);
        }
        catch (Exception e )
        {
            throw e;
        }

        return jArray;
    }

    public JSONObject requestConta(String id_perfil, String id_conta, String contaTipo) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            JSONArray jArray = get("api/Conta?id_perfil="+id_perfil+"&id_conta="+id_conta+"&id_contatipo="+contaTipo);

            if(jArray != null &&  jArray.length() > 0)
            {
                for(int c=0; c < jArray.length(); c++)
                {
                    jObject = jArray.getJSONObject(c);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public boolean post(JSONObject jsonObject) throws Exception
    {
        boolean result = false;

        try
        {
            result = post("api/Conta", jsonObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean put(JSONObject jsonObject) throws Exception
    {
        boolean result = false;

        try
        {
            result = put("api/Conta", jsonObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean delete(String id_perfil) throws Exception
    {
        boolean result = false;

        try
        {
            result = delete("api/Conta?id_perfil=" +id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }
}
