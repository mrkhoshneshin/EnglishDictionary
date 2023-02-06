package english.dictionary.app.screen.inputInformation

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.Education
import english.dictionary.app.ui.common.CustomTextField
import english.dictionary.app.ui.theme.*
import english.dictionary.app.util.getLocale
import java.util.*

@Composable
fun InputInformationScreen(
    viewModel: InputInformationViewModel = hiltViewModel(),
    onSaveButtonClicked: () -> Unit
) {
    var fullNameValue by remember { mutableStateOf("") }
    var universityValue by remember { mutableStateOf("") }
    var educationItemsVisibility by remember { mutableStateOf(false) }
    var educationValue by remember { mutableStateOf(Education.getAll()[1]) }
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
                text = stringResource(id = R.string.welcome),
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
                painter = painterResource(id = if (getLocale() == Locale.US) R.drawable.close_up_man else R.drawable.close_up_man_flip),
                contentDescription = "man",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.please) + "!",
            style = DefaultTextStyle(
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.fullName),
            style = DefaultTextStyle()
        )
        CustomTextField(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            label = "",
            leadingIcon = R.drawable.user,
            textFieldValue = fullNameValue,
            iconTint = Color.DarkGray,
            onTextFieldTextChanged = { fullNameValue = it },
            onSearchIconClicked = { }) {
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.education),
            style = DefaultTextStyle()
        )
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 15.dp, end = 15.dp)
                .clip(RoundedCornerShape(12.dp)),
            onClick = { educationItemsVisibility = !educationItemsVisibility }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.university),
                        tint = Color.DarkGray,
                        contentDescription = ""
                    )
                    Text(text = "  $educationValue", style = DefaultTextStyle())
                }
                Icon(
                    modifier = Modifier.rotate(90f), painter = painterResource(
                        id = R.drawable.arrow_right,
                    ), contentDescription = "icon", tint = Color.DarkGray
                )
            }
        }
        if (educationItemsVisibility) {
            Column(modifier = Modifier.padding(start = 15.dp, top = 10.dp)) {
                for (i in Education.getAll()) {
                    Text(
                        text = i,
                        style = DefaultTextStyle(color = if (educationValue.isNotEmpty() && educationValue == i) blue else Color.LightGray),
                        modifier = Modifier.clickable {
                            educationValue = i
                            educationItemsVisibility = false
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.university),
            style = DefaultTextStyle()
        )
        CustomTextField(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            label = "",
            leadingIcon = R.drawable.student_hat,
            textFieldValue = universityValue,
            iconTint = Color.DarkGray,
            onTextFieldTextChanged = { universityValue = it },
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
                    if (fullNameValue.isEmpty()) {
                        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
                    } else if (universityValue.isEmpty()) {
                        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.updateUserInformation(
                            fullNameValue,
                            educationValue,
                            universityValue
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

@Composable
@Preview(showBackground = true)
fun InputInformationPreview() {
    InputInformationScreen(onSaveButtonClicked = {})
}