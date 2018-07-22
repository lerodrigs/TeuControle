package com.teucontrole.teucontrole.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.Utils;

import java.util.Calendar;


public class CustomDatePickerFragment extends DialogFragment
{
    private String dataEscolhida;
    private String tag;

    public CustomDatePickerFragment()
    {
        this.dataEscolhida = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Bundle _bundle = getArguments();

        if(_bundle != null)
            this.tag = _bundle.getString("tag");
        else
            this.tag = null;

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            dataEscolhida = year + "-" + month + "-" + day;

            try
            {
                switch (tag){

                    case "MainActivity_calendario":
                        break;

                    case "AdicionarReceitasActivity_dataVencimento":
                        break;

                    case "AdicionarReceitasActivity_dataPagamento":
                        break;
                }
            }
            catch(Exception e){

            }
        }
    };

}
