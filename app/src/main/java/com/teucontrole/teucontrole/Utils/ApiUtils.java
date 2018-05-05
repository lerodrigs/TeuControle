package com.teucontrole.teucontrole.Utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiUtils
{
    public String InputStreamToString (InputStream inputStream)
    {
        try
        {
            BufferedReader bufferedReader;
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();

        }
        catch (Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }
}
