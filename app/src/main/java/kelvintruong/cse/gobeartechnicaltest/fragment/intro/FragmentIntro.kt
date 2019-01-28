package kelvintruong.cse.gobeartechnicaltest.fragment.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kelvintruong.cse.gobeartechnicaltest.R

class FragmentIntro : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_intro, container, false)
    }
}