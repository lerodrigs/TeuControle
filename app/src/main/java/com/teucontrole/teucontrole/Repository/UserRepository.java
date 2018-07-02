package com.teucontrole.teucontrole.Repository;

import android.content.Context;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserRepository
{
    private MyDbAdapter myDbAdapter;
    private Context context;
    private UserPreferences userPreferences;

    public UserRepository(Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
        this.userPreferences = new UserPreferences(context);
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean ok = false;

        try
        {
            String data_nascimento = Utils.getDateFromJObject(jObject, "data_nascimento");
            String validade_assinatura = Utils.getDateFromJObject(jObject, "validade_assinatura");

            String command = "INSERT INTO USUARIOS (" +
                    "ID_USUARIO, "  +
                    "NOME, "  +
                    "SEXO, " +
                    "ID_ESTADO, " +
                    "ID_CIDADE, " +
                    "DATA_NASCIMENTO, " +
                    "EMAIL, " +
                    "VALIDADE_ASSINATURA)" +
                    " VALUES (" +
                    ""+Utils.getValueJObject(jObject, "id_usuario") + "," +
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", " +
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject, "sexo")) + ", "+
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_estado")) + ", "+
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject,"id_cidade"))+ ", "+
                    ""+Utils.checkStringForExec(data_nascimento)+ ", "+
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject, "email"))+ ", "+
                    ""+Utils.checkStringForExec(validade_assinatura)+ "); ";

            ok = myDbAdapter.execCommand(command);
        }
        catch (Exception e)
        {
            throw e;
        }

        return ok;
    }

    public boolean update(JSONObject jObject) throws Exception
    {
        boolean ok = false;

        try
        {
            String dataNascimento = Utils.getDateFromJObject(jObject, "data_nascimento");
            String validadeAssinatura = Utils.getDateFromJObject(jObject, "validade_assinatura");

            String command = "UPDATE USUARIOS " +
                             "SET NOME = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome"))+ ", " +
                             "SEXO = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "sexo"))+ ", " +
                             "ID_ESTADO = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_estado"))+", " +
                             "ID_CIDADE = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_cidade")) + ", " +
                             "DATA_NASCIMENTO =" + Utils.checkStringForExec(dataNascimento) + ", " +
                             "VALIDADE_ASSINATURA = " +Utils.checkStringForExec(validadeAssinatura)+ " " +
                             "WHERE EMAIL = " +Utils.checkStringForExec(Utils.getValueJObject(jObject, "email")) + ";";

            ok = myDbAdapter.execCommand(command);
        }
        catch (Exception e)
        {
            throw e;
        }

        return ok;
    }

    public boolean delete(int id_user)
    {
        boolean ok = false;

        try
        {
            String query = "DELETE FROM USUARIOS WHERE ID_USUARIO = "+id_user+";";
            ok = myDbAdapter.execCommand(query);
        }
        catch (Exception e)
        {
            throw e;
        }

        return ok;
    }

    public JSONObject getByEmail(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            JSONArray jArray = new JSONArray();

            String query = "SELECT * FROM USUARIOS WHERE EMAIL = '"+email+"'";
            jArray = myDbAdapter.get(query);

            if(jArray.length() > 0)
            {
                jObject = jArray.getJSONObject(0);
            }
        }
        catch (Exception e )
        {
            throw e;
        }

        return jObject;
    }

    public JSONObject getFromUserLogged() throws Exception
    {
        JSONObject jObject = null;

        try
        {
            JSONArray jArray = new JSONArray();
            String email = userPreferences.get("email");

            jArray = myDbAdapter.get("SELECT email, nome, id_usuario FROM USUARIOS WHERE EMAIL = '"+email+"'");

            if(jArray.length() > 0)
            {
                jObject = jArray.getJSONObject(0);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }
}
