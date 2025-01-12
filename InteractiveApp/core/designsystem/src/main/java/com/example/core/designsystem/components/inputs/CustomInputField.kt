package com.example.core.designsystem.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.designsystem.utils.dimenXSmall6
import com.example.core.designsystem.utils.dimenXSmall8
import com.example.core.designsystem.utils.grayColor
import com.example.core.designsystem.utils.mainBackgroundColor

@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = modifier
            .fillMaxWidth()
            .padding(dimenXSmall8),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = mainBackgroundColor,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = grayColor
        ),
        shape = RoundedCornerShape(dimenXSmall6)
    )
}