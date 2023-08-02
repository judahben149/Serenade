package com.judahben149.serenade.ui.components

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
fun NextButton() {
    Button(
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        onClick = {  }
    ) {
        Icon(
            modifier = Modifier.size(52.dp),
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = stringResource(R.string.next))
    }
}

@Preview(showBackground = true)
@Composable
fun NextButtonPreview() {
    NextButton()
}
