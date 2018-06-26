package com.teucontrole.teucontrole.Api;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

public class FaturaRequest extends ApiRequest
{
    private Context context;

    public FaturaRequest(Context _context)
    {
        super(_context);
        this.context = _context;
    }

    public JSONArray requestFaturas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = get("api/Fatura?id_perfil"+id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public JSONObject requestFatura(String id_perfil, String id_fatura) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            JSONArray jArray = get("api/Fatura?id_perfil"+id_perfil+"&id_fatura="+id_fatura);

            if(jArray != null && jArray.length() > 0)
            {
                jObject = jArray.getJSONObject(0);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

}
