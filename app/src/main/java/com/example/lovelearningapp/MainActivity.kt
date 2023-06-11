package com.example.lovelearningapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 事先插入文章数据
        insertInitialData()

        // 找到底部导航栏，获取导航控制器，用于管理导航图和处理 Fragment 之间的导航操作
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // 找到布局文件中的 BottomNavigationView 视图
        // 将 NavController 与 BottomNavigationView 关联起来，使得导航目标与底部导航栏菜单项进行自动同步。
        // 当用户点击底部导航栏的菜单项时，NavController 将根据配置的导航图自动切换到相应的目标 Fragment
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)
    }

    private fun insertInitialData() {
        //获取到一个可写的数据库
        val db = DatabaseHelper(this).writableDatabase

        //创建一个包含文章数据的ContentValues对象
        val values1 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, "anzhuobianc")
            put(DatabaseHelper.COLUMN_TOPIC, "安卓编程")
            put(DatabaseHelper.COLUMN_CONTENT, "Article Content 1")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image1)    // 从本地加载图片和视频
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video1)
        }

        //插入数据
        db.insert(DatabaseHelper.TABLE_NAME, null, values1)

        //重复以上步骤插入其他文章
        val values2 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, "Article Title 2")
            put(DatabaseHelper.COLUMN_CONTENT, "Article Content 2")
            //put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values2)

        //...
    }
}





