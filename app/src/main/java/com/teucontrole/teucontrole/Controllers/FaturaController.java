package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.FaturaRequest;
import com.teucontrole.teucontrole.Repository.FaturaRepository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FaturaController
{
    private Context context;
    private FaturaRequest faturaRequest;
    private FaturaRepository faturaRepository;
    private PerfilController perfilController;

    public FaturaController(Context _context)
    {
        this.context = _context;
        this.faturaRequest = new FaturaRequest(context);
        this.faturaRepository = new FaturaRepository(context);
        this.perfilController = new PerfilController(context);
    }


    public void start() throws Exception
    {
        try
        {
            String ids[] = perfilController.getIdPerfilUserLogged();
            String id_perfil = null;

            if(ids != null && ids.length > 0)
            {

                for(int p =0; p < ids.length; p++)
                {
                    id_perfil = ids[p];
                    JSONArray jArray = requestFaturas(id_perfil);

                    if(jArray != null && jArray.length() > 0)
                    {
                        for(int j=0; j < jArray.length(); j++)
                        {
                            JSONObject jObject = jArray.getJSONObject(j);

                            if(jObject != null)
                                insert(jObject);
                        }
                    }
                }
            }

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public JSONArray getList(Date data, String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            if(data == null)
                data = new Date();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dataFormatada = dateFormat.format(data);

            jArray = faturaRepository.getList(dataFormatada, id_perfil);
        }
        catch (Exception e){
            throw e;
        }

        return jArray;
    }


    public JSONArray requestFaturas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = faturaRequest.requestFaturas(id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public JSONObject requestFatura(String id_perfil, String id_fatura) throws Exception
    {

        JSONObject jObject = null;

        try
        {
            jObject = faturaRequest.requestFatura(id_perfil, id_fatura);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            if(getFatura(jObject.getString("id_fatura")) == null )
                result = faturaRepository.insert(jObject);
            else
                result = update(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean update(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            if(getFatura(jObject.getString("id_fatura")) != null )
                result = faturaRepository.update(jObject);
            else
                result = insert(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONObject getFatura(String id_fatura) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = faturaRepository.getFatura(id_fatura);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }
}
