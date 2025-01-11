package com.example.domain.models

import java.time.LocalDate

data class ColombiaPresident(
    val id: Int = 0,
    val image: String = "",
    val name: String= "",
    val lastName: String= "",
    val startPeriodDate: String = "",
    val endPeriodDate: String? = null,
    val politicalParty: String= "",
    val description: String= "",
    val cityId: Int = 0,
    val city: String? = null
)
