package com.example.paging3

import retrofit2.http.GET
import retrofit2.http.Query

interface BrandApi {
    @GET("Product/GetBrandList")
    suspend fun getBrandList(
        @Query("Name") requestType:String,
        @Query("PageNumber") pageNumber: Int,
        @Query("PageSize") pageSize:Int
    ): BrandListResponse
}