package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReceitaRepository
{
    private Context context;
    private MyDbAdapter myDbAdapter;

    public ReceitaRepository(Context _context)
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
            command.append("INSERT INTO RECEITAS (");
            command.append("ID_RECEITA, ");
            command.append("NOME, ");
            command.append("VALOR, ");
            command.append("VALOR_RECEBIDO, ");
            command.append("DATA_VENCIMENTO, ");
            command.append("DATA_PAGAMENTO, ");
            command.append("ID_PERFIL, ");
            command.append("DATA_CADASTRO, ");
            command.append("DATA_MODIFICACAO, ");
            command.append("ID_CONTA, ");
            command.append("ID_CATEGORIA_RECEITA, ");
            command.append("DESCRICAO, ");
            command.append("ID_RECEITA_RECORRENTE, ");
            command.append("EXCECAO, ");
            command.append("DATA_ORIGINAL, ");
            command.append("ID_TITULO_STATUS, ");
            command.append("CONTA_NOME, ");
            command.append("CATEGORIA_RECEITA_NOME, ");
            command.append("COR, ");
            command.append("ID_RECORRENTE_FREQUENCIA, ");
            command.append("INICIO, ");
            command.append("TERMINO, ");
            command.append("INTERVALO, ");
            command.append("TOTAL_OCORRENCIAS, ");
            command.append("TIPO_FIM ) VALUES (");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_receita")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", ");
            command.append(Utils.getValueJObject(jObject, "valor") + ", ");
            command.append(Utils.getValueJObject(jObject, "valor_recebido") + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_vencimento")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_modificacao")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta")) + ", ");
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

        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean delete(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            String query = "DELETE FROM RECEITAS WHERE ID_RECEITA='"+jObject.getString("id_receita")+"';";

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
            String query = "SELECT * FROM RECEITAS WHERE ID_RECEITA = '" + id_receita + "';";

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
