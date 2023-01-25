package english.dictionary.app.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.User
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.common.UsersItem
import english.dictionary.app.ui.theme.DefaultTextStyle


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Header("Profile", R.drawable.menu, R.drawable.microphone)
        UserInformationSection(viewModel.getUser())
        DescriptionSection()
    }
}

//TODO change image
@Composable
fun UserInformationSection(
    user: User
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        UsersItem()
        Column {
            TextWithIcon(
                title = stringResource(id = R.string.fullName),
                value = user.fullName(),
                icon = R.drawable.education
            )
            TextWithIcon(
                title = stringResource(id = R.string.university),
                value = user.university,
                icon = R.drawable.education
            )
            TextWithIcon(
                title = stringResource(id = R.string.education),
                value = user.education,
                icon = R.drawable.education
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserInformationPreview() {
    UserInformationSection(
        user = User(
            "Ali",
            lastName = "Javadi",
            university = "Sistan and bluchestan",
            education = "Compouter science"
        )
    )
}

@Composable
fun TextWithIcon(
    title: String,
    value: String,
    icon: Int
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = 20.dp, end = 5.dp),
            painter = painterResource(id = icon),
            contentDescription = "icon",
            tint = Color.DarkGray
        )
        Column {
            Text(
                text = title,
                style = DefaultTextStyle(fontSize = MaterialTheme.typography.body2.fontSize),
                maxLines = 1
            )
            Text(text = value, style = DefaultTextStyle(fontWeight = FontWeight.Bold), maxLines = 1)
        }
    }
}


@Composable
fun DescriptionSection() {
    Text(modifier = Modifier.fillMaxWidth(), text = stringResource(id = R.string.text), style = DefaultTextStyle(), textAlign = TextAlign.Justify)
}