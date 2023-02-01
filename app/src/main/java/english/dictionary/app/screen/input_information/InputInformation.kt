package english.dictionary.app.screen.input_information

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.ThisUser
import english.dictionary.app.ui.common.CustomTextField
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue

@Composable
fun GetUserInformationScreen(
    viewModel: InputUserInformationViewModel = hiltViewModel(),
    onReadyForNavigateToHomeScreen: () -> Unit
) {

    var textFieldValue by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.enterYourName),
                style = DefaultTextStyle(fontSize = MaterialTheme.typography.body1.fontSize),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            CustomTextField(
                textFieldValue = textFieldValue,
                onTextFieldTextChanged = { textFieldValue = it },
                onSearchIconClicked = { /*TODO*/ },
                leadingIcon = null,
                onKeyBoardOptionClicked = {},
                label = stringResource(id = R.string.nameTextFieldLable)
            )
            TextButton(onClick = {
                if (textFieldValue.isNotEmpty()) {
                    ThisUser.name = textFieldValue
                    viewModel.updateFirstEnterValue(context, false)
                    onReadyForNavigateToHomeScreen()
                } else Toast.makeText(
                    context,
                    context.getString(R.string.textFieldIsEmpty_cution),
                    Toast.LENGTH_LONG
                ).show()
            }) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.save),
                    style = DefaultTextStyle(
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        color = blue
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GetUserInformationPreview() {
    GetUserInformationScreen {

    }
}