package com.teucontrole.teucontrole.Api;

import android.content.Context;

import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class DespesaRequest extends ApiRequest
{
    public DespesaRequest(Context _context)
    {
        super(_context);
    }

    public JSONArray request(String endPoint) throws Exception
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

    public boolean delete(String id_perfil, Date dt_inicio, JSONObject despesa)
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

    public boolean post(JSONObject despesa, String method)
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

    public boolean put(JSONObject jObject ) throws Exception
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

}
