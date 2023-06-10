package com.example.paging3

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(
    private val brandApi: BrandApi
) : BrandRepository {

    override fun getBrandList(name: String): LiveData<PagingData<AllBrand>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 60
            ),
            pagingSourceFactory = { BrandListPagingDataSource(name, brandApi) },
        ).liveData
    }
}