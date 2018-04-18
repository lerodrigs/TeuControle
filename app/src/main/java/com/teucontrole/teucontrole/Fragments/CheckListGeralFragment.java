package com.teucontrole.teucontrole.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teucontrole.teucontrole.R;

public class CheckListGeralFragment extends Fragment
{

    public static CheckListGeralFragment newInstance()
    {
        CheckListGeralFragment fragment = new CheckListGeralFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_check_list_geral, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle _bundle)
    {
        super.onViewCreated(view, _bundle);
    }

}
