package com.project.newsgo.data.datasource

import com.project.newsgo.retrofit.NewsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataSource(var newsDao: NewsDao) {
    private val API_KEY = "b5b871bae44141f8b69cabe3e2b8e9fd"

    suspend fun getNews(
        query: String, page: Int,
    ) = withContext(
        Dispatchers.IO
    ) {
        val response = newsDao.getNews(query, page, API_KEY)
    }
}