package ir.piosis.piosismart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import ir.piosis.piosismart.fragment.BlankFragment;
import ir.piosis.piosismart.fragment.BlankFragment2;
import ir.piosis.piosismart.fragment.BlankFragment3;

/**
 * Created by PIOSISLP on 6/21/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(BlankFragment.newInstance("param1","param2"));
        fragments.add(BlankFragment2.newInstance("param1","param2"));
        fragments.add(BlankFragment3.newInstance("param1","param2"));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }
}
