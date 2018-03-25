package com.teucontrole.teucontrole.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskUtils implements TextWatcher
{
    EditText editText;
    String mask;
    String old = "";
    boolean isUpd;
    boolean isMoneyMask;

    public MaskUtils(EditText _editText, String _mask, boolean _isMoneyMask)
    {
        this.editText = _editText;
        this.mask = _mask;
        this.isMoneyMask = _isMoneyMask;
    }

    public static String unMask(String txt)
    {
        try
        {
            return txt.replace("/", "")
                    .replace(".", "")
                    .replace("/", "")
                    .replace("(", "")
                    .replace(")", "");
        }
        catch (Exception e){ return null;}
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String str = unMask(s.toString());
        String mascara = "";

        if(isUpd)
        {
            old = str;
            isUpd = false;
            return;
        }

        if(isMoneyMask)
        {
            if(str.length() < 2)
                mask = "#.##";
            else if (str.length() == 3)
                mask = "#.##";
            else if (str.length() == 4)
                mask = "##.##";
            else if (str.length() == 5)
                mask = "###.##";
            else if (str.length() == 6)
                mask = "#.###.##";
            else if (str.length() == 7)
                mask = "##.###.##";
            else if (str.length() == 8)
                mask = "###.###.##";
            else if (str.length() == 9)
                mask = "#.###.###.##";
            else if (str.length() == 10)
                mask = "##.###.###.##";
            else if (str.length() == 11)
                mask = "###.###.###.##";
        }

        int i =0;

        for (char c : mask.toCharArray())
        {
            try
            {
                if(c != '#' && str.length() > old.length())
                {
                    mascara += c;
                    continue;
                }

                mascara += str.charAt(i);
                i++;
            }
            catch(Exception e ) { break;}
        }

        isUpd = true;
        editText.setText(mascara);
        editText.setSelection(mascara.length());
    }
}
