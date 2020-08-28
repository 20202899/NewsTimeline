package com.carlos.news.framework.services

import com.carlos.core.domain.Articles
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NewsServices {
    @GET("top-headlines?country=br&apiKey=4a7b790cd84e43f28672ed3b3fc0d6dd")
    fun topHeadLines() : Deferred<Articles>
}