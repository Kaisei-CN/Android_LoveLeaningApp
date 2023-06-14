package com.example.lovelearningapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val articles:  MutableMap<String, List<Article>>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var onItemClickListener: ((Article) -> Unit)? = null

    // 创建 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    // 绑定数据到 ViewHolder
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles.values.flatten()[position]
        Log.d("选号", article.id)
        holder.bind(article)

        // 设置文章的标题和内容到视图元素
        holder.titleTextView.text = article.title
        holder.descriptionTextView.text = article.content

        // 设置点击事件监听器
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(article)
        }
    }

    // 返回数据项的数量
    override fun getItemCount(): Int {
        return articles.values.flatten().size
    }

    fun setArticles(newArticles: List<Article>,topics: String) {
        articles.clear()
//        articles.putAll(newArticles)
//        articles["中国历史"] = newArticles
        // 更新每个话题的文章
        articles[topics] = newArticles
        notifyDataSetChanged()
        Log.d("历史", topics)
        Log.d("历史", articles.toString())

    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        public val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        public val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        public val imageView: ImageView = itemView.findViewById(R.id.imageView)

        // 将数据绑定到视图组件
        fun bind(article: Article) {
            titleTextView.text = article.title
            descriptionTextView.text = article.content
            imageView.setImageResource(article.imageUrl)

            itemView.setOnClickListener {
                onItemClickListener?.invoke(article)
            }
        }
    }
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}
