package com.carlos.news.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlos.core.domain.Article

class NewsPageViewModel : ViewModel() {
    val articlePageLiveData = MutableLiveData<Article>()
    val closePageLiveData = MutableLiveData(false)

    fun load(article: Article) = article.let {
        articlePageLiveData.value = it
    }

    fun close(b: Boolean) = b.let {
        closePageLiveData.value = it
    }
}