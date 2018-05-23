package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.Api.UserRequest;

public class SyncController
{
    private UserControllers userController;
    private PerfilController perfilController;

    public SyncController(Context _context)
    {
        this.userController = new UserControllers(_context);
        this.perfilController = new PerfilController(_context);
    }

    /* METODO USADO QUANDO O USUÁRIO FAZ O LOGIN */

    public void start(String email, String pass)
    {
        try
        {
             String token = ApiRequest.token;

             if(token == null)
             {
                 token = userController.getToken(email, pass);
             }

             perfilController.getAll();
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    /*USUARIO JÁ LOGADO
      METODO PARA SINCRONISMO DE INFORMAÇÃO
     */
    public void sync()
    {
        try
        {

        }
        catch (Exception e){}
    }


}
