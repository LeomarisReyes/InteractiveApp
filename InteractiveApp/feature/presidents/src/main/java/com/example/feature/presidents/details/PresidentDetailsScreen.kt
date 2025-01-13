package com.example.feature.presidents.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.core.designsystem.utils.dimenXLarge100
import com.example.core.designsystem.utils.dimenXLarge140
import com.example.core.designsystem.utils.dimenXLarge200
import com.example.core.designsystem.utils.dimenXSmall16
import com.example.core.designsystem.utils.dimenXSmall8
import com.example.feature.presidents.R

@Composable
fun PresidentDetailsScreen(
    presidentId: Int
) {
    val viewModel: PresidentDetailsViewModel = hiltViewModel()
    val state by viewModel.viewStateFlow.collectAsStateWithLifecycle()

    val selectedPresident = state.president

    LaunchedEffect(Unit) {
        viewModel.processEvent(PresidentDetailsViewModel.ViewEvent.OnPresidentById(presidentId = presidentId))
    }

    Column(modifier = Modifier.fillMaxSize())
    {
        if (state.loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimenXSmall16),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                TimelineItem(
                    photoUrl = selectedPresident.image,
                    title = "${selectedPresident.name} ${selectedPresident.lastName}",
                    description = selectedPresident.politicalParty
                )

                ContentInformation(
                    modifier = Modifier.padding(horizontal = dimenXSmall16),
                    startPeriodDate = selectedPresident.startPeriodDate,
                    endPeriodDate = selectedPresident.endPeriodDate ?: "",
                    description = selectedPresident.description
                )
            }
        }

    }
}

@Composable
fun TimelineItem(
    photoUrl: String,
    title: String,
    description: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_colombian_flag),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimenXLarge200)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = dimenXSmall16)
                .padding(top = dimenXLarge100)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(dimenXLarge140)
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

            Spacer(modifier = Modifier.height(dimenXSmall16))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(dimenXSmall8))

            Text(
                text = description,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ContentInformation(
    modifier: Modifier = Modifier,
    startPeriodDate: String,
    endPeriodDate: String,
    description: String
) {
    Column(
        modifier = modifier.padding(dimenXSmall16),
        verticalArrangement = Arrangement.spacedBy(dimenXSmall8)
    ) {
        Text(
            text = stringResource(id = R.string.presidential_dates),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "$startPeriodDate - $endPeriodDate",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(dimenXSmall16))
        Text(
            text = stringResource(id = R.string.presidential_description),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
}