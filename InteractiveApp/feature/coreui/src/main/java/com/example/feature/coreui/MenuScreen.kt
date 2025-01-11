package com.example.feature.coreui

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.designsystem.ui.theme.Utils.dimenXLarge100
import com.example.designsystem.ui.theme.Utils.dimenXLarge140
import com.example.designsystem.ui.theme.Utils.dimenXLarge200
import com.example.designsystem.ui.theme.Utils.dimenXSmall16
import com.example.designsystem.ui.theme.Utils.dimenXSmall20
import com.example.designsystem.ui.theme.Utils.dimenXSmall8
import com.example.designsystem.ui.theme.Utils.grayColor
import com.example.designsystem.ui.theme.component.buttons.StandardButton

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel : MenuViewModel = viewModel()
    val state by viewModel.viewStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateEffect) {
        when (val effect = state.navigateEffect) {
            is MenuViewModel.ViewEffect.Navigate -> {
                if (effect.route.isNotEmpty()) {
                    navController.navigate(effect.route)
                 }
            }
        }
    }

    Column(
        modifier = modifier
            .background(grayColor)
            .padding(dimenXSmall16)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_description),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = dimenXSmall16)
        )

        StandardButton(
            text = stringResource(id = R.string.go_to_capitalizer_generator_description),
            onButtonClick = { viewModel.processEvent(MenuViewModel.ViewEvent.OnGoToCapitalizer) }
        )
        Spacer(modifier = Modifier.height(dimenXSmall20))

        StandardButton(
            text = stringResource(id = R.string.go_to_password_generator_description),
            onButtonClick = { viewModel.processEvent(MenuViewModel.ViewEvent.OnGoToPassword) }
        )
        Spacer(modifier = Modifier.height(dimenXSmall20))

        StandardButton(
            text = stringResource(id = R.string.go_to_presidents_list_description),
            onButtonClick = { viewModel.processEvent(MenuViewModel.ViewEvent.OnGoToPresidentsList) }
        )
    }

}