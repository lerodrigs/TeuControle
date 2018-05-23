package com.teucontrole.teucontrole.Api;

import com.teucontrole.teucontrole.Utils.ApiUtils;
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

    public JSONObject getInfo(String email)
    {
        try
        {
            JSONObject jObject = null;
            url = new URL("https://www.teucontrole.com/api/Usuario?email="+email);

            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer "+ token);

            connection.connect();
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                ApiUtils apiUtils = new ApiUtils();
                jObject = new JSONObject(apiUtils.InputStreamToString(connection.getInputStream()).toString());
            }

            return jObject;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
