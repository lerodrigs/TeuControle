package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.ReceitaRequest;
import com.teucontrole.teucontrole.Repository.ReceitaRepository;
import com.teucontrole.teucontrole.Utils.Utils;

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

    public JSONObject requestReceita(String id_perfil, String id_receita) throws Exception
    {
        JSONObject receita = null;

        try
        {
            JSONArray jsonArray = receitaRequest.getReceitas("api/Receita?_id_perfil="+id_perfil+"&_id_receita="+id_receita);

            if(jsonArray != null && jsonArray.length() > 0)
            {
                for (int i=0; i< jsonArray.length(); i++)
                {
                    receita = jsonArray.getJSONObject(i);
                }
            }
        }
        catch (Exception e){
            throw e;
        }

        return receita;
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

    public JSONArray getList(Date data, String id_perfil) throws Exception
    {
        JSONArray list = null;

        try
        {
            if(data == null){
                data = new Date();
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            list = receitaRepository.getList(dateFormat.format(data), id_perfil);
        }
        catch (Exception e){
            throw e;
        }

        return list;
    }

    public String validaObject(JSONObject jsonObject) throws Exception
    {
        String message = null;

        try
        {
            if(Utils.getValueJObject(jsonObject, "nome") == null)
                message = "Receita inválida, favor informar o nome!";

            if(Utils.getValueJObject(jsonObject, "id_conta") == null)
                message = "Receita inválida, selecione uma conta!";

            if(Utils.getValueJObject(jsonObject, "id_titulo_status") == null)
                message = "Receita inválida, selecione uma situação!";

            if(Utils.getValueJObject(jsonObject, "data_vencimento") == null)
                message = "Receita inválida, informar a data de vencimento!";

            if(Utils.getValueJObject(jsonObject, "data_pagamento") != null)
            {

            }

            if(Utils.getValueJObject(jsonObject, "valor") == null || Utils.getValueJObject(jsonObject, "valor") == "0")
                message = "Receita inválida, informar o valor!";
        }
        catch (Exception e){
            throw e;
        }

        return message;
    }

    public JSONObject completeJSON(JSONObject receita) throws Exception
    {
        try
        {
            JSONObject oldReceita = getReceita(receita.getString("id_receita"));

            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            Date date = new Date();

            if(!Utils.existsParam(receita, "data_cadastro")){
                if(oldReceita == null)
                    receita.put("data_cadastro", dateFormat.format(date));
                else
                    receita.put("data_cadastro", Utils.getValueJObject(oldReceita, "data_cadastro") != null ? dateFormat.format(oldReceita.getString("data_cadastro")) : dateFormat.format(date));
            }

            if(!Utils.existsParam(receita, "data_modificacao")){
                if(oldReceita == null)
                    receita.put("data_modificacao", dateFormat.format(date));
                else {
                    receita.put("data_modificacao", Utils.getValueJObject(oldReceita, "data_modificacao") != null ? dateFormat.format(oldReceita.getString("data_modificacao")) : dateFormat.format(date));
                }
            }

            if(!Utils.existsParam(receita, "excecao")){
                if(oldReceita == null)
                    receita.put("excecao", 1);
                else
                    receita.put("excecao", Utils.getValueJObject(oldReceita, "excecao") != null ? oldReceita.getString("excecao") : 1);
            }

            if(!Utils.existsParam(receita, "cor")){
                if(oldReceita == null)
                    receita.put("cor", JSONObject.NULL);
                else
                    receita.put("cor", Utils.getValueJObject(oldReceita, "cor") != null ? oldReceita.getString("cor") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "id_receita_recorrente")){
                if(oldReceita == null)
                    receita.put("id_receita_recorrente", JSONObject.NULL);
                else
                    receita.put("id_recorrente_receita", Utils.getValueJObject(oldReceita, "id_recorrente_receita") != null ? oldReceita.getString("id_recorrente_receita") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "id_recorrente_frequencia")){
                if(oldReceita == null)
                    receita.put("id_recorrente_frequencia", JSONObject.NULL);
                else
                    receita.put("id_recorrente_frequencia", Utils.getValueJObject(oldReceita, "id_recorrente_frequencia") != null ? oldReceita.getString("id_recorrente_frequencia") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "inicio")){
                if(oldReceita == null)
                    receita.put("inicio", JSONObject.NULL);
                else
                    receita.put("inicio", Utils.getValueJObject(oldReceita, "inicio") != null ? oldReceita.getString("inicio") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "termino")){
                if(oldReceita == null)
                    receita.put("termino", JSONObject.NULL);
                else
                    receita.put("termino", Utils.getValueJObject(oldReceita, "termino") != null ? oldReceita.getString("termino") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "intervalo")){
                if(oldReceita == null)
                    receita.put("intervalo", JSONObject.NULL);
                else
                    receita.put("intervalo", Utils.getValueJObject(oldReceita, "intervalo") != null ? oldReceita.getString("intervalo") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "total_ocorrencias")){
                if(oldReceita == null)
                    receita.put("total_ocorrencias", JSONObject.NULL);
                else
                    receita.put("total_ocorrencias", Utils.getValueJObject(oldReceita, "total_ocorrencias") != null ? oldReceita.getString("total_ocorrencias") : JSONObject.NULL);
            }

            if(!Utils.existsParam(receita, "tipo_fim")){
                if(oldReceita == null)
                    receita.put("tipo_fim", JSONObject.NULL);
                else
                    receita.put("tipo_fim", Utils.getValueJObject(oldReceita, "tipo_fim") != null ? oldReceita.getString("tipo_fim") : JSONObject.NULL);
            }
        }
        catch (Exception e){
            throw e;
        }

        return receita;
    }
}
