package com.example.paging3


data class BrandListResponse(
    val allBrands: List<AllBrand> ?= null,
    val message: String?="",
    val ref: String?=""
)
data class AllBrand(
    val id: Int = 0,
    val description: String = "",
    val imageUrl: ImageUrl ?= ImageUrl(),
    val logoImageUrl: ImageUrl ?= ImageUrl(),
    val name: String ?= "",
    val serNo: Int ?= 0,
    var isSelect:Boolean = false
)

data class ImageUrl(
    val id: Int ?= 0,
    val miniUrl: String ?= "",
    val thumbnailUrl: String ?="",
    val url: String ?= ""
)