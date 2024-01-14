package com.project.newsgo.ui.viewmodels

import android.util.Log
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
    var isLoading = false
    var currentPage = 1
    var query = "Android"
    val news = MutableLiveData<List<Article>>()

    init {
        getNews()
    }

    fun getNews() {
        CoroutineScope(Dispatchers.Main).launch {
            if (!isLoading){
                isLoading=true
                val result = newsRepository.getNews(query, currentPage)
                if (currentPage > 1) {
                    news.value = news.value?.plus(result.articles)
                } else {
                    news.value = result.articles
                }
            }
           isLoading=false

        }
    }

}