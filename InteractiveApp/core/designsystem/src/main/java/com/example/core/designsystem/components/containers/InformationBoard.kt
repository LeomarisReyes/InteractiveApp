package com.example.core.designsystem.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.utils.dimenXSmall16
import com.example.core.designsystem.utils.dimenXSmall20


@Composable
fun InformationBoard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    borderColor : Color = Color.Gray,
    title: String,
    containerDescription: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .border(0.2.dp, borderColor, RoundedCornerShape(dimenXSmall16))
            .fillMaxWidth()
            .padding(dimenXSmall20)
    ) {
        Column {
            Text(text = title, modifier = Modifier.padding(bottom = dimenXSmall16))
            containerDescription()
        }
    }
}

@Preview
@Composable
fun InformationBoardPreview(){
    InformationBoard(
        title = "Show your password"
    ) {
        Column (modifier = Modifier.height(80.dp).background(Color.Blue)){

        }
    }
}