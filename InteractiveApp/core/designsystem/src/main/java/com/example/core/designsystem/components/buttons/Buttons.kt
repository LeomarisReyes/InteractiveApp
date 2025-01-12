package com.example.core.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.core.designsystem.utils.dimenXSmall12
import com.example.core.designsystem.utils.mainBackgroundColor
import com.example.core.designsystem.utils.mainTextColor


@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = mainBackgroundColor,
    textColor: Color = mainTextColor,
    text: String,
    onButtonClick: ()-> Unit
) {
    Button(
        onClick = onButtonClick,
        modifier = modifier
            .clip((RoundedCornerShape(dimenXSmall12)))
            .background(backgroundColor)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor),
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.titleMedium
        );
    }
}
