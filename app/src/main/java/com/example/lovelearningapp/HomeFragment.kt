package com.example.lovelearningapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var tabLayout: TabLayout
    private lateinit var articleRecyclerView: RecyclerView
    private lateinit var topics: List<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        tabLayout = view.findViewById(R.id.tabLayout)
        articleRecyclerView = view.findViewById(R.id.articleRecyclerView)

        // 设置导航栏选项
        topics = listOf("Android编程", "中国历史", "生活技能")
        topics.forEach { topic ->
            val tab = tabLayout.newTab().setText(topic)
            tabLayout.addTab(tab)
            Log.d("项目", tab.text.toString())
        }

        // 模拟文章数据
        val articles = createDummyArticles()
//        Log.d("articles", "articles[中国历史]")
        // 设置文章列表适配器
        val adapter = ArticleAdapter(articles)
        // 设置点击文章时跳转到详情页
        adapter.setOnItemClickListener { article ->
//            Log.d("学号", article.id)
            // 获取文章的id
            val articleId = article.id
            // 创建 Bundle 对象并设置参数
            val bundle = Bundle()
//            Log.d(, "onCreateView: ")
            bundle.putString("articleId", articleId)
            // 导航到文章详情页并传递参数
            findNavController().navigate(R.id.action_navigation_home_to_articleDetailFragment, bundle)
        }
        articleRecyclerView.adapter = adapter
//        Log.d("学号", adapter.itemCount.toString())
        // 设置TabLayout的点击事件监听器
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
//                Log.d("话题", "1234")
                val articles2 = createDummyArticles()


                val selectedTopic = topics[tab.position]
                Log.d("话题", selectedTopic)

                Log.d("话题", topics.toString())
                Log.d("话题", articles2.toString())

                val selectedGroup = articles2[selectedTopic] ?: emptyList()
                Log.d("话题", selectedGroup.isEmpty().toString())

                for (index in selectedGroup.indices) {
                    val item = selectedGroup[index]
                    println("Item at index $index is $item")
//                    Log.d("组别", item.toString())
                }
//                Log.d("组别", selectedGroup)
                adapter.setArticles(selectedGroup,selectedTopic)
                adapter.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return view
    }

    fun createDummyArticles():  MutableMap<String, List<Article>> {
        // 创建一些假的文章数据
        // 这里可以根据你的实际需求，从本地或网络获取文章数据
        val articles = mutableMapOf<String, List<Article>>()

        articles["Android编程"] = listOf(
            Article("1", "Android Article 1", "Content 1", R.drawable.image1, 0),
            Article("2", "Android Article 2", "Content 2", R.drawable.image2, 0),
            Article("3", "Android Article 3", "Content 3", R.drawable.image3, 0)
        )

        articles["中国历史"] = listOf(
            Article("4", "History Article 4", "Content 1", R.drawable.image4, 0),
            Article("5", "History Article 5", "Content 2", R.drawable.image5, 0),
            Article("6", "History Article 6", "Content 3", R.drawable.image6, 0)
        )

        articles["生活技能"] = listOf(
            Article("7", "Lifestyle Article 7", "Content 1", R.drawable.image7, 0),
            Article("8", "Lifestyle Article 8", "Content 2", R.drawable.image8, 0),
            Article("9", "Lifestyle Article 9", "Content 3", R.drawable.image9, 0)
        )

        return articles

    }



}



