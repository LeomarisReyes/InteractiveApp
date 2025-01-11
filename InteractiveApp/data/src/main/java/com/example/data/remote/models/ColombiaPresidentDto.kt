package com.example.data.remote.models


data class ColombiaPresidentDto(
    val id: Int,
    val image: String,
    val name: String,
    val lastName: String,
    val startPeriodDate: String,
    val endPeriodDate: String? = null,
    val politicalParty: String,
    val description: String,
    val cityId: Int,
    val city: String?
)