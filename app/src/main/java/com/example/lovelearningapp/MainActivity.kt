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
            put(DatabaseHelper.COLUMN_TITLE, "Android开发书籍推荐")
            put(DatabaseHelper.COLUMN_CONTENT, "很多时候我们都会不断收到新手的提问“Android开发的经典入门教材和学习路线？”、“Android 开发入门教程有哪些推荐？”等类似的问题，我们不断重复回答这些问题，这让我们萌生了做《 Android开发书籍推荐：从入门到精通系列学习路线书籍介绍》的想法，整理收集开发大牛的学习经验，以便让我们少走弯路，更快速成长。希望这个系列可以成为大家手头应对新手的好答案。")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image2)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values2)

        //...
        val values3 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "3")
            put(DatabaseHelper.COLUMN_TITLE, "学习Android网站推荐")
            put(DatabaseHelper.COLUMN_CONTENT, "1. Android Developers\n" +
                    "作为一个Android开发者，官网的资料当然不可错过，从设计，培训，指南，文档，都不应该错过，在以后的学习过程中慢慢理解体会。\n" +
                    "2. Android Guides - CodePath\n" + "CodePath是国外一个技术培训机构，主要培训iOS 和Android开发，而CodePath将Android Guides放在Github，已经获得了4000+个赞，对于Android初学这特别适合，而且浅显易懂。\n" )
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image3)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values3)

        val values4 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "4")
            put(DatabaseHelper.COLUMN_TITLE, "南昌起义")
            put(DatabaseHelper.COLUMN_CONTENT, "八一南昌起义常简称南昌起义或者八一起义，指在1927年8月1日中共联合 国民党左派 ，打响了武装反抗 国民党反动派 的第一枪，揭开了中国共产党独立领导武装斗争和创建革命军队的序幕。 [1] 起义由 周恩来 、 贺龙 、 叶挺 、 朱德 、 刘伯承 、 谭平山 领导。 1933年7月11日， 中华苏维埃共和国临时中央政府 根据 中央革命军事委员会 6月30日的建议，决定8月1日为 中国工农红军 成立纪念日。 从此，8月1日成为中国工农红军和后来的 中国人民解放军 的 建军节 。 1927年 8月1日2时，在周恩来、贺龙、叶挺、朱德、刘伯承的领导下，南昌起义开始。")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image4)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values4)

        val values5 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "5")
            put(DatabaseHelper.COLUMN_TITLE, "晋朝历史")
            put(DatabaseHelper.COLUMN_CONTENT, "晋朝（265年－420年），分为西晋与东晋两个时期，西晋为中国历史上九个大一统王朝之一，两晋上承三国下启南北朝，其中东晋属于六朝之一。. 265年司马炎篡魏，建立晋朝，定都洛阳，史称西晋，280年灭东吴，完成统一，后经历“八王之乱”。. 晋愍帝迁都长安，316年西晋灭亡，史称“五胡乱华”。. 317年，晋室南渡，司马睿在建邺建立东晋，东晋曾多次北伐。.")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image5)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values5)

        val values6 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "6")
            put(DatabaseHelper.COLUMN_TITLE, "故宫历史")
            put(DatabaseHelper.COLUMN_CONTENT, "故宫始建于1406年明永乐四年，建成于1420年明永乐十八年。 在故宫六百多年的发展中，经历了三个重要的历史阶段，分别是：第一阶段，明成祖朱棣营建故宫，至1644年，为明皇宫；第二阶段，清顺治十四年至1924年为清皇宫；第三阶段，1925年10月至今，为故宫博物院。")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image6)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values6)

        val values7 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "7")
            put(DatabaseHelper.COLUMN_TITLE, "职场技能")
            put(DatabaseHelper.COLUMN_CONTENT, "之所以把思考能力放在第一位，是因为思考不仅对于职场，对于人生中各种场合都非常重要。有 …\n" +
                    "去哪所学校，上哪所大学，和谁谈恋爱，选择去哪家公司就职，选择用什么方法跟上司讲述你的解决方案，用什么方式跟客户达成协议，决定公司是否要开展或终止某个事业……你的每一个决定都是人生的十字路口，每一个决定都需要慎重思考。")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image7)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values7)

        val values8 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "8")
            put(DatabaseHelper.COLUMN_TITLE, "幼儿园趣味活动")
            put(DatabaseHelper.COLUMN_CONTENT, "品尝到成功滋味的、身体平衡能力较强的三名幼儿自由组合走大鞋，方法同两人走，只是难度有所增加，三人必须密切配合、步调一致才能走好。")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image8)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values8)

        val values9 = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ID, "9")
            put(DatabaseHelper.COLUMN_TITLE, "生活小常识")
            put(DatabaseHelper.COLUMN_CONTENT, "可以把盘子放进锅子里倒入牛奶，加热四至五分钟，牛奶沸腾后就可熄火取出盘子，裂痕就会几乎消失不见了。")
            put(DatabaseHelper.COLUMN_IMAGE_RES_ID, R.drawable.image9)
            //put(DatabaseHelper.COLUMN_VIDEO_RES_ID, R.raw.video2)
        }
        db.insert(DatabaseHelper.TABLE_NAME, null, values9)
    }
}





