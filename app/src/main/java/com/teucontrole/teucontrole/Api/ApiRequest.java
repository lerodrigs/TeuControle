package com.teucontrole.teucontrole.Api;

import com.teucontrole.teucontrole.Actitivies.SplashScreenActivity;
import com.teucontrole.teucontrole.Models.Receita;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ApiRequest
{
    String api = "https://www.teucontrole.com/api/";

    public static String token;
    private HttpURLConnection connection;
    private URL url;

    public String getToken(String email, String pass)
    {
        try
        {
            String params = "grant_type=password&username="+email+"&password="+pass;

            url = new URL(api + "Token");

            connection = (HttpURLConnection) url.openConnection();
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

}
