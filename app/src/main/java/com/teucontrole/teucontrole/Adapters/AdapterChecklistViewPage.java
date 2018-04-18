package com.teucontrole.teucontrole.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.teucontrole.teucontrole.Fragments.CheckListGeralFragment;
import com.teucontrole.teucontrole.Fragments.CheckListModeloFragment;
import com.teucontrole.teucontrole.Fragments.Check_sList_sFragment;


import java.util.ArrayList;
import java.util.List;

public class AdapterChecklistViewPage extends FragmentStatePagerAdapter
{
    List<Fragment> fragments;
    List<String> titles;

    public AdapterChecklistViewPage(FragmentManager _fragmentManager)
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
            Fragment fragment = null;

            switch (position)
            {
                case 0:
                    fragment = Check_sList_sFragment.newInstance();
                    break;

                case 1:
                    fragment = CheckListGeralFragment.newInstance();
                    break;

                case 2:
                    fragment = CheckListModeloFragment.newInstance();
                    break;
            }

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
                    title = "Checklists";
                    break;

                case 1:
                    title = "Geral";
                    break;

                case 2:
                    title = "Modelos";
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
