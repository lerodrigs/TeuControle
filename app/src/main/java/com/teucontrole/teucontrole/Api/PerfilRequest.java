package com.teucontrole.teucontrole.Api;

import android.content.Context;

import com.teucontrole.teucontrole.Actitivies.SplashScreenActivity;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerfilRequest extends ApiRequest
{
    private URL url;
    private String token;
    private ApiUtils apiUtils;
    private UserPreferences userPreferences;
    private HttpURLConnection connection;

    public PerfilRequest(Context _context)
    {
        this.token = ApiRequest.token;
        this.apiUtils = new ApiUtils();
        this.userPreferences = new UserPreferences(_context);
    }

    public JSONArray getAll()
    {
        try
        {
            JSONArray jArray = null;

            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            url = new URL(super.api + "Perfil");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.addRequestProperty("Authorization", "Bearer " + token);

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                jArray = new JSONArray(apiUtils.InputStreamToString(connection.getInputStream()));
            }

            return jArray;
        }
        catch (Exception e )
        {
            return null;
        }
    }

    public JSONObject getById(String id_perfil)
    {
        try
        {
            JSONObject jObject = null;

            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            String auth = "Bearer " + token;

            url = new URL(super.api + "Perfil?id_perfil="+id_perfil);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.addRequestProperty("Authorization", auth);

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                jObject = new JSONObject(apiUtils.InputStreamToString(connection.getInputStream()));
            }

            return jObject;
        }
        catch (Exception e )
        {
            return null;
        }
    }

    public void postOrput(JSONObject perfil, String method)
    {
        try
        {
            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            String auth = "Bearer " + token;

            url = new URL(super.api + "Perfil");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setRequestProperty("Authorizarion", auth);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            outputStreamWriter.write(perfil.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                String retorno = apiUtils.InputStreamToString(connection.getInputStream());
            }

        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    public void delete(String id_perfil, String id_perfil_atual, JSONObject perfil)
    {
        try
        {
            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("email"));
            }

            String auth = "Bearer " + token;

            url = new URL(super.api + "Perfil?id_perfil=" + id_perfil + "?id_perfil_atual=" + id_perfil_atual);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setRequestProperty("Authorization", auth);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            outputStreamWriter.write(perfil.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                String retorno = apiUtils.InputStreamToString(connection.getInputStream());
            }

        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

}
