package english.dictionary.app.ui.common

import androidx.compose.foundation.clickable
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
    modifier: Modifier = Modifier,
    headerTitle: String,
    leftIcon: Int?,
    rightIcon: Int?,
    onLeftIconClicked: () -> Unit,
    onRightIconClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            if(leftIcon != null){
                Icon(
                    painter = painterResource(id = leftIcon),
                    contentDescription = "leftIcon",
                    tint = blue,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { onLeftIconClicked() }
                )
            }
            Text(text = headerTitle, style = DefaultTextStyle(fontWeight = FontWeight.Bold))
        }
        if (rightIcon != null)
            Icon(
                modifier = Modifier.clickable { onRightIconClicked() },
                painter = painterResource(id = rightIcon),
                contentDescription = "rightIcon",
                tint = blue
            )
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderPreview() {
    Header(
        headerTitle = "Profile",
        leftIcon = R.drawable.menu,
        rightIcon = R.drawable.microphone,
        onLeftIconClicked = {},
        onRightIconClicked = {})
}