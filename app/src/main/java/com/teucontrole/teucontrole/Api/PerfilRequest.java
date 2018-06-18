package com.teucontrole.teucontrole.Api;

import android.content.Context;
import android.util.Log;

import com.teucontrole.teucontrole.Actitivies.SplashScreenActivity;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerfilRequest extends ApiRequest
{
    public PerfilRequest(Context _context)
    {
        super(_context);
    }

    public JSONArray getAll() throws Exception
    {
        JSONArray jArray = new JSONArray();

        try
        {
            jArray = get("api/Perfil");
        }
        catch (Exception e )
        {
            throw e;
        }

        return jArray;
    }

    public JSONObject getById(String id_perfil) throws Exception
    {

        JSONObject jObject = null;
        try
        {
            JSONArray jArray = get("api/Perfil?id_perfil="+id_perfil);

            if(jArray != null && jArray.length() > 0)
            {
                jObject = jArray.getJSONObject(0);
            }
        }
        catch (Exception e )
        {
            throw e;
        }
        return jObject;
    }

    public boolean post(JSONObject jObject) throws Exception
    {
        boolean response = false;

        try
        {
            response = post("", jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return response;
    }

    public boolean put(JSONObject jObject) throws Exception
    {
        boolean response = false;

        try
        {
            response = put("", jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return response;
    }

    public boolean delete(JSONObject jObject) throws Exception
    {
        boolean response = false;

        try
        {
            response = delete("");
        }
        catch(Exception e)
        {
            throw e;
        }
        return response;
    }







}
