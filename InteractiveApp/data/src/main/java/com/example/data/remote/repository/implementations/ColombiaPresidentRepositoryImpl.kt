package com.example.data.remote.repository.implementations


import com.example.data.remote.apis.ColombiaPresidentsApi
import com.example.data.remote.models.ColombiaPresidentDto
import com.example.data.remote.utils.NetworkResult
import com.example.data.remote.utils.handleApiRequest
import javax.inject.Inject

class ColombiaPresidentRepositoryImpl @Inject constructor(
    private val api: ColombiaPresidentsApi
) {
    suspend fun getPresidents(): NetworkResult<List<ColombiaPresidentDto>> {
        return handleApiRequest {
            api.getColombianPresidents()
        }
    }
}