package com.example.lovelearningapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

// 积分fragment，用来更新当前积分
class PointFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModels()

//    注意，你需要在每个 Fragment 的布局文件中添加一个用于显示积分的 TextView，
//    并在 Fragment 的代码中找到这个 TextView。例如，在 PointsFragment 中，
//    你可以使用 findViewById 方法或者 View Binding 来获取 pointsTextView。

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("执行", "onViewCreated: ")
        // Observe the user's points
        viewModel.userPoints.observe(viewLifecycleOwner) { points ->
            // Update the UI with the user's points
            view.findViewById<TextView>(R.id.pointsTextView).text = points.toString()  // 需要一个text组件来更新积分
        }
    }

    //...其他的 Fragment 代码
}