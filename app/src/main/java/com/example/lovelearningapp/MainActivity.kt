package com.example.lovelearningapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


        // 执行删除操作
        db.delete(DatabaseHelper.TABLE_NAME, null, null)

        //创建一个包含文章数据的ContentValues对象
        val values1 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "1")
            put(DatabaseHelper.COLUMN_TITLE, "Android 开发到底是做什么？")
//            put(DatabaseHelper.COLUMN_TOPIC, "安卓编程")
            put(DatabaseHelper.COLUMN_CONTENT, "Android 的能力很复杂，不同分类的 App 会偏重不同的技术点，但基本的功能是相似的，基础的部分我们只谈所有 App 都必须用到的技术，实践部分再根据情况展开。\n" +
                    "\n" +
                    "1. 前提：开发环境和编程语言\n" +
                    "\n" +
                    "Android 开发有唯一官方指定 IDE：Android Studio，Android Studio 支持 Windows、Mac 以及 Linux，所以开发机的选择范围非常广泛，但考虑到编译的时间和开发时的体验，至少要保证 8G 内存。\n" +
                    "\n" +
                    "开发语言可以选择 Java 或 Kotlin，比较推荐在两种语言都会的基础上选择 Kotlin，官方虽然说始终支持 Java，但最近有些扩展库已经基于 Kotlin 特性了…\n" +
                    "\n" +
                    "2. 开发工作涉及的技术\n" +
                    "\n" +
                    "（仅做一个概述，用于理解 Android 开发涉及的知识面，不是完整的总结）\n" +
                    "\n" +
                    "首先是基于前后端的结构，网络是 Android 应用很重要的一部分，Android 开发需要做的是构建请求、处理数据和网络错误、监控网络状态等工作，涉及 Http、Socket 等技术。\n" +
                    "\n" +
                    "跟用户交互是 Android App 的核心，通过代码实现 UI 效果是 Android 开发中占比最大的一部分。Android 的 UI 绘制是由单独一个线程负责的，为了保证流畅交互，所有耗时操作都必须在其他线程完成，所以线程间通信也是 Android 开发不可避免的问题。\n" +
                    "\n" +
                    "再就是近几年需求比较多的音视频和直播相关技术，虽然广大用户（包括我）普遍觉得所有 App 都带着视频和直播是一种打扰和越界，但这也意味着作为开发者不管去哪工作都离不开这些了。\n" +
                    "\n" +
                    "其实还有很多技术无法一一介绍，也有很多我并不了解的技术存在。某著名产品制作人曾经说过，手机是人身体的延伸。只要是 Android 设备（手机、电视、车载等）上存在的功能，就需要相应的开发者将其实现。\n")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image1)    // 从本地加载图片和视频
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video1)
        }
        //插入数据
        try {
            db.insert(DatabaseHelper.TABLE_NAME, null, values1)
            Log.d("学号", "Data inserted into database")
        } catch (e: Exception) {
            Log.e("学号", "Error inserting data into database: ${e.message}")
        }
//        db.insert(DatabaseHelper.TABLE_NAME, null, values1)

        //重复以上步骤插入其他文章
        val values2 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "2")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values2)

        //...
        val values3 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "3")
            put(DatabaseHelper.COLUMN_TITLE, "历史")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image3)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values3)

        val values4 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "4")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史4")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values4)

        val values5 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "5")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史5")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values5)

        val values6 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "6")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史6")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values6)

        val values7 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "7")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史7")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values7)

        val values8 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "8")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史8")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values8)

        val values9 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "9")
            put(DatabaseHelper.COLUMN_TITLE, "发展历史9")
            put(DatabaseHelper.COLUMN_CONTENT, "郑州大学的新闻学教育开始于1976年在中文系开办新闻学专业。1984年郑州大学成立新闻系，1993年获批新闻学硕士点，2000年获批传播学硕士点。2004年6月，郑州大学新闻系改制为新闻与传播学院。2014年12月，河南省委宣传部与郑州大学签约共建新闻与传播学院。2016年学院获批“公共传播”二级学科博士点，2018年获批新闻传播学一级学科博士点，2019年获批设立新闻传播学博士后科研流动站")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values9)
    }
}





