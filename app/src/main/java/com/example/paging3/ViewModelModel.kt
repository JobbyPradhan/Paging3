package com.example.paging3

sealed class BrandListingEvent{
    data class OnSearchQueryChanged(val query :String):BrandListingEvent()
}