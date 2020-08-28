package com.carlos.news.framework.repository

import com.carlos.core.data.NewsDataSource
import com.carlos.core.domain.Articles
import com.carlos.news.framework.services.NewsServices

class NewsRepository (private val newsApi: NewsServices) : NewsDataSource {

    override suspend fun topHeadlines(): Articles {
        return newsApi.topHeadLines().await()
    }

    override suspend fun everything() {

    }

    override suspend fun sources() {

    }

}