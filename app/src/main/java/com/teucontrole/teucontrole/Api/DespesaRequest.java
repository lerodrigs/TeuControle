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
    private String token;
    private URL url;
    private HttpURLConnection connection;
    private ApiUtils apiUtils;
    private UserPreferences userPreferences;

    public DespesaRequest(Context _context)
    {
        this.token = ApiRequest.token;
        this.apiUtils = new ApiUtils();
        this.userPreferences = new UserPreferences(_context);
    }

    public JSONArray get(String id_perfil, Date dt_inicio, Date dt_fim)
    {
        try
        {
            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            JSONArray jsonArray = null;
            url = new URL(super.api + "Despesa?id_perfil=" + id_perfil + "&data_inicio="+ dt_inicio + "&data_fim=" + dt_fim);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.addRequestProperty("Authorization", "Bearer " + token);

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                jsonArray = new JSONArray(apiUtils.InputStreamToString(connection.getInputStream()));
            }

            return jsonArray;

        }
        catch (Exception e )
        {
            return null;
        }
    }

    public void delete(String id_perfil, Date dt_inicio, JSONObject despesa)
    {
        try
        {
            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            url = new URL(super.api + "Despesa?id_perfil=" + id_perfil + "&data_inicio="+ dt_inicio);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.addRequestProperty("Authorization", "Bearer " + token);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            outputStreamWriter.write(despesa.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                String retorno = apiUtils.InputStreamToString(connection.getInputStream());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void postOrput(JSONObject despesa, String method)
    {
        try
        {
            if(token == null)
            {
                token = super.getToken(userPreferences.get("email"), userPreferences.get("pass"));
            }

            url = new URL(super.api + "Despesa");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.addRequestProperty("Authorization", "Bearer " + token);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            outputStreamWriter.write(despesa.toString());
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
