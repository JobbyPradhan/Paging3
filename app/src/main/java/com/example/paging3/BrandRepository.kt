package com.example.paging3

import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface BrandRepository {
    fun getBrandList(name:String): LiveData<PagingData<AllBrand>>
}