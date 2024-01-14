package com.project.newsgo.data.datasource

import com.project.newsgo.data.entity.News
import com.project.newsgo.retrofit.NewsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataSource(var newsDao: NewsDao) {
    private val API_KEY = "b5b871bae44141f8b69cabe3e2b8e9fd"
    private val PAGE_SIZE = 20

    suspend fun getNews(
        query: String, page: Int,
    ): News = withContext(
        Dispatchers.IO
    ) {
        return@withContext newsDao.getNews(query, page, PAGE_SIZE,API_KEY)
    }
}