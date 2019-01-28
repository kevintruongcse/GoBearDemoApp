package kelvintruong.cse.gobeartechnicaltest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class IntroAdapter(var fragmentList: List<androidx.fragment.app.Fragment>, fm: androidx.fragment.app.FragmentManager) :
    androidx.fragment.app.FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): androidx.fragment.app.Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}