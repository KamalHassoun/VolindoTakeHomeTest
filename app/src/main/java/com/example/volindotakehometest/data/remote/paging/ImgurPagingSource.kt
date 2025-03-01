package com.example.volindotakehometest.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.volindotakehometest.data.remote.response.ImgurEncapsulatedResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ImgurPagingSource<T: Any>(
    private val action: suspend (page: Int) -> Response<ImgurEncapsulatedResponse<List<T>>>
): PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val currentPage = params.key ?: 1
            val response = action(currentPage)
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                LoadResult.Page(
                    data = responseBody.data,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = currentPage + 1
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}