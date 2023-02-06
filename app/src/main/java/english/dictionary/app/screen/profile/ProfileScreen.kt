package english.dictionary.app.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.User
import english.dictionary.app.data.UserSettings
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.common.UsersItem
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.backgroundColor


@Composable
fun ProfileScreen(
    onAddNewWordButtonClicked: () -> Unit,
    onOnlineListButtonClicked: () -> Unit,
    onUpdateInformationClicked: () -> Unit
) {
    val userName = UserSettings.fullNameState.collectAsState().value
    val education = UserSettings.educationState.collectAsState().value
    val university = UserSettings.universityState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Header(modifier = Modifier.padding(bottom = 12.dp),
                title = stringResource(id = R.string.profile),
                leftIcon = R.drawable.menu,
                rightIcon = null,
                onLeftIconClicked = {},
                onRightIconClicked = {})
            UserInformationSection(
                fullName = userName,
                education = education,
                university = university
            )
            DescriptionSection()
            Spacer(modifier = Modifier.height(16.dp))
            if (userName.isEmpty()) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    onClick = { onUpdateInformationClicked() }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.updateInformation),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                onClick = { onAddNewWordButtonClicked() }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.addNewWord),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                onClick = { onOnlineListButtonClicked() }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.onlineList),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

//TODO change image
@Composable
fun UserInformationSection(
    fullName: String,
    education: String,
    university: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        UsersItem(secondImage = null)
        Column {
            TextWithIcon(
                title = stringResource(id = R.string.fullName),
                value = fullName.ifEmpty { "---" },
                icon = R.drawable.education
            )
            TextWithIcon(
                title = stringResource(id = R.string.university),
                value = education.ifEmpty { "---" },
                icon = R.drawable.education
            )
            TextWithIcon(
                title = stringResource(id = R.string.education),
                value = university.ifEmpty { "---" },
                icon = R.drawable.education
            )
        }
    }
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
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.text),
        style = DefaultTextStyle(),
        textAlign = TextAlign.Justify
    )
}