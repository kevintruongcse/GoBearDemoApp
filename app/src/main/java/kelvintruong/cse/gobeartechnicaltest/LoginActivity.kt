package kelvintruong.cse.gobeartechnicaltest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kelvintruong.cse.gobeartechnicaltest.util.SharePrefUtils
import kelvintruong.cse.gobeartechnicaltest.util.UserUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val msgLoginError: String by lazy { getString(R.string.login_error) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            val userName = edtUserName.text.toString()
            val pwd = edtPassword.text.toString()
            val isRemember = swtRemember.isChecked

            if (UserUtil.valid(userName, pwd)) {
                SharePrefUtils.setBoolean(this, SharePrefUtils.Key.REMEMBER, isRemember)
                moveToArticleListScreen()
            } else {
                Toast.makeText(this, msgLoginError, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun moveToArticleListScreen() {
        val intent = Intent(this, ArticleListActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}
