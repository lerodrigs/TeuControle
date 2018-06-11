package com.teucontrole.teucontrole.Api;

import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserRequest extends ApiRequest
{
    String token;
    URL url;
    HttpURLConnection connection;

    public UserRequest()
    {
        this.token = ApiRequest.token;
    }

    public JSONObject getInfo(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            ApiUtils apiUtils = new ApiUtils();
            url = new URL(super.api +"/api/Usuario?email="+email);

            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer "+ token);

            connection.connect();
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                JSONArray jArray = new JSONArray(apiUtils.InputStreamToString(connection.getInputStream()));
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
