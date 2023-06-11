package com.example.lovelearningapp

import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

// 文章fragment，用来计时获取积分
class ArticleFragment : Fragment() {

    val viewModel: ArticleViewModel by viewModels() // 使用viewModels委托来创建和获取 ViewModel

    override fun onResume() {
        super.onResume()

        // Start a timer when the fragment is resumed
        val timer = object : CountDownTimer(300000, 1000) {
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

}