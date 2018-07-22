package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.teucontrole.teucontrole.Controllers.ReceitaController;
import com.teucontrole.teucontrole.R;

import org.json.JSONObject;

public class ProcessReceitaTask extends AsyncTask<String, Void, Boolean>
{
    private int process;
    private JSONObject receita;
    private Activity context;
    private ReceitaController receitaController;
    private Dialog dialog;
    private String messageValidation;
    private LinearLayout rootView;

    public ProcessReceitaTask(Activity _context, JSONObject item, int _process, LinearLayout _rootView)
    {
        this.receita = item;
        this.process = _process;
        this.context = _context;
        this.receitaController = new ReceitaController(context);
        this.messageValidation = null;
        this.rootView = _rootView;
    }

    @Override
    protected void onPreExecute()
    {
        try
        {
            dialog = new Dialog(context);
            View view = context.getLayoutInflater().inflate(R.layout.loader, null);

            dialog.setContentView(view);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();
        }
        catch (Exception e ){
            throw e;
        }
    }

    @Override
    protected Boolean doInBackground(String... strings)
    {
        boolean result = false;

        try
        {
            messageValidation = receitaController.validaObject(receita);

            if(messageValidation != null)
                return false;

            receita = receitaController.completeJSON(receita);

            switch(process)
            {
                case 1:
                    result = receitaController.insert(receita);

                    if(result)
                        result = receitaController.post(receita);

                    if(!result)
                        receitaController.deleteFromDB(receita);

                    break;
                case 2:

                    JSONObject oldReceita = receitaController.getReceita(receita.getString("id_receita"));
                    result = receitaController.update(receita);

                    if(result)
                        result = receitaController.put(receita, receita.getString("id_receita"));

                    if(!result)
                        receitaController.update(oldReceita);

                    break;
                case 3:
                    result = receitaController.deleteFromDB(receita);

                    if(result)
                        result = receitaController.delete(receita);

                    if(!result)
                        receitaController.insert(receita);

                    break;
            }
        }
        catch (Exception e){ }

        return result;
    }

    @Override
    protected void onPostExecute(Boolean _result)
    {
        try
        {
            if(dialog != null)
                dialog.dismiss();

            String action = "";
            String message = "";

            switch(process)
            {
                case 1:
                    action ="salva";
                    break;
                case 2:
                    action = "atualizada";
                    break;
                case 3:
                    action = "deletada";
                    break;
            }

            if(_result && messageValidation== null)
                message = "Receita "+action+" !";
            else if(!_result && messageValidation != null)
                message = this.messageValidation;
            else
                message = "Erro ao processar receita!";

            Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
                    .setAction("OK", null)
                    .show();
        }
        catch (Exception e){
            throw e;
        }
    }
}
