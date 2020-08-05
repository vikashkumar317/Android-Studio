package com.example.learntablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("TAG","onCreateView");
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = mView.findViewById(R.id.tabs);
        viewPager = mView.findViewById(R.id.viewPager);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAG","onActivityCreated");
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcon();
    }

    private void setUpIcon(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cake);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_alarm);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_call);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_fingerprint);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new FirstFragment(), "BirthDay");
        viewPagerAdapter.addFragment(new SecondFragment(), "Alarm");
        viewPagerAdapter.addFragment(new ThirdFragment(), "Camera");
        viewPagerAdapter.addFragment(new FourthFragment(), "Call");
        viewPagerAdapter.addFragment(new FifthFragment(), "FingerPrint");
        viewPager.setAdapter(viewPagerAdapter);
    }
}