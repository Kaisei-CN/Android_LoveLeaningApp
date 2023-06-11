package com.example.lovelearningapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticleViewModel : ViewModel() {
    // LiveData to hold the user's points
    private val _userPoints = MutableLiveData<Int>(0)
    val userPoints: LiveData<Int> get() = _userPoints

    fun addUserPoints(points: Int) {
        _userPoints.value = (_userPoints.value ?: 0) + points
    }

}