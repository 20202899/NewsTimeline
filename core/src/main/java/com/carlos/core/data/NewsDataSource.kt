package com.carlos.core.data

import com.carlos.core.domain.Article
import com.carlos.core.domain.Articles
import kotlinx.coroutines.Deferred

interface NewsDataSource {
    suspend fun topHeadlines() : Articles
    suspend fun everything()
    suspend fun sources()
}