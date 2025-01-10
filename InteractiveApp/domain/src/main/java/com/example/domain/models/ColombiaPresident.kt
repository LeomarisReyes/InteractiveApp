package com.example.domain.models

data class ColombiaPresident(
    val id: Int,
    val image: String,
    val name: String,
    val lastName: String,
    val startPeriodDate: String,
    val endPeriodDate: String,
    val politicalParty: String,
    val description: String,
    val cityId: Int,
    val city: String?
)
