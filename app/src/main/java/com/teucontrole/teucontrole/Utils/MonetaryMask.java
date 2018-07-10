package com.teucontrole.teucontrole.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.teucontrole.teucontrole.R;

import java.text.NumberFormat;

public class MonetaryMask implements TextWatcher
{
    private boolean isUpdating;
    private EditText mEditText;
    private NumberFormat mNF;

    public MonetaryMask(EditText mEditText)
    {
        this.mEditText = mEditText;
        this.mNF= NumberFormat.getCurrencyInstance();
        this.isUpdating = false;
    }

    @Override
    public void beforeTextChanged(CharSequence cs, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence cs, int start, int before, int count)
    {

        if(isUpdating)
        {
            isUpdating = false;
            return;
        }

        String str = cs.toString();
        boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str.indexOf(".") > -1 || str.indexOf(",") > -1));

        if(hasMask)
            str = str.replaceAll("[R$]", "").replaceAll("[,]", "").replaceAll("[.]", "");

        try
        {
            str = mNF.format(Double.parseDouble(str) / 100);
            this.mEditText.setText(str);
            this.mEditText.setSelection(this.mEditText.getText().length());
        }
        catch (Exception e){
            e.printStackTrace();
            cs = "";
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

}