package com.teucontrole.teucontrole.Repository;

import android.content.Context;
import android.icu.util.UniversalTimeScale;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContaRepository
{
    private Context context;
    private MyDbAdapter myDbAdapter;

    public ContaRepository(Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public JSONObject getConta(String id_conta) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM CONTAS_BANCARIAS WHERE id_conta = '" + id_conta + "';");

            JSONArray jarray = myDbAdapter.get(strBuilder.toString());

            if(jarray != null && jarray.length() > 0)
            {
                for(int i=0; i < jarray.length(); i++)
                {
                    jObject = jarray.getJSONObject(i);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;
        int ativa = 0;
        StringBuilder command = new StringBuilder();

        try
        {
            if(jObject.getBoolean("ativa"))
                ativa = 1;
            else
                ativa = 0;

            command.append("INSERT INTO CONTAS_BANCARIAS (");
            command.append("ID_CONTA, ");
            command.append("NOME, ");
            command.append("ID_USUARIO_CRIADOR, ");
            command.append("ID_PERFIL, ");
            command.append("SALDO, ");
            command.append("ATIVA, ");
            command.append("ID_CONTA_TIPO, ");
            command.append("ID_CONTA_BANCARIA_TIPO, ");
            command.append("NOME1, ");
            command.append("NOME2, ");
            command.append("DIA_FECHAMENTO, ");
            command.append("DIA_VENCIMENTO, ");
            command.append("LIMITE, ");
            command.append("ID_BANDEIRA ) VALUES (");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_usuario_criador")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "saldo")) + ", ");
            command.append(ativa + ", ");
            command.append(Utils.getValueJObject(jObject, "id_conta_tipo") + ", ");
            command.append(Utils.getValueJObject(jObject, "id_conta_bancaria_tipo") + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome1")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome2")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "dia_fechamento")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "dia_fechamento")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "limite")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_bandeira")) + "); ");


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
        int ativa =0;
        StringBuilder command = new StringBuilder();

        try
        {
            if(jObject.getBoolean("ativa"))
                ativa = 1;
            else
                ativa = 0;

            command.append("UPDATE CONTAS_BANCARIAS ");
            command.append("SET NOME = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", ");
            command.append("ID_USUARIO_CRIADOR = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_usuario_criador")) + ", ");
            command.append("ID_PERFIL = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil")) + ", ");
            command.append("SALDO = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "saldo")) + ", ");
            command.append("ATIVA = " + ativa + ", ");
            command.append("ID_CONTA_TIPO = " + Utils.getValueJObject(jObject, "id_conta_tipo") + ", ");
            command.append("ID_CONTA_BANCARIA_TIPO  = " + Utils.getValueJObject(jObject, "id_conta_bancaria_tipo") + ", ");
            command.append("NOME1  = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome1")) + ", ");
            command.append("NOME2  = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome2")) + ", ");
            command.append("DIA_FECHAMENTO =" + Utils.getValueJObject(jObject, "dia_fechamento") + ", ");
            command.append("DIA_VENCIMENTO =" + Utils.getValueJObject(jObject, "dia_vencimento") + ", ");
            command.append("LIMITE = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "limite")) + ", ");
            command.append("ID_BANDEIRA = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_bandeira")) + " ");
            command.append("WHERE id_conta =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta"))+ ";");

            result = myDbAdapter.execCommand(command.toString());
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
            String command = "DELETE FROM CONTAS_BANCARIAS WHERE ID_CONTA =" + Utils.checkStringForExec(jObject.getString("id_conta")) + ";";
            result = myDbAdapter.execCommand(command);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONArray getByIdPerfil(String _idPerfil) throws Exception
    {
        JSONArray items = null;

        try
        {
            items = myDbAdapter.get("SELECT * FROM CONTAS_BANCARIAS WHERE ID_PERFIL = '"+_idPerfil+"';");
        }
        catch (Exception e){
            throw e;
        }

        return items;
    }
}

