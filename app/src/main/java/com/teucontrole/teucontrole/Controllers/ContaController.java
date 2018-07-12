package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.ContaRequest;
import com.teucontrole.teucontrole.Repository.ContaRepository;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContaController
{
    private Context context;
    private ContaRepository contaRepository;
    private ContaRequest contaRequest;
    private PerfilController perfilController;

    public ContaController(Context _context)
    {
        this.context = _context;
        this.contaRepository = new ContaRepository(context);
        this.contaRequest = new ContaRequest(context);
        this.perfilController = new PerfilController(context);
    }

    public boolean start() throws Exception
    {
        boolean result = false;

        try
        {
            String ids[] = perfilController.getIdPerfilUserLogged();
            String id_perfil = null;

            if(ids != null && ids.length > 0)
            {
                JSONArray jsonArray = null;

                for(int c=0; c < ids.length; c++)
                {
                    id_perfil = ids[c];
                    jsonArray = requestContas(id_perfil);

                    if(jsonArray != null && jsonArray.length() > 0)
                    {
                        for(int i=0; i < jsonArray.length(); i++)
                        {
                            JSONObject jObject = jsonArray.getJSONObject(i);

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

        return result;
    }


    public JSONArray requestContas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = contaRequest.requestContas(id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public JSONObject requestConta(String id_perfil, String id_conta, String contaTipo) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = contaRequest.requestConta(id_perfil, id_conta, contaTipo);
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
            if(jObject != null)
            {
                if(getConta(jObject.getString("id_conta")) == null)
                    result = contaRepository.insert(jObject);
                else
                    result = update(jObject);
            }
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
            if(getConta(jObject.getString("id_conta")) != null)
                result = contaRepository.update(jObject);
            else
                result = insert(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }


    public JSONObject getConta(String id_conta) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = contaRepository.getConta(id_conta);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public JSONArray getById(String _idPerfil) throws Exception
    {
        JSONArray jsonArray = null;

        try
        {
            jsonArray = contaRepository.getByIdPerfil(_idPerfil);
        }
        catch (Exception e){
            throw e;
        }

        return jsonArray;
    }

    public boolean deleteFromDB(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            result = contaRepository.delete(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }



}

