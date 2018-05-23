package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.PerfilRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class PerfilController
{
    private Context context;
    private PerfilRequest perfilRequest;

    public PerfilController(Context _context)
    {
        this.context = _context;
        this.perfilRequest = new PerfilRequest(_context);
    }

    public JSONArray getAll()
    {
        try
        {
            JSONArray jsonArray = perfilRequest.getAll();

            return jsonArray;
        }
        catch (Exception e ){ return null; }
    }

    public JSONObject getById(String id_perfil)
    {
        try
        {
            JSONObject jObject = perfilRequest.getById(id_perfil);

            return jObject;
        }
        catch (Exception e ){ return null; }
    }

}
