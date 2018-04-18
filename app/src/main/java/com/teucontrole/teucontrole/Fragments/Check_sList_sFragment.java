package com.teucontrole.teucontrole.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teucontrole.teucontrole.R;

public class Check_sList_sFragment extends Fragment
{

    public static Check_sList_sFragment newInstance()
    {
        Check_sList_sFragment fragment = new Check_sList_sFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_check_s_list_s, container, false);
    }

    public static void addNewChecklist()
    {
        try
        {

        }
        catch (Exception e){}
    }
}
