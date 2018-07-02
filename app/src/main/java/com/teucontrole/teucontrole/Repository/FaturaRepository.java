package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FaturaRepository
{
    private Context context;
    private MyDbAdapter myDbAdapter;

    public FaturaRepository(Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public JSONArray getList(String dataFormatada, String id_perfil) throws Exception
    {
        JSONArray jArray = null;
        StringBuilder query = new StringBuilder();

        try
        {
            query.append("SELECT *");
            query.append("  FROM FATURAS A ");
            query.append(" WHERE 1=1");
            query.append("   AND A.ID_PERFIL ='"+id_perfil+"'");
            //query.append(" AND strftime('%m', A.DATA_VENCIMENTO) = strftime('%m', '"+dataFormatada+"')");

            jArray = myDbAdapter.get(query.toString());
        }
        catch (Exception e){
            throw e;
        }

        return jArray;
    }

    public JSONObject getFatura(String id_fatura) throws Exception
    {
        JSONObject jsonObject = null;

        try
        {
            String query = "SELECT * FROM FATURAS WHERE ID_FATURA ='"+id_fatura+"';";
            JSONArray jArray = myDbAdapter.get(query);

            if(jArray != null && jArray.length() > 0)
            {
                jsonObject= jArray.getJSONObject(0);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jsonObject;
    }

    public JSONArray getFaturas(String id_perfil, Date data) throws Exception
    {
        JSONArray jArray = null;
        StringBuilder query = new StringBuilder();

        try
        {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
            String dateFormatted = dateFormat.format(data);

            query.append("SELECT * ");
            query.append("  FROM FATURAS ");
            query.append(" WHERE ID_FATURA = '"+id_perfil + "'");
            query.append("   AND DATA_FECHAMENTO = '" + dateFormatted +"';");

            jArray = myDbAdapter.get(query.toString());

        }
        catch (Exception e)
        {
            throw  e;
        }

        return jArray;
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;
        StringBuilder command = new StringBuilder();

        try
        {
            command.append("INSERT INTO FATURAS (");
            command.append("ID_FATURA, ");
            command.append("ID_PERFIL, ");
            command.append("DATA_VENCIMENTO, ");
            command.append("DATA_FECHAMENTO, ");
            command.append("VALOR_TOTAL, ");
            command.append("VALOR_PAGO, ");
            command.append("DATA_PAGAMENTO, ");
            command.append("ID_CONTA, ");
            command.append("DATA_CADASTRO, ");
            command.append("DATA_MODIFICACAO, ");
            command.append("ID_CONTA_QUE_PAGA, ");
            command.append("ID_TITULO_STATUS ( ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_fatura"))+ ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil"))+ ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_vencimento"))+ ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_fechamento"))+ ", ");
            command.append(Utils.getValueJObject(jObject, "valor_total")+ ", ");
            command.append(Utils.getValueJObject(jObject, "valor_pago") + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_pagamento")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_cadastro"))+ ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_modificacao")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta_que_paga")) + ", ");
            command.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_titulo_status")) + ");");

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
            command.append("UPDATE FATURAS SET ");
            command.append("ID_PERFIL =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil")) + ", ");
            command.append("DATA_VENCIMENTO =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_vencimento")) + ", ");
            command.append("DATA_FECHAMENTO =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_fechamento")) + ", ");
            command.append("VALOR_TOTAL =" + Utils.getValueJObject(jObject, "valor_total") + ", ");
            command.append("VALOR_PAGO =" + Utils.getValueJObject(jObject, "valor_pago") + ", ");
            command.append("DATA_PAGAMENTO =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_pagamento")) + ", ");
            command.append("ID_CONTA =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_conta")) + ", ");
            command.append("DATA_CADASTRO =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_cadastro")) + ", ");
            command.append("DATA_MODIFICACAO =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "data_modificacao")) + ", ");
            command.append("ID_TITULO_STATUS =" + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_titulo_status")) + " ");
            command.append("WHERE ID_FATURA = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_fatura")));

            result = myDbAdapter.execCommand(command.toString());
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean delete(String id_fatura) throws Exception
    {
        boolean result = false;

        try
        {
            String command = "DELETE FROM FATURAS WHERE ID_FATURA = '"+id_fatura+"';";
            result = myDbAdapter.execCommand(command);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }
}
