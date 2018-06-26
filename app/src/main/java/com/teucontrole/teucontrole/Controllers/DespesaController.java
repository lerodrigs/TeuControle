 package com.teucontrole.teucontrole.Controllers;

import android.content.Context;
import com.teucontrole.teucontrole.Api.DespesaRequest;
import com.teucontrole.teucontrole.Controllers.PerfilController;
import com.teucontrole.teucontrole.Repository.DespesaRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

 public class DespesaController
{
    private DespesaRepository despesaRepository;
    private DespesaRequest despesaRequest;
    private PerfilController perfilController;
    private Context context;

    public DespesaController(Context _context)
    {
        this.context = _context;
        this.despesaRepository = new DespesaRepository(context);
        this.despesaRequest = new DespesaRequest(context);
        this.perfilController = new PerfilController(context);
    }

    public void start() throws Exception
    {
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
                for(int c=0; c < ids.length; c++ )
                {
                    id_perfil = ids[c];
                    JSONArray jArray = requestDespesas(dataInicio, dataTermino, id_perfil);

                    if( jArray != null && jArray.length() > 0)
                    {
                        for(int i=0; i < jArray.length(); i ++)
                        {
                            JSONObject jObject = jArray.getJSONObject(i);

                            if(jObject != null )
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

    public JSONArray requestDespesas(Date dt_inicio, Date dt_fim, String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String dataInicio = dateFormat.format(dt_inicio);
            String dataFim = dateFormat.format(dt_fim);

            String endPoint = "api/Despesa?id_perfil="+id_perfil+"&dt_inicio="+dataInicio+"&dt_termino="+dataFim;

            jArray = despesaRequest.get(endPoint);
        }
        catch (Exception e )
        {
            throw e;
        }

        return jArray;
    }

    public JSONObject getDespesa(String id_despesa) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = despesaRepository.getDespesa(id_despesa);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public boolean post(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            result = post(jObject);
        }
        catch (Exception e)
        {
            throw e;
        }
        return result;
    }

    public boolean put(JSONObject jObject) throws Exception
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

    public boolean delete(JSONObject jObject) throws Exception
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

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;
        try
        {
            if(getDespesa(jObject.getString("id_despesa")) == null)
                result = despesaRepository.insert(jObject);
            else
                result = update(jObject);
        }
        catch (Exception e )
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
            if(getDespesa(jObject.getString("id_despesa")) != null)
                result = despesaRepository.update(jObject);
            else
                result = insert(jObject);
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;

    }

    public boolean deleteFromDB(String id_despesa) throws Exception
    {
        boolean result = false;

        try
        {
            result = despesaRepository.delete(id_despesa);
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;

    }



}
