package com.teucontrole.teucontrole.Api;

import com.teucontrole.teucontrole.Models.Receita;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class ApiRequest
{
    String api = "https://www.teucontrole.com/api/";
    HttpURLConnection connection;
    URL url;

    static String token;

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

    public List<Receita> getReceitas(String id_perfil, Date dt_inicio, Date dt_fim)
    {
        try
        {
            List<Receita> receitas = new ArrayList<Receita>();

            if(token != null)
            {
                url = new URL(api);
            }

            return receitas;
        }
        catch (Exception e ){return null;}
    }

    public Receita getReceitaById(String id_perfil, String id_receita, String id_receita_recorrente, byte excecao, Date dt_ocorrencia, byte tipo_edicao)
    {
        try
        {
            Receita receita = new Receita();

            return null;
        }
        catch (Exception e ){ return null;}
    }

}
