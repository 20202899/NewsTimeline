package com.carlos.news.extensions

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toFormattedDateString(): String? {
    val simpleFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("pt_BR"))
    val dateFormatted = simpleFormatter.parse(this)
    simpleFormatter.applyPattern("HH:mm")
    return simpleFormatter.format(dateFormatted)
}