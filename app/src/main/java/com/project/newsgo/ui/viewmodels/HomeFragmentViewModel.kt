package com.project.newsgo.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.project.newsgo.data.repository.FirebaseFirestoreRepository
import com.project.newsgo.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private var newsRepository: NewsRepository,
    private var firebaseFirestoreRepository: FirebaseFirestoreRepository,
)  : ViewModel(){

}