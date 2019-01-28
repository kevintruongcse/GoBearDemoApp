package kelvintruong.cse.gobeartechnicaltest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kelvintruong.cse.gobeartechnicaltest.util.SharePrefUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent = Intent(this, IntroActivity::class.java)
        val isLaunched = SharePrefUtils.getBoolean(this, SharePrefUtils.Key.LAUNCHED)
        if (isLaunched) {
            val isRemember = SharePrefUtils.getBoolean(this, SharePrefUtils.Key.REMEMBER)
            intent = when {
                isRemember -> Intent(this, ArticleListActivity::class.java)
                else -> Intent(this, LoginActivity::class.java)
            }
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}