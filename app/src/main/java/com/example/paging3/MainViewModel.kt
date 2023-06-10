package com.example.paging3

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val brandUseCase: BrandUseCase
):ViewModel() {

   private var selectFromListQuery = MutableLiveData<String>("")

    private var searchJob : Job?=null

    fun onEvent(event: BrandListingEvent) {
        when (event) {
            is BrandListingEvent.OnSearchQueryChanged -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    selectFromListQuery.value = event.query
                }
            }
        }
    }

    val pagingDataFlow = selectFromListQuery.switchMap {
        brandUseCase(it).cachedIn(viewModelScope)
    }
}