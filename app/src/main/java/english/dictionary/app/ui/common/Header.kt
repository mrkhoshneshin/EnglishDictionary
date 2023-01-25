package english.dictionary.app.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import english.dictionary.app.R
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue


@Composable
fun Header(
    headerTitle: String,
    leftIcon: Int,
    rightIcon: Int
) {
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            Icon(
                painter = painterResource(id = leftIcon),
                contentDescription = "leftIcon",
                tint = blue,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = headerTitle, style = DefaultTextStyle(fontWeight = FontWeight.Bold))
        }
        Icon(
            painter = painterResource(id = rightIcon),
            contentDescription = "rightIcon",
            tint = blue
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderPreview() {
    Header(headerTitle = "Profile", leftIcon = R.drawable.menu, rightIcon = R.drawable.microphone)
}