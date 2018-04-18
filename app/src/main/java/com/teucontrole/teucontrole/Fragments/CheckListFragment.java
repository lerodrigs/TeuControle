package com.teucontrole.teucontrole.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.teucontrole.teucontrole.Actitivies.MainActivity;
import com.teucontrole.teucontrole.Adapters.AdapterChecklistViewPage;
import com.teucontrole.teucontrole.Adapters.AdapterViewPage;
import com.teucontrole.teucontrole.R;

public class CheckListFragment extends Fragment
{
    TabLayout tabLayout;
    ViewPager viewPager;

    public static CheckListFragment newInstance()
    {
        CheckListFragment fragment = new CheckListFragment();
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
        return inflater.inflate(R.layout.fragment_check_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle _bundle)
    {
        super.onViewCreated(view, _bundle);

        MainActivity.setToolbar("Checklist");

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);

        setupViewPager(getFragmentManager());

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(tabSelectedListener);
    }

    public void setupViewPager(FragmentManager fragmentManager)
    {
        AdapterChecklistViewPage adapterViewPage = new AdapterChecklistViewPage(fragmentManager);

        adapterViewPage.addFragment(Check_sList_sFragment.newInstance(), "CheckLists");
        adapterViewPage.addFragment(CheckListGeralFragment.newInstance(), "Geral");
        adapterViewPage.addFragment(CheckListModeloFragment.newInstance(), "Modelos");

        viewPager.setAdapter(adapterViewPage);
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener()
    {
        @Override
        public void onTabSelected(TabLayout.Tab tab)
        {
            int position = tab.getPosition();
            viewPager.setCurrentItem(position, true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}
