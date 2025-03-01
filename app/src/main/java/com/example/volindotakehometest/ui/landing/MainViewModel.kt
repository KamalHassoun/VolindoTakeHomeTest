package com.example.volindotakehometest.ui.landing

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.volindotakehometest.domain.repositories.FeedRepository
import com.example.volindotakehometest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val feedRepository: FeedRepository
): BaseViewModel<MainUIState, MainIntent>(MainUIState()) {
    @Inject
    fun init() {
        viewModelScope.launch(dispatcher.io) {
            feedRepository.getFeed()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pagedFeedData ->
                    _uiState.update { it.copy(feed = pagedFeedData) }
                }
        }
    }

    override fun processIntents(intent: MainIntent) {}
}