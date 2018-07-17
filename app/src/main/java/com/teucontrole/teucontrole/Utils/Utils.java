package com.teucontrole.teucontrole.Utils;

import android.util.Log;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils
{
    public static String isNullOrEmpty(String word)
    {
        try
        {
            if (word.isEmpty())
                return null;
            else
                return word;

        }
        catch (Exception e )
        {
            throw e;
        }
    }

    public static String DateFormatting(Date date, String format)
    {
        String formatted = null;

        try
        {
            DateFormat dateFormat = new SimpleDateFormat(format);
            formatted = dateFormat.format(date);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return formatted;
        }
    }

    public static String getValueJObject(JSONObject _object, String key) throws Exception
    {
        String value = null;

        try
        {
            if(!existsParam(_object, key))
                return value;

            if(_object.getString(key) != null && !_object.getString(key).contains("null"))
            {
                value = _object.getString(key);
            }
        }
        catch(Exception e)
        {
            throw e;
        }

        return value;
    }

    public static boolean existsParam(JSONObject jObject, String key)
    {
        try
        {
            if(jObject.getString(key) != null)
                return true;
            else
                return false;
        }
        catch (Exception e){
            Log.e("existsParam", e.getMessage());
            return false;
        }
    }

    /*
    only for yyyy-mm-dd format
     */
    public static String getDateFromJObject(JSONObject jsonObject, String key) throws Exception
    {
        String data = null;

        try
        {
            if(!existsParam(jsonObject, key))
                return null;

            data = getValueJObject(jsonObject, key);

            if(data != null)
            {
                data = data.substring(0,10);
            }

        }
        catch (Exception e)
        {
            throw e;
        }

        return data;
    }

    public static String checkStringForExec(String campo)
    {
        String retorno = null;

        try
        {
            if(campo == null || campo.contains("null") || campo.isEmpty())
                retorno = null;
            else
                retorno = "'" + campo + "'";
        }
        catch (Exception e )
        {
            throw e;
        }

        return retorno;
    }

    /*
     yyyy-mm-dd for dd/mm/yyyy
     */
    public static String formatData(String value) throws Exception
    {
        String dataFormatada = null;

        try
        {
            String _mes;

            int ano = Integer.parseInt(value.substring(0,4));
            int mes = Integer.parseInt(value.substring(5,7));
            int dia = Integer.parseInt(value.substring(8,10));

            if(mes < 9 )
                _mes = "0"+mes;
            else
                _mes = String.valueOf(mes);

            dataFormatada = dia + "/" + _mes + "/" + ano;
        }
        catch (Exception e){
            throw e;
        }

        return dataFormatada;
    }

    /*
    dd/mm/yyyy
     */
    public static String formatDataType2(String value) throws Exception
    {
        String dataFormatada = null;

        try
        {
            String _mes;

            int ano = Integer.parseInt(value.substring(0,4));
            int mes = Integer.parseInt(value.substring(5,7));
            int dia = Integer.parseInt(value.substring(8,10));

            if(mes < 9 )
                _mes = "0"+mes;
            else
                _mes = String.valueOf(mes);

            dataFormatada = dia + "/" + _mes + "/" + ano;
        }
        catch (Exception e){
            throw e;
        }

        return dataFormatada;
    }


    public static String genericFormatDate(String data, String requiredFormat) throws Exception
    {
        String dateFormatted = null;

        try
        {
            String ano = null;
            String mes = null;
            String dia = null;

            if(data.contains("-")){
                ano = data.substring(0, 4);
                mes = data.substring(5,7);
                dia = data.substring(8,10);
            }
            else if(data.contains("/")){
                dia = data.substring(0,2);
                mes = data.substring(3,5);
                ano = data.substring(6,10);
            }

            if(requiredFormat.contains("-")){
                dateFormatted = ano + "-" + mes + "-" + dia;
            }
            else if(requiredFormat.contains("/")){
                dateFormatted = dia + "/" + mes + "/" + ano;
            }
        }
        catch (Exception e){
            throw e;
        }

        return dateFormatted;
    }





}
