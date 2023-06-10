package com.example.paging3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class BrandListPagingDataSource(
    private val name:String ="",
    private val brandApi: BrandApi
): PagingSource<Int, AllBrand>() {

    override fun getRefreshKey(state: PagingState<Int, AllBrand>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllBrand> {
        val page = params.key?: 1
        return try{

            val response = brandApi.getBrandList(name,page,20)
            //insert to db here
            LoadResult.Page(
                response.allBrands!!,
                prevKey = if(page == 1) null else page-1,
                nextKey = if(response.allBrands.isEmpty()) null else page+1
            )

        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }


}