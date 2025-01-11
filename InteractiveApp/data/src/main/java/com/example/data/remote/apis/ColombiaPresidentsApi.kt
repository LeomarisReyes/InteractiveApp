package com.example.data.remote.apis

import com.example.data.remote.models.ColombiaPresidentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val COLOMBIA_PRESIDENTS_URL = "President/"
private const val SORT_BY_VALUE = "sortBy"
private const val SORT_DIRECTION_VALUE = "sortDirection"

interface ColombiaPresidentsApi {
    @GET(COLOMBIA_PRESIDENTS_URL)
    suspend fun getColombianPresidents(
        @Query(SORT_BY_VALUE) sortBy: String? = "id",
        @Query(SORT_DIRECTION_VALUE) sortDirection: String? = "asc",
    ): Response<List<ColombiaPresidentDto>>

    @GET("$COLOMBIA_PRESIDENTS_URL/{id}")
    suspend fun getPresidentById(
        @Path("id") id: Int
    ): Response<ColombiaPresidentDto>

}
