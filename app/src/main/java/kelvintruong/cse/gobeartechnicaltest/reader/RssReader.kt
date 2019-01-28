package kelvintruong.cse.gobeartechnicaltest.reader

import android.os.AsyncTask
import kelvintruong.cse.gobeartechnicaltest.model.Article
import kelvintruong.cse.gobeartechnicaltest.util.XMLUtil
import java.net.URL

class RssReader(
    private var url: String,
    private var callback: RssCallback
) : AsyncTask<Void, Void, ArrayList<Article>>() {
    override fun doInBackground(vararg params: Void?): ArrayList<Article>? {
        try {
            val inputStream = URL(url).openConnection().getInputStream()
            val document = XMLUtil.getDocumentFromStream(inputStream)
            document?.let {
                return XMLUtil.getArticleList(it)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: ArrayList<Article>?) {
        super.onPostExecute(result)
        if (result != null && !result.isEmpty()) {
            callback.onSuccess(result)
        } else {
            callback.onFail()
        }
    }

    interface RssCallback {
        fun onSuccess(articleList: ArrayList<Article>)
        fun onFail()
    }
}

