package kelvintruong.cse.gobeartechnicaltest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kelvintruong.cse.gobeartechnicaltest.adapter.IntroAdapter
import kelvintruong.cse.gobeartechnicaltest.fragment.intro.FragmentIntro
import kelvintruong.cse.gobeartechnicaltest.util.SharePrefUtils
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initSlider()

        txtSkip.setOnClickListener { moveToLogin() }
        btnDone.setOnClickListener { moveToLogin() }
    }

    private fun initSlider() {
        val fragmentList =
            listOf<Fragment>(FragmentIntro(), FragmentIntro(), FragmentIntro())

        val introAdapter = IntroAdapter(fragmentList, supportFragmentManager)
        viewPager.adapter = introAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    introAdapter.count - 1 -> {
                        txtSkip.visibility = View.GONE
                        btnDone.visibility = View.VISIBLE
                    }
                    else -> {
                        txtSkip.visibility = View.VISIBLE
                        btnDone.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        SharePrefUtils.setBoolean(this, SharePrefUtils.Key.LAUNCHED, true)
    }
}