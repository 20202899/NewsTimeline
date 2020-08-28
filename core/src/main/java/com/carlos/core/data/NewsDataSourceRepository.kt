package com.carlos.core.data

class NewsDataSourceRepository (
    private val newsDataSource: NewsDataSource
) {
    suspend fun topHeadlines() = newsDataSource.topHeadlines()
}