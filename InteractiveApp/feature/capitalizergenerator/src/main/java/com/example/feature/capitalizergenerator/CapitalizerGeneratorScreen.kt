package com.example.feature.capitalizergenerator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.designsystem.ui.theme.Utils.dimenXLarge120
import com.example.designsystem.ui.theme.Utils.dimenXSmall12
import com.example.designsystem.ui.theme.Utils.dimenXSmall16
import com.example.designsystem.ui.theme.Utils.dimenXSmall20
import com.example.designsystem.ui.theme.Utils.dimenXSmall8
import com.example.designsystem.ui.theme.Utils.mainIconColor
import com.example.designsystem.ui.theme.component.buttons.StandardButton
import com.example.designsystem.ui.theme.component.containers.InformationBoard
import com.example.designsystem.ui.theme.component.inputs.CustomInputField

@Composable
fun CapitalizerGeneratorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState
){
    val viewModel : CapitalizerGeneratorViewModel = viewModel()
    val state by viewModel.viewStateFlow.collectAsStateWithLifecycle()

    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(dimenXSmall20)
            .fillMaxSize()
            .padding(top = dimenXSmall16, start = dimenXSmall12, end = dimenXSmall12),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimenXSmall20)
    ) {
        MainInformation()

        InformationBoard(
            title = stringResource(id = R.string.phrase_settings)
        ) {
            CustomInputField(
                value = state.phraseToCapitalized,
                onValueChange = {
                    viewModel.processEvent(CapitalizerGeneratorViewModel.ViewEvent.OnPhraseChanged(it))
                },
                 placeholder =  stringResource(id = R.string.capitalizer_input_placeholder)
            )
        }

        StandardButton(
            text = stringResource(
                id = R.string.capitalized_description
            ),
            onButtonClick = {
                viewModel.processEvent(CapitalizerGeneratorViewModel.ViewEvent.OnCapitalizedPhrase)
              }
        )

        CapitalizedPhrase(
            capitalizedPhrase = state.capitalizedPhrase,
            clipboardManager = clipboardManager
        )
    }
}

@Composable
fun MainInformation() {
    Image(
        painter = painterResource(id = R.drawable.ic_capitalize_image),
        contentDescription = stringResource(id = R.string.capitalizer_image),
        modifier = Modifier.size(dimenXLarge120),
        contentScale = ContentScale.Crop
    )

    Text(
        text = stringResource(id = R.string.capitalizer_title),
        style = MaterialTheme.typography.titleLarge
    )
    Text(
        text = stringResource(id = R.string.capitalizer_description),
        style = MaterialTheme.typography.titleMedium,
        color = Color.DarkGray
    )
    Spacer(modifier = Modifier.height(dimenXSmall12))
}

@Composable
fun CapitalizedPhrase(
    capitalizedPhrase: String,
    clipboardManager: ClipboardManager
) {
    InformationBoard(
        title = stringResource(id = R.string.you_capitalizer_phrase_descriptiln)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimenXSmall8)
        ) {
            Text(
                text = capitalizedPhrase,
                modifier = Modifier
                    .padding(end = dimenXSmall8),
                style = MaterialTheme.typography.titleMedium
            )

            IconButton(onClick = {
                clipboardManager.setText(AnnotatedString(capitalizedPhrase))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "", // stringResource(id = R.string.copy_to_clipboard_description),
                    tint = mainIconColor
                )
            }
        }
    }
}