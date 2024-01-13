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

    val news = MutableLiveData<List<Article>>()

    init {
        getNews("bitcoin", 1)
    }

    fun getNews(query: String, page: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = newsRepository.getNews(query, page)
            news.value = result.articles
        }
    }

}