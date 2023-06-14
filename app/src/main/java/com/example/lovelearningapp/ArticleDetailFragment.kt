package com.example.lovelearningapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class ArticleDetailFragment : Fragment() {

    private lateinit var articleId: String
    private lateinit var articleTitleTextView: TextView
    private lateinit var articleContentTextView: TextView
    private lateinit var articleImageView: ImageView
    private lateinit var viewModel: ArticleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 获取从上一个页面传递过来的文章 ID
        articleId = arguments?.getString("articleId") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = viewModels<ArticleViewModel>().value

        articleTitleTextView = view.findViewById(R.id.articleTitleTextView)
        articleContentTextView = view.findViewById(R.id.articleContentTextView)
        articleImageView = view.findViewById(R.id.articleImageView)

        // 根据文章 ID 查询数据库获取文章内容
        val article = getArticleFromDatabase(articleId)

        article?.let {
            articleTitleTextView.text = it.title
            articleContentTextView.text = it.content
            articleImageView.setImageResource(it.imageUrl)
        }

        // Start a timer when the fragment is resumed
        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Do nothing
            }

            override fun onFinish() {
                // Add points to the user when the timer finishes
                viewModel.addUserPoints(5)
            }
        }
        timer.start()
    }

    @SuppressLint("Range")
    private fun getArticleFromDatabase(articleId: String): Article? {
        // 根据文章 ID 从数据库查询文章内容
        // 这里可以使用 DatabaseHelper 或其他数据库操作类来实现
        // 返回查询到的文章对象
        val databaseHelper = DatabaseHelper(requireContext())
        // 获取可读取的数据库实例
        val database = databaseHelper.readableDatabase

        // 定义查询语句
        val query = "SELECT * FROM ${DatabaseHelper.TABLE_NAME}"

        // 执行查询操作
        val cursor = database.rawQuery(query, null)

        // 遍历查询结果
        while (cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTENT))
            val imageResId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_RES_ID))
            val videoResId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_VIDEO_RES_ID))

            // 打印查询结果
            Log.d("Article", "ID: $id, Title: $title, Content: $content, ImageResId: $imageResId, VideoResId: $videoResId")
        }

        // 关闭 cursor 和 database 对象
        cursor.close()
        database.close()

        return databaseHelper.getArticle(articleId)
    }



//    override fun onResume() {
//        super.onResume()
//
//
//    }
}