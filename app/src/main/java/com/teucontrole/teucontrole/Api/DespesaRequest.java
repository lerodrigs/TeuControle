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

    public JSONArray get(String id_perfil, Date dt_inicio, Date dt_fim)
    {
        return null;
    }

    public void delete(String id_perfil, Date dt_inicio, JSONObject despesa)
    {

    }

    public void postOrput(JSONObject despesa, String method)
    {

    }


}
