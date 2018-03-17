package com.teucontrole.teucontrole.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.teucontrole.teucontrole.Adapters.AdapterViewPage;
import com.teucontrole.teucontrole.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LancamentosFragment extends Fragment
{
    ViewPager viewPager;
    TabLayout tabLayout;

    public static LancamentosFragment NewInstance()
    {
        LancamentosFragment fragment = new LancamentosFragment();
        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        try
        {
            return layoutInflater.inflate(R.layout.fragment_lancamentos, viewGroup, false);
        }
        catch (Exception e )
        {
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        try
        {
            tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
            viewPager = (ViewPager) view.findViewById(R.id.pager);

            setupViewPager(viewPager, getFragmentManager());

            tabLayout.setupWithViewPager(viewPager);

        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    private void setupViewPager(ViewPager viewPager, FragmentManager fragmentManager)
    {
        AdapterViewPage adapter = new AdapterViewPage(fragmentManager);

        adapter.addFragment(ReceitasFragment.NewInstance(), "Receitas");
        adapter.addFragment(DespesasFragment.NewInstance(), "Despesas");
        adapter.addFragment(ContasCartoesFragment.NewInstance(), "Contas e Cartões");
        adapter.addFragment(FaturasFragment.NewInstance(), "Faturas");

        viewPager.setAdapter(adapter);
    }
}