package com.teucontrole.teucontrole.Controllers;

import android.content.Context;
import android.util.Log;

import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.Api.UserRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import org.json.JSONArray;

public class SyncController
{
    private UserControllers userController;
    private PerfilController perfilController;
    private CategoriaController categoriaController;
    private ContaController contaController;
    private ReceitaController receitaController;
    private UserPreferences userPreferences;
    private Context context;

    public SyncController(Context _context)
    {
        this.context = _context;
        this.userController = new UserControllers(_context);
        this.perfilController = new PerfilController(_context);
        this.userPreferences = new UserPreferences(_context);
        this.categoriaController = new CategoriaController(context, true); //receita
        this.contaController = new ContaController(context);
        this.receitaController = new ReceitaController(context);
    }


    public void start(String email, String pass) throws Exception
    {
        try
        {
            userController.start(email);
            perfilController.start();
            categoriaController.start();
            contaController.start();
            receitaController.start();
            //receita recorrente
            //despesa,
            //despesa recorrente
            //fatura
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void sync()
    {
        try
        {

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void logoff()
    {
        try
        {
            userPreferences.remove("email");
            userPreferences.remove("pass");
            userPreferences.remove("isLogged");

            MyDbAdapter myDbAdapter = new MyDbAdapter(context);
            myDbAdapter.dropDb();

        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
