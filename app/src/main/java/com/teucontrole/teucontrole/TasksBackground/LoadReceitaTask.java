package com.teucontrole.teucontrole.TasksBackground;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.teucontrole.teucontrole.Controllers.ReceitaController;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONObject;

public class LoadReceitaTask extends AsyncTask<String, Void, JSONObject>
{
    private String id_receita;
    private String id_perfil;
    private Activity context;
    private ReceitaController receitaController;
    private Dialog dialog;
    private JSONObject jObject;
    private View view;

    public LoadReceitaTask(String _id_receita, String _id_perfil, Activity _context, View _view)
    {
        this.id_receita = _id_receita;
        this.id_perfil = _id_perfil;
        this.context = _context;
        this.view = _view;
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
    protected JSONObject doInBackground(String... strings)
    {
        jObject = null;

        try
        {
            jObject = receitaController.getReceita(id_receita);

            if(jObject == null)
                jObject = receitaController.requestReceita(id_receita, id_perfil);
        }
        catch (Exception e){
        }

        return jObject;
    }

    @Override
    protected void onPostExecute(JSONObject jObject)
    {
        try
        {
            if(dialog != null)
            {
                dialog.dismiss();

                EditText nome = view.findViewById(R.id.nome_receita);
                nome.setText(jObject.getString("nome"));

                EditText valor = view.findViewById(R.id.txt_valor);
                valor.setText(Utils.getValueJObject(jObject, "valor") != null ? Utils.getValueJObject(jObject, "valor") : "");

                EditText valorRecebido = view.findViewById(R.id.txt_valor_recebido);
                valorRecebido.setText(Utils.getValueJObject(jObject, "valor_recebido") != null ? Utils.getValueJObject(jObject, "valor_recebido") : "");

                TextView conta = view.findViewById(R.id.txt_conta);
                conta.setText(Utils.getValueJObject(jObject, "conta_nome") != null ? Utils.getValueJObject(jObject, "conta_nome") : "");

                TextView categoria = view.findViewById(R.id.txt_categoria);
                categoria.setText(Utils.getValueJObject(jObject, "categoria_receita_nome") != null ? Utils.getValueJObject(jObject, "categoria_receita_nome") : "");

                TextView txtDataVencimento = view.findViewById(R.id.txt_dt_vencimento);
                TextView txtDataPagamento = view.findViewById(R.id.txt_dt_pagamento);

                String data_vencimento = Utils.getDateFromJObject(jObject, "data_vencimento");
                String data_pagamento = Utils.getDateFromJObject(jObject, "data_pagamento");

                if(data_vencimento != null){
                    data_vencimento = Utils.formatData(data_vencimento);
                    txtDataVencimento.setText(data_vencimento);
                }

                if(data_pagamento != null){
                    data_pagamento = Utils.formatData(data_pagamento);
                    txtDataPagamento.setText(data_pagamento);
                }

                TextView situacao = view.findViewById(R.id.txt_selecione_situacao);
                situacao.setText(Utils.getValueJObject(jObject, "id_titulo_status"));

                EditText descricao = view.findViewById(R.id.txt_descricao);
                descricao.setText(Utils.getValueJObject(jObject, "descricao"));
            }

        }
        catch (Exception e){
            Log.e("LoadReceitaTask 73", e.getMessage());
        }
    }

    public JSONObject getJSONObject()
    {
        return this.jObject;
    }
}
