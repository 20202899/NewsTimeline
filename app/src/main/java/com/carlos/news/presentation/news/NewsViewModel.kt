package com.carlos.news.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.core.domain.Articles
import com.carlos.news.framework.Interactors
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NewsViewModel (private val interactors: Interactors) : ViewModel() {
    val topHeadLinesLiveData = MutableLiveData<Articles>()

    fun getTopHeadLines() = viewModelScope.launch {
        val result = async { interactors.getTopHeadLines() }.await()
        topHeadLinesLiveData.value = result
    }
}