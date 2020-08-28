package com.carlos.core.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Articles(
    @SerializedName("articles")
    val articles: List<Article>
) : Serializable