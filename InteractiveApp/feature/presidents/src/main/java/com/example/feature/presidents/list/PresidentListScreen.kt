package com.example.feature.presidents.list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.designsystem.ui.theme.Utils.dimenXSmall12
import com.example.designsystem.ui.theme.Utils.dimenXSmall15
import com.example.designsystem.ui.theme.Utils.dimenXSmall16
import com.example.designsystem.ui.theme.Utils.dimenXSmall8
import com.example.designsystem.ui.theme.component.containers.InformationBoard
import com.example.designsystem.ui.theme.component.inputs.CustomInputField
import com.example.domain.models.ColombiaPresident
import com.example.feature.presidents.R

@Composable
fun PresidentListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {

    val viewModel: PresidentListViewModel = hiltViewModel()
    val state by viewModel.viewStateFlow.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    val filteredPresidents = if (searchQuery.isEmpty()) {
        state.presidents
    } else {
        state.presidents.filter { president ->
            president.name.contains(searchQuery, ignoreCase = true)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.processEvent(PresidentListViewModel.ViewEvent.OnPresident)
    }

    LaunchedEffect(state.navigateEffect) {
        when (val effect = state.navigateEffect) {
            is PresidentListViewModel.ViewEffect.Navigate -> {
                if (effect.route.isNotEmpty()) {
                    navController.navigate(effect.route)
                }
            }

            PresidentListViewModel.ViewEffect.GoBack -> TODO()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = dimenXSmall16, start = dimenXSmall12, end = dimenXSmall12),
        verticalArrangement = Arrangement.spacedBy(dimenXSmall12)
    ) {

        Text(
            text = stringResource(id = R.string.president_searchbar_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = dimenXSmall8)
        )

        CustomInputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = stringResource(id = R.string.president_searchbar_placeholder)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimenXSmall8)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.colombian_presidents_title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = dimenXSmall12)
                )
            }

            if (state.loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimenXSmall16),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                items(items = filteredPresidents) { item ->
                    InformationBoard(
                        modifier = Modifier.height(205.dp),
                        title = item.politicalParty
                    ) {
                        PresidentItem(
                            president = item,
                            onItemSelected = {
                                viewModel.processEvent(
                                    PresidentListViewModel.ViewEvent.OnItemSelected(
                                        itemId = item.id
                                    )
                                )
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(dimenXSmall16))
                }
            }
        }

    }
}

@Composable
private fun PresidentItem(
    modifier: Modifier = Modifier,
    president: ColombiaPresident,
    onItemSelected: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onItemSelected()
            },
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = president.image,
                contentDescription = stringResource(id = R.string.president_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(70.dp)
            )

            Column {
                Text(
                    text = "${president.name} ${president.lastName}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${stringResource(id = R.string.start_period_date)}  ${president.startPeriodDate}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "${stringResource(id = R.string.end_period_date)}  ${president.endPeriodDate}",
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }

        Spacer(modifier = Modifier.height(dimenXSmall12))

        Text(
            text = president.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }

}
