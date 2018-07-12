package com.teucontrole.teucontrole.Controllers;

import android.app.Activity;

import com.teucontrole.teucontrole.Repository.SituacaoRepository;

import org.json.JSONArray;
import org.json.JSONObject;

public class SituacaoController
{
    private Activity context;
    private SituacaoRepository situacaoRepository;

    public SituacaoController(Activity _context)
    {
        this.context = _context;
        this.situacaoRepository = new SituacaoRepository(context);
    }

    public JSONObject get(int id_titulo_status) throws Exception
    {
        JSONObject item = null;

        try
        {
            item = situacaoRepository.get(id_titulo_status);
        }
        catch (Exception e){
            throw e;
        }

        return item;
    }

    public JSONArray getList() throws Exception
    {
        JSONArray items = null;

        try
        {
            items = situacaoRepository.getList();
        }
        catch (Exception e){
            throw e;
        }

        return items;
    }

    public void firstCharge() throws Exception
    {
        try
        {
            situacaoRepository.firstCharge();
        }
        catch (Exception e){
            throw e;
        }
    }
}
