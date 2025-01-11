package com.example.feature.presidents.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.designsystem.ui.theme.Utils.dimenXSmall16
import com.example.designsystem.ui.theme.Utils.dimenXSmall4
import com.example.designsystem.ui.theme.Utils.dimenXSmall8
import com.example.designsystem.ui.theme.Utils.mainBackgroundColor
import java.sql.Date

@Composable
fun PresidentDetailsScreen(

) {
    Column {

    }
}

@Composable
fun Timeline(
    photoUrl: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimenXSmall8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Línea vertical del Timeline
        Box(
            modifier = Modifier
                .width(dimenXSmall4)
                .fillMaxHeight()
                .background( mainBackgroundColor)
        )

        Spacer(modifier = Modifier.width(dimenXSmall16))

        // Foto redondeada
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(dimenXSmall16))

        Column(
            modifier = Modifier.padding(dimenXSmall8)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(dimenXSmall4))
            Text(
                text = description,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ContentInformation(
    startPeriodDate : Date,
    endPeriodDate : Date,
    politicalParty : String,
    description : String
){
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Start period date: $startPeriodDate",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "End period date: $endPeriodDate",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Political party:",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = politicalParty,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Description",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}