package com.example.domain.repository

import com.example.domain.models.ColombiaPresident


interface ColombiaPresidentRepository {
    suspend fun getPresidents(): Result<List<ColombiaPresident>>
}
