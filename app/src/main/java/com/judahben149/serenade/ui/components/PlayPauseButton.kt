package com.judahben149.serenade.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judahben149.serenade.R

@Composable
fun PlayButton(onClick:() -> Unit) {
    Button(
        modifier = Modifier.size(72.dp),
        shape = CircleShape,
        onClick = { onClick() },
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = stringResource(R.string.play))
    }
}

@Composable
fun PauseButton(onClick:() -> Unit) {
    Button(
        modifier = Modifier.size(72.dp),
        shape = CircleShape,
        onClick = { onClick() },
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.ic_pause),
            contentDescription = stringResource(R.string.pause))
    }
}

@Preview(showBackground = true)
@Composable
fun PlayButtonPreview() {
    PlayButton {}
}

@Preview(showBackground = true)
@Composable
fun PauseButtonPreview() {
    PauseButton {}
}
