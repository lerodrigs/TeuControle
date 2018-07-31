package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.teucontrole.teucontrole.Controllers.UserControllers;

import org.json.JSONObject;

public class LoadInfoUserTask extends AsyncTask<String, Void, JSONObject> {

    private JSONObject jUser;
    private TextView txtnome;
    private TextView txtemail;
    private Activity context;
    private UserControllers userController;

    public LoadInfoUserTask(Activity _context, TextView _nome, TextView _email)
    {
        this.context = _context;
        this.txtnome = _nome;
        this.txtemail = _email;
        this.userController = new UserControllers(context);
    }

    @Override
    protected void onPreExecute()
    {
        try
        {

        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    protected JSONObject doInBackground(String... strings)
    {
        try
        {
            jUser = userController.getFromUserLogged();
            return jUser;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject jObject)
    {
        try
        {
            String nome = jObject.getString("nome");
            String email = jObject.getString("email");

            if(nome != null)
                txtnome.setText(nome);

            if(email != null)
                txtemail.setText(email);
        }
        catch(Exception e){

        }
    }

}
