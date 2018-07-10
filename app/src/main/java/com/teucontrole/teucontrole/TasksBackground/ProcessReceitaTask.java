package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.View;
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

    public ProcessReceitaTask(Activity _context, JSONObject item, int _process)
    {
        this.receita = item;
        this.process = _process;
        this.context = _context;
        this.receitaController = new ReceitaController(context);

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
            switch(process)
            {
                case 1:
                    result = receitaController.insert(receita);
                    break;
                case 2:
                    result = receitaController.update(receita);
                    break;
                case 3:
                    result = receitaController.deleteFromDB(receita);
                    break;
            }
        }
        catch (Exception e){ }

        return true;
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

            if(_result)
                message = "Receita "+action+" !";
            else
                message = "Erro ao "+action+" receita!";

            Toast.makeText(context, message, Toast.LENGTH_LONG)
                    .show();


        }
        catch (Exception e){

        }
    }
}
