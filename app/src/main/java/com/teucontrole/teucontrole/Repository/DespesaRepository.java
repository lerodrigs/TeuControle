package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class DespesaRepository
{
    private Context context;
    private MyDbAdapter myDbAdapter;

    public DespesaRepository(Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;
        StringBuilder command = new StringBuilder();

        try
        {
            command.append("INSERT INTO DESPESAS (");
            command.append("ID_DESPESA, ");
            command.append("NOME, ");
            command.append("VALOR, ");
            command.append("VALOR_PAGO, ");
            command.append("DATA_VENCIMENTO, ");
            command.append("DATA_PAGAMENTO, ");
            command.append("ID_PERFIL, ");
            command.append("DATA_CADASTRO, ");
            command.append("DATA_MODIFICACAO, ");
            command.append("ID_CONTA, ");
            command.append("ID_CATEGORIA_DESPESA, ");
            command.append("DESCRICAO, ");
            command.append("ID_DESPESA_RECORRENTE, ");
            command.append("EXCECAO, ");
            command.append("DATA_ORIGINAL, ");
            command.append("ID_TITULO_STATUS, ");
            command.append("CONTA_NOME, ");
            command.append("CATEGORIA_DESPESA_NOME, ");
            command.append("COR, ");
            command.append("ID_RECORRENTE_FREQUENCIA, ");
            command.append("INICIO, ");
            command.append("TERMINO, ");
            command.append("INTERVALO, ");
            command.append("TOTAL_OCORRENCIAS, ");
            command.append("TIPO_FIM, ");
            command.append("ID_FATURA, ");
            command.append("MAE) VALUES ( ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_despesa")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", ");
            command.append(Utils.getValueJObject(jObject, "valor") + ", ");
            command.append(Utils.getValueJObject(jObject, "valor_pago") + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_vencimento")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_pagamento")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_cadastro")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_modificacao")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_categoria_despesa")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "descricao")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_despesa_recorrente")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "excecao")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_original")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_titulo_status")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "conta_nome")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "categoria_despesa_nome")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "cor")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_recorrente_frequencia")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "inicio")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "termino")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "intervalo")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "total_ocorrencias")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "tipo_fim")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_fatura")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "mae")) + "); ");

            result = myDbAdapter.execCommand(command.toString());
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
        StringBuilder command = new StringBuilder();

        try
        {
            command.append("UPDATE DESPESAS SET");
            command.append(" NOME =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", ");
            command.append("VALOR ="+Utils.getValueJObject(jObject, "valor") + ", ");
            command.append("VALOR_PAGO ="+Utils.getValueJObject(jObject, "valor_pago") + ", ");
            command.append("DATA_VENCIMENTO =" +Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_vencimento")) + ", ");
            command.append("DATA_PAGAMENTO = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_pagamento")) + ", " );
            command.append("ID_PERFIL ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil")) + ", ");
            command.append("DATA_CADASTRO ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_cadastro")) + ", ");
            command.append("DATA_MODIFICACAO ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_modificacao")) + ", ");
            command.append("ID_CONTA ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta")) + ", ");
            command.append("ID_CATEGORIA_DESPESA ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_categoria_despesa")) + ", ");
            command.append("DESCRICAO = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "descricao")) + ", ");
            command.append("ID_DESPESA_RECORRENTE = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_despesa_recorrente")) + ", ");
            command.append("EXCECAO ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "excecao")) + ", ");
            command.append("DATA_ORIGINAL= "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_original")) + ", ");
            command.append("ID_TITULO_STATUS ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_titulo_status")) + ", ");
            command.append("CONTA_NOME ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "conta_nome")) + ", ");
            command.append("CATEGORIA_DESPESA_NOME = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "categoria_despesa_nome")) + ", ");
            command.append("COR ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "cor")) + ", ");
            command.append("ID_RECORRENTE_FREQUENCIA ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_recorrente_frequencia")) + ", ");
            command.append("INICIO ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "inicio")) + ", ");
            command.append("TERMINO ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "termino")) + ", ");
            command.append("INTERVALO ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "intervalo")) + ", " );
            command.append("TOTAL_OCORRENCIAS =" +Utils.checkStringForExec(Utils.getValueJObject(jObject, "total_ocorrencias")) + ", ");
            command.append("TIPO_FIM = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "tipo_fim")) + ", ");
            command.append("ID_FATURA = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_fatura")) + ", ");
            command.append("MAE = "+Utils.checkStringForExec(Utils.getValueJObject(jObject, "mae")) + " ");
            command.append("WHERE ID_DESPESA ="+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_despesa"))+"");

            result = myDbAdapter.execCommand(command.toString());
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean delete(String id_despesa) throws Exception
    {
        boolean result = false;
        StringBuilder command = new StringBuilder();

        try
        {
            String query = "DELETE FROM DESPESAS WHERE ID_DESPESA='"+id_despesa+"';";

            result = myDbAdapter.execCommand(command.toString());
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONObject getDespesa(String id_despesa) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            String query = "SELECT * FROM DESPESAS WHERE ID_DESPESA = '" + id_despesa + "';";

            JSONArray jArray = myDbAdapter.get(query);

            if(jArray != null && jArray.length() > 0)
            {
                for(int i=0; i < jArray.length(); i++)
                {
                    jObject = jArray.getJSONObject(i);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }
}
