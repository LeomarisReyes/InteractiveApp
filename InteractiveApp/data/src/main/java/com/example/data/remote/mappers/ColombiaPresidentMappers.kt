package com.example.data.remote.mappers

import com.example.core.models.presidents.ColombiaPresident
import com.example.data.remote.models.ColombiaPresidentDto
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun ColombiaPresidentDto.toColombiaPresident() = ColombiaPresident(
    id = id,
    image = image,
    name = name,
    lastName = lastName,
    startPeriodDate =  formatDate(startPeriodDate),
    endPeriodDate = endPeriodDate?.let { formatDate(it) },
    politicalParty = politicalParty,
    description = description
)

fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("es")) // Interpretar la fecha original
    val outputFormat =
        SimpleDateFormat("d 'de' MMMM 'del' yyyy", Locale("es")) // Formatear al español
    val parsedDate: Date = inputFormat.parse(date)
    return outputFormat.format(parsedDate)
}
