package com.example.feature.passwordgenerator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.designsystem.ui.theme.Utils.dimenXLarge120
import com.example.designsystem.ui.theme.Utils.dimenXSmall12
import com.example.designsystem.ui.theme.Utils.dimenXSmall15
import com.example.designsystem.ui.theme.Utils.dimenXSmall16
import com.example.designsystem.ui.theme.Utils.dimenXSmall20
import com.example.designsystem.ui.theme.Utils.dimenXSmall5
import com.example.designsystem.ui.theme.Utils.dimenXSmall8
import com.example.designsystem.ui.theme.Utils.grayColor
import com.example.designsystem.ui.theme.Utils.mainBackgroundColor
import com.example.designsystem.ui.theme.component.buttons.StandardButton
import com.example.designsystem.ui.theme.component.containers.InformationBoard
import com.example.feature.passwordgenerator.RandomPasswordGeneratorViewModel.ViewEvent

@Composable
fun RandomPasswordGeneratorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val viewModel : RandomPasswordGeneratorViewModel = viewModel()
    val state by viewModel.viewStateFlow.collectAsStateWithLifecycle()

    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(dimenXSmall20)
            .fillMaxSize()
            .padding(top = dimenXSmall16, start = dimenXSmall12, end = dimenXSmall12),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimenXSmall15)
    ) {

        MainInformation()

        InformationBoard(
            title = stringResource(id = R.string.password_settings)
        ) {
            Options(
                isSelected = state.includeUpperCaseLetter,
                text = stringResource(id = R.string.include_uppercase),
                onSwitchChange = { newValue ->
                    viewModel.processEvent(ViewEvent.OnIncludeUpperCaseLetterChanged(newValue))
                }
            )

            Options(
                isSelected = state.includeNumber,
                text = stringResource(id = R.string.include_numbers),
                onSwitchChange = {  newValue ->
                    viewModel.processEvent(ViewEvent.OnIncludeNumberChanged(newValue))
                }
            )

            Options(
                isSelected = state.includeSymbol,
                text = stringResource(id = R.string.include_symbols),
                onSwitchChange = {  newValue ->
                    viewModel.processEvent(ViewEvent.OnIncludeSymbolChanged(newValue))
                }
            )

            PasswordOption(
                text = stringResource(id = R.string.password_size),
                sliderValue = state.size,
                onEvent =  viewModel::processEvent
            )
        }

        StandardButton(
            text = stringResource(
                id = R.string.generate_password
            ),
            onButtonClick = {  viewModel.processEvent(ViewEvent.OnGeneratedPassword)  }
        )

        GeneratedPassword(
            generatedPassword = state.generatedPassword,
            clipboardManager = clipboardManager
        )
    }
}

@Composable
fun GeneratedPassword(
    generatedPassword: String,
    clipboardManager: ClipboardManager
){
    InformationBoard(
        title = stringResource(id = R.string.your_generated_password)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimenXSmall8)
        ) {
            Text(
                text = generatedPassword,
                modifier = Modifier
                    .padding(end = dimenXSmall8),
                style = MaterialTheme.typography.titleMedium
            )

//            IconButton(onClick = {
//                clipboardManager.setText(AnnotatedString(generatedPassword))
//            }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_copy),
//                    contentDescription = stringResource(id = com.example.designsystem.R.string.copy_to_clipboard),
//                    tint = mainIconColor
//                )
//            }
        }
    }
}

@Composable
private fun MainInformation() {
    Image(
        painter = painterResource(id = R.drawable.ic_password_clock),
        contentDescription = stringResource(id = R.string.password_generator_title),
        modifier = Modifier.size(dimenXLarge120),
        contentScale = ContentScale.Crop
    )

    Text(
        text = stringResource(id = R.string.password_generator_title),
        style = MaterialTheme.typography.titleLarge
    )
    Text(
        text = stringResource(id = R.string.password_generator_description),
        style = MaterialTheme.typography.titleMedium,
        color = Color.DarkGray
    )
}

@Composable
private fun PasswordOption(
    modifier: Modifier = Modifier,
    text: String,
    sliderValue: Float,
    onEvent: (ViewEvent) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "$text:      $sliderValue",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(vertical = dimenXSmall5)
        )

        Text(
            text = stringResource(id = R.string.password_size_placeholder),
            style = MaterialTheme.typography.titleSmall,
            color = Color.LightGray
        )

        Slider(
            value = sliderValue,
            onValueChange = { newValue -> onEvent(ViewEvent.OnSizeChanged(newValue)) },
            valueRange = 5f..20f,
            steps = 15,
            onValueChangeFinished = {  },
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = mainBackgroundColor,
                activeTrackColor = mainBackgroundColor,
                inactiveTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun Options(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    onSwitchChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimenXSmall5)
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleSmall
        )

        Switch(
            checked = isSelected,
            onCheckedChange = onSwitchChange,
            colors = SwitchDefaults.colors(
                uncheckedBorderColor = Color.Transparent,
                checkedBorderColor = Color.Transparent,
                checkedThumbColor = Color.White,
                checkedTrackColor = mainBackgroundColor,
                uncheckedThumbColor = mainBackgroundColor,
                uncheckedTrackColor = grayColor
            )
        )
    }
}