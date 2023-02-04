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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import english.dictionary.app.R
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue
import english.dictionary.app.util.getCurrentHour


@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    greetingTitle: Boolean = false,
    leftIcon: Int?,
    rightIcon: Int?,
    onLeftIconClicked: () -> Unit,
    onRightIconClicked: () -> Unit
) {
    val currentHour = getCurrentHour()
    val greetingText =
        if (currentHour < 12) stringResource(id = R.string.goodMorning) else if (currentHour in 13..17) stringResource(
            id = R.string.goodAfternoon
        ) else stringResource(id = R.string.goodNight)
    val headerTitle = if (greetingTitle) {
        stringResource(id = R.string.hi) + " " + title + ", " + greetingText
    } else title
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if (rightIcon != null) Arrangement.SpaceBetween else Arrangement.Start
    ) {
        Row {
            if (leftIcon != null) {
                Icon(
                    painter = painterResource(id = leftIcon),
                    contentDescription = "leftIcon",
                    tint = blue,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { onLeftIconClicked() }
                )
            }
            Text(
                text = headerTitle,
                style = DefaultTextStyle()
            )
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
        title = "Profile",
        leftIcon = R.drawable.menu,
        rightIcon = R.drawable.microphone,
        onLeftIconClicked = {},
        onRightIconClicked = {})
}