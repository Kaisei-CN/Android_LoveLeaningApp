package com.example.lovelearningapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var tabLayout: TabLayout
    private lateinit var articleRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        tabLayout = view.findViewById(R.id.tabLayout)
        articleRecyclerView = view.findViewById(R.id.articleRecyclerView)

        // 设置导航栏选项
        val topics = listOf("Topic 1", "Topic 2", "Topic 3")
        topics.forEach { topic ->
            val tab = tabLayout.newTab().setText(topic)
            tabLayout.addTab(tab)
        }

        // 模拟文章数据
        val articles = createDummyArticles()

        // 设置文章列表适配器
        val adapter = ArticleAdapter(articles)
        articleRecyclerView.adapter = adapter

        return view
    }

    fun createDummyArticles(): List<Article> {
        // 创建一些假的文章数据
        // 这里可以根据你的实际需求，从本地或网络获取文章数据
        return listOf(
            Article("1", "Article 1", "Content 1", R.drawable.image1, 0),
            Article("2", "Article 2", "Content 2", R.drawable.image2, 0),
//            Article("3", "Article 3", "Content 3", R.drawable.image3, R.drawable.video3),
            // 添加更多文章...
        )
    }
}



