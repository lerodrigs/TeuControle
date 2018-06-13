package com.teucontrole.teucontrole.Api;

import android.content.Context;

import com.teucontrole.teucontrole.Actitivies.SplashScreenActivity;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.ApiUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiRequest
{
    private String api;
    private String token;
    private UserPreferences userPreferences;
    private Context context;
    private ApiUtils apiUtils;

    public ApiRequest(Context _context )
    {
        this.context = _context;
        this.userPreferences = new UserPreferences(context);
        this.apiUtils = new ApiUtils();
        this.api = "https://www.teucontrole.com/api/";
    }

    public String getToken(String email, String pass)
    {
        try
        {
            String params = "grant_type=password&username="+email+"&password="+pass;

            URL url = new URL(api + "Token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput( true );

            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            outputStreamWriter.write(params);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                JSONObject jObject;

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                jObject = new JSONObject(br.readLine());

                if(jObject.getString("access_token") != null)
                {
                    token = jObject.getString("access_token");
                }

                UserPreferences userPreferences = new UserPreferences(SplashScreenActivity.context);

                userPreferences.set("email", email);
                userPreferences.set("pass", pass);

                return token;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            return null;
        }
    }

    public JSONArray get(String endPoint) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            if(token == null)
            {
                token = getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            URL url = new URL(api + endPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.addRequestProperty("Authorization", "Bearer " + token);

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                String retorno = apiUtils.InputStreamToString(connection.getInputStream());
                jArray = new JSONArray(retorno);
            }

        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public boolean post(String endPoint, JSONObject jObject) throws Exception
    {
        boolean resposta = false;

        try
        {
            URL url = new URL(api + endPoint);
        }
        catch (Exception e)
        {
            throw e;
        }

        return resposta;
    }

    public boolean put(String endPoint, JSONObject jObject) throws Exception
    {
        boolean resposta = false;

        try
        {
            URL url = new URL(api + endPoint);
        }
        catch (Exception e)
        {
            throw e;
        }

        return resposta;
    }

    public boolean delete(String endPoint, JSONObject jObject) throws Exception
    {
        boolean resposta = false;

        try
        {
           URL url = new URL(api + endPoint);
        }
        catch (Exception e)
        {
            throw e;
        }

        return resposta;
    }

}
