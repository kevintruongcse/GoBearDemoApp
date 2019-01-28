package kelvintruong.cse.gobeartechnicaltest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kelvintruong.cse.gobeartechnicaltest.adapter.ArticleAdapter
import kelvintruong.cse.gobeartechnicaltest.callback.ArticleClickListener
import kelvintruong.cse.gobeartechnicaltest.model.Article
import kelvintruong.cse.gobeartechnicaltest.reader.RssReader
import kelvintruong.cse.gobeartechnicaltest.util.SharePrefUtils
import kotlinx.android.synthetic.main.activity_article_list.*

class ArticleListActivity : AppCompatActivity(), RssReader.RssCallback, SwipeRefreshLayout.OnRefreshListener,
    ArticleClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        swipeRefreshLayout.setOnRefreshListener(this)

        recyclerViewArticle.let {
            it.visibility = View.VISIBLE
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = ArticleAdapter(ArrayList(), this)
        }

        btnLogout.setOnClickListener { logout() }

        loadRss()
    }

    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

        SharePrefUtils.clearAll(this)
    }

    private fun loadRss() {
        RssReader(getString(R.string.rss_host), this).execute(null as Void?)
    }

    private fun clearData() {
        (recyclerViewArticle.adapter as ArticleAdapter).clear()
    }

    override fun onSuccess(articleList: ArrayList<Article>) {
        swipeRefreshLayout.isRefreshing = false
        if (articleList.isNotEmpty()) {
            (recyclerViewArticle.adapter as ArticleAdapter).add(articleList)
            txtNoData.visibility = View.GONE
            recyclerViewArticle.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.VISIBLE
            recyclerViewArticle.visibility = View.GONE
        }
    }

    override fun onFail() {
        swipeRefreshLayout.isRefreshing = false
        txtNoData.visibility = View.VISIBLE
        recyclerViewArticle.visibility = View.GONE
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        clearData()
        loadRss()
    }

    override fun onClick(article: Article) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        val bundle = Bundle().apply {
            putString(ArticleDetailActivity.LINK, article.link)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
