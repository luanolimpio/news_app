package com.example.newsapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.GetBreakingNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getBreakingNewsUseCase: GetBreakingNewsUseCase) :
    ViewModel() {

    val articles = getBreakingNewsUseCase().cachedIn(viewModelScope)
}