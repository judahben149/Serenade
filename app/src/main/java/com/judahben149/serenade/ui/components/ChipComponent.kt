package com.judahben149.serenade.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judahben149.serenade.ui.theme.Grey
import com.judahben149.serenade.ui.theme.RedCrayola
import com.judahben149.serenade.ui.theme.RedCrayolaAlpha20

@Composable
fun ChipComponent(
    text: String,
    isSelected: Boolean = false,
    onClick:() -> Unit = { }
) {
    Button(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        shape = RoundedCornerShape(30),
        border = if (isSelected) BorderStroke(1.dp, RedCrayola) else BorderStroke(1.dp, Grey),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) RedCrayolaAlpha20 else MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Black else Grey
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipComponentPreview() {
    ChipComponent("Sample", false)
}
