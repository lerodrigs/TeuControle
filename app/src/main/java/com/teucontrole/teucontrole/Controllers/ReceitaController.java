package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.ReceitaRequest;
import com.teucontrole.teucontrole.Repository.ReceitaRepository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReceitaController
{
    private Context context;
    private ReceitaRequest receitaRequest;
    private ReceitaRepository receitaRepository;
    private PerfilController perfilController;

    public ReceitaController(Context _context)
    {
        this.context = _context;
        this.receitaRequest = new ReceitaRequest(context);
        this.receitaRepository = new ReceitaRepository(context);
        this.perfilController = new PerfilController(context);
    }

    public boolean start() throws Exception
    {
        boolean result = false;

        try
        {
            String ids[] = perfilController.getIdPerfilUserLogged();
            String id_perfil = null;

            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

            Date dataInicio = calendar.getTime();

            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dataInicio.getDay() + 29);
            Date dataTermino = calendar.getTime();

            if(ids != null && ids.length > 0)
            {
                for(int c=0; c < ids.length; c++)
                {
                    id_perfil = ids[c];
                    JSONArray jsonArray = requestReceitas(dataInicio, dataTermino, id_perfil);

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


    public JSONArray requestReceitas(Date dt_inicio, Date dt_fim, String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String dataInicio = dateFormat.format(dt_inicio);
            String dataFim = dateFormat.format(dt_fim);

            String endPoint = "api/Receita?id_perfil="+id_perfil+"&dt_inicio="+dataInicio+"&dt_termino="+dataFim;

            jArray = receitaRequest.getReceitas(endPoint);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public boolean post(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            result = receitaRequest.post(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean put(JSONObject jObject, String id_receita) throws Exception
    {
        boolean result = false;

        try
        {
            result = receitaRequest.put(jObject, id_receita);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            if(getReceita(jObject.getString("id_receita")) == null)
                result = receitaRepository.insert(jObject);
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
            if(getReceita(jObject.getString("id_receita")) != null)
                result = receitaRepository.update(jObject);
            else
                result = insert(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean deleteFromDB(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {

        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONObject getReceita(String id_receita) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = receitaRepository.getReceita(id_receita);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public JSONArray getList(Date data) throws Exception
    {
        JSONArray list = null;

        try
        {
            if(data == null){
                data = new Date();
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            list = receitaRepository.getList(dateFormat.format(data));
        }
        catch (Exception e){
            throw e;
        }

        return list;
    }
}
