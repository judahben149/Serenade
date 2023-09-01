package com.judahben149.serenade.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judahben149.serenade.R
import com.judahben149.serenade.ui.theme.Purple80

@Composable
fun PreviousButton(onClick:() -> Unit) {
    Button(
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Purple80),
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.SkipPrevious,
            contentDescription = stringResource(R.string.previous))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviousButtonPreview() {
    PreviousButton {}
}
