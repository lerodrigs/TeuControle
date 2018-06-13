package com.teucontrole.teucontrole.Api;

import android.content.Context;

import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserRequest extends ApiRequest
{
    public UserRequest(Context _context)
    {
        super(_context);
    }

    public JSONObject getInfo(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            JSONArray jsonArray = get("api/Usuario?email="+email);
            jObject = jsonArray.getJSONObject(0);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }
}
