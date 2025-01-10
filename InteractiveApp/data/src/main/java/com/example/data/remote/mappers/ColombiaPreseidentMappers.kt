package com.example.data.remote.mappers

import com.example.data.remote.models.ColombiaPresidentDto
import com.example.domain.models.ColombiaPresident

fun ColombiaPresidentDto.toColombiaPresident() = ColombiaPresident(
    id = id,
    image = image,
    name = name,
    lastName = lastName,
    startPeriodDate = startPeriodDate,
    endPeriodDate = endPeriodDate,
    politicalParty = politicalParty,
    description = description,
    cityId = cityId,
    city = city
)