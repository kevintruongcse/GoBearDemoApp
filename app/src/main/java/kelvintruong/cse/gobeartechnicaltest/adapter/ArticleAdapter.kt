package kelvintruong.cse.gobeartechnicaltest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kelvintruong.cse.gobeartechnicaltest.R
import kelvintruong.cse.gobeartechnicaltest.callback.ArticleClickListener
import kelvintruong.cse.gobeartechnicaltest.model.Article
import kotlinx.android.synthetic.main.article_list_item.view.*

class ArticleAdapter(
    private var articleList: ArrayList<Article>,
    private var listener: ArticleClickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articleList[position])
        holder.itemView.setOnClickListener { listener.onClick(articleList[position]) }
    }

    fun clear() {
        articleList.clear()
        notifyDataSetChanged()
    }

    fun add(articleList: ArrayList<Article>) {
        this.articleList.addAll(articleList)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            itemView.txtTitle.text = article.title
            itemView.txtPublishDate.text = article.pubDate
            itemView.txtDescription.text = article.desciption

            if (!article.imgUrl.isEmpty()) {
                Glide.with(itemView.context)
                    .load(article.imgUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .into(itemView.imgThumbnail)
            }
        }
    }
}
