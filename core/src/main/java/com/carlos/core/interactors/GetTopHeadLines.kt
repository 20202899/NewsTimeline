package com.carlos.core.interactors

import com.carlos.core.data.NewsDataSourceRepository

class GetTopHeadLines (
    private val newsDataSourceRepository: NewsDataSourceRepository
) {
    suspend operator fun invoke() = newsDataSourceRepository.topHeadlines()
}