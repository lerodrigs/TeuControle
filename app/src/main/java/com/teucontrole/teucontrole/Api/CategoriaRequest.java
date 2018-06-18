package com.teucontrole.teucontrole.Api;

import android.content.Context;

import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class CategoriaRequest extends ApiRequest
{
    private Context context;

    public CategoriaRequest (Context _context)
    {
       super(_context);
       this.context = _context;
    }

    public JSONArray getCategoriasReceitas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = get("api/CategoriaReceita?id_perfil="+id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public JSONArray getCategoriasDespesas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = get("api/CategoriaDespesa?id_perfil="+ id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public boolean post(JSONObject obj) throws Exception
    {
        boolean resposta = false;

        try
        {
            resposta = post("", obj);
        }
        catch (Exception e)
        {
            throw e;
        }

        return resposta;
    }

    public boolean put(JSONObject obj) throws Exception
    {
        boolean resposta = false;
        try
        {
            resposta = put("", obj);
        }
        catch (Exception e)
        {
            throw e;
        }
        return resposta;
    }

    public boolean delete(JSONObject jObject) throws Exception
    {
        boolean response = false;

        try
        {
            response = delete(jObject.getString(""));
        }
        catch (Exception e)
        {
            throw e;
        }

        return response;
    }
}
