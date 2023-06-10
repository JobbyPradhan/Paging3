package com.example.paging3

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import javax.inject.Inject

class BrandUseCase @Inject constructor(
    private val brandRepository: BrandRepository
) {

    operator fun invoke(searchTxt:String): LiveData<PagingData<AllBrand>> {
        return brandRepository.getBrandList(searchTxt)
    }
}