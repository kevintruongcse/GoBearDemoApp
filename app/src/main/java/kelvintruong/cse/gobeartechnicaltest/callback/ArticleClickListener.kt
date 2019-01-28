package kelvintruong.cse.gobeartechnicaltest.callback

import kelvintruong.cse.gobeartechnicaltest.model.Article

public interface ArticleClickListener {
    fun onClick(article: Article)
}