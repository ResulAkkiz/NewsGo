package com.project.newsgo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.newsgo.data.entity.Article
import com.project.newsgo.data.repository.FirebaseFirestoreRepository
import com.project.newsgo.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private var newsRepository: NewsRepository,
) : ViewModel() {
    var currentPage = 1
    val news = MutableLiveData<List<Article>>()

    init {
        getNews("android", currentPage)
    }

    fun getMoreNews(query: String) {
        currentPage++
        getNews(query, currentPage)
    }

    fun getNews(query: String, page: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = newsRepository.getNews(query, page)
            if (page > 1) {
                news.value = news.value?.plus(result.articles)
            } else {
                news.value = result.articles
            }
        }
    }

}