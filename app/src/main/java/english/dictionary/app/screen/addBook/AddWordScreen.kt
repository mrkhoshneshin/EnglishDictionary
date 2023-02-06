package english.dictionary.app.screen.addBook

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.Word
import english.dictionary.app.ui.common.CustomTextField
import english.dictionary.app.ui.theme.*

@Composable
fun AddWordScreen(
    viewModel: AddWordViewModel = hiltViewModel(),
    onSaveButtonClicked: () -> Unit
) {
    var wordEnglishTitle by remember { mutableStateOf("") }
    var wordPersianTitle by remember { mutableStateOf("") }
    var englishDesc by remember { mutableStateOf("") }
    var persianDesc by remember { mutableStateOf("") }
    val toastMessage = stringResource(id = R.string.checkEntries)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 100.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            blue1, blue2, blue3
                        )
                    )
                )
        ) {
            val (text, image) = createRefs()
            Text(
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top, margin = 40.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                },
                text = stringResource(id = R.string.addNewWord),
                style = DefaultTextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h4.fontSize
                )
            )
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .constrainAs(image) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                painter = painterResource(id = R.drawable.bookmark),
                contentDescription = "man",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.englishTitle),
            style = DefaultTextStyle()
        )
        CustomTextField(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            label = "",
            leadingIcon = null,
            textFieldValue = wordEnglishTitle,
            iconTint = Color.DarkGray,
            onTextFieldTextChanged = { wordEnglishTitle = it },
            onSearchIconClicked = { }) {
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.persianTitle),
            style = DefaultTextStyle()
        )
        CustomTextField(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            label = "",
            leadingIcon = null,
            textFieldValue = wordPersianTitle,
            iconTint = Color.DarkGray,
            onTextFieldTextChanged = { wordPersianTitle = it },
            onSearchIconClicked = { }) {
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.englishDesc),
            style = DefaultTextStyle()
        )
        CustomTextField(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            label = "",
            height = 150.dp,
            singleLine = false,
            leadingIcon = null,
            textFieldValue = englishDesc,
            iconTint = Color.DarkGray,
            onTextFieldTextChanged = { englishDesc = it },
            onSearchIconClicked = { }) {
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.persianDesc),
            style = DefaultTextStyle()
        )
        CustomTextField(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            label = "",
            singleLine = false,
            height = 150.dp,
            leadingIcon = null,
            textFieldValue = persianDesc,
            iconTint = Color.DarkGray,
            onTextFieldTextChanged = { persianDesc = it },
            onSearchIconClicked = { }) {
        }
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = blue),
                onClick = {
                    if (wordEnglishTitle.isEmpty() || wordPersianTitle.isEmpty() || englishDesc.isEmpty() || persianDesc.isEmpty()) {
                        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.addWord(
                            Word(
                                englishTitle = wordEnglishTitle,
                                persianTitle = wordPersianTitle,
                                englishDescription = englishDesc,
                                persianDescription = persianDesc,
                                image = null
                            )
                        )
                        onSaveButtonClicked()
                    }
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.saveAndContinue),
                    style = DefaultTextStyle(color = Color.White),
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}