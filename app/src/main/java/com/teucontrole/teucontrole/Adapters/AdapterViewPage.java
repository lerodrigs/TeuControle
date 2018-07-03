package com.teucontrole.teucontrole.Adapters;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import com.teucontrole.teucontrole.Fragments.CategoriasFragment;
import com.teucontrole.teucontrole.Fragments.ContasCartoesFragment;
import com.teucontrole.teucontrole.Fragments.DespesasFragment;
import com.teucontrole.teucontrole.Fragments.FaturasFragment;
import com.teucontrole.teucontrole.Fragments.ReceitasFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPage extends FragmentStatePagerAdapter
{
    List<Fragment> fragments;
    List<String> titles;

    public AdapterViewPage(FragmentManager _fragmentManager)
    {
        super(_fragmentManager);

        this.fragments = new ArrayList<Fragment>();
        this.titles = new ArrayList<String>();
    }

    @Override
    public Fragment getItem(int position)
    {
        try
        {
            Fragment fragment = this.fragments.get(position);
            return fragment;
        }
        catch (Exception e )
        {
            return null;
        }
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        try
        {
            String title = "";

            switch(position)
            {
                case 0:
                    title = "Receitas";
                    break;

                case 1:
                    title = "Despesas";
                    break;

                case 2:
                    title = "Contas e Cartões";
                    break;

                case 3:
                    title = "Categorias";
                    break;

                case 4:
                    title = "Faturas";
                    break;
            }

            return title;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void addFragment(Fragment fragment, String title)
    {
        fragments.add(fragment);
        titles.add(title);
    }

}
