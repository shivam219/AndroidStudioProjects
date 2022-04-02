package com.project.mytabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewAV extends FragmentPagerAdapter {
    public ViewAV(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch (position) {
            case 0:
                f = new f1();
                break;
            case 1:
                f = new f2();
                break;
        }
        return f;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Weather";
            case 1:
                return "News";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
