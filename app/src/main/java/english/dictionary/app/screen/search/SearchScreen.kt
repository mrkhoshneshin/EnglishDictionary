package english.dictionary.app.screen.search

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.Word
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.common.SearchBox
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue
import english.dictionary.app.ui.theme.blueLight
import english.dictionary.app.util.Alphabet
import english.dictionary.app.util.RecognitionListener


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onWordItemClicked: (Word) -> Unit,
    showShowRuntimePermission_voiceRecord: () -> Unit
) {
    val userName = viewModel.getUserName()
    var textFiledValue by remember { mutableStateOf("") }
    var clickedAlphabetic by remember { mutableStateOf("") }
    var recognitionStateDialog by remember { mutableStateOf(false) }
    //What user have spoken in microphone ( speech dialog )
    var speechText by remember { mutableStateOf("") }
    var isSpeechListening by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val words = viewModel.wordState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 56.dp)
    ) {
        if (recognitionStateDialog)
            RecognitionDialog(
                onDialogDismissed = { },
                onSpeechIconClicked = {
                },
                speechText = speechText,
                onStopRecognitionClicked = { text ->
                    stopRecognition(context)
                    textFiledValue = text
                    recognitionStateDialog = false
                }
            )

        Header(
            headerTitle = "Hi $userName, Good midnight",
            leftIcon = R.drawable.menu,
            rightIcon = R.drawable.microphone,
            onLeftIconClicked = {},
            onRightIconClicked = {
                requestPermission(
                    context,
                    shouldShowRuntimePermission = { showShowRuntimePermission_voiceRecord() })
                recognitionStateDialog = true
                startRecognition(
                    context,
                    speechText = { speechText = it },
                )
            },
            modifier = Modifier.padding(bottom = 12.dp)
        )
        SearchBox(
            textFieldValue = textFiledValue,
            onTextFieldTextChanged = {
                textFiledValue = it
                viewModel.onTextFieldTextChanged(it)
            },
            onSearchIconClicked = { /*TODO*/ }) {
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            WordsList(
                words = words,
                onWordItemClicked = { onWordItemClicked(it) })
            AlphabeticSection(
                onCharacterItemChanged = {
                    viewModel.onAlphabeticCharItemClicked(it)
                    clickedAlphabetic = it
                }, selectedChar = clickedAlphabetic
            )
        }
    }
}

private fun requestPermission(context: Context, shouldShowRuntimePermission: () -> Unit) {
    when {
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.RECORD_AUDIO
        ) != PackageManager.PERMISSION_GRANTED -> {
            shouldShowRuntimePermission()
        }
    }
}

private fun startRecognition(
    context: Context,
    speechText: (String) -> Unit,

    ) {
    val recognizer = SpeechRecognizer.createSpeechRecognizer(context)
    val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }
    recognizer.startListening(recognizerIntent)
    recognizer.setRecognitionListener(object : RecognitionListener() {
        override fun onResults(results: Bundle?) {
            val transcribedText =
                results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.get(0)
            if (transcribedText != null) {
                speechText(transcribedText)
            }
        }
    })

}

private fun stopRecognition(context: Context) {
    val recognizer = SpeechRecognizer.createSpeechRecognizer(context)
    recognizer.cancel()
}

@Composable
fun RecognitionDialog(
    onDialogDismissed: () -> Unit,
    onSpeechIconClicked: () -> Unit,
    onStopRecognitionClicked: (String) -> Unit,
    speechText: String = ""
) {

    Dialog(onDismissRequest = { onDialogDismissed() }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .height(300.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.microphone),
                    contentDescription = "speechIcon",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { onSpeechIconClicked() }
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = speechText.ifEmpty { "..." },
                    style = DefaultTextStyle()
                )
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(blue),
                    onClick = { onStopRecognitionClicked(speechText) }) {
                    Text(
                        text = stringResource(id = R.string.stopRecognition),
                        style = DefaultTextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecognitionDialogPreview() {
    RecognitionDialog(
        onDialogDismissed = { /*TODO*/ },
        onStopRecognitionClicked = {},
        onSpeechIconClicked = {})
}

@Composable
fun AlphabeticSection(
    modifier: Modifier = Modifier,
    onCharacterItemChanged: (String) -> Unit,
    selectedChar: String
) {
    val interactionSource = MutableInteractionSource()

    LazyColumn(modifier = modifier) {

        items(Alphabet.getAsList().size) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(3.dp)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        onCharacterItemChanged(Alphabet.getAsList()[it].toString())
                    }
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (selectedChar == Alphabet.getAsList()[it].toString()) blueLight else Color.Transparent)
            ) {
                Text(
                    text = Alphabet.getAsList()[it].toString(),
                    style = DefaultTextStyle(
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        color = if (selectedChar == Alphabet.getAsList()[it].toString()) blue else Color.Black
                    ),
                )
            }
        }
    }
}

@Composable
fun WordsList(
    modifier: Modifier = Modifier,
    words: List<Word>,
    onWordItemClicked: (Word) -> Unit
) {
    LazyColumn(modifier = modifier.width(300.dp)) {
        items(words.size) {
            Text(
                modifier = modifier
                    .padding(3.dp)
                    .clickable { onWordItemClicked(words[it]) },
                text = words[it].englishDescription,
                style = DefaultTextStyle(fontSize = MaterialTheme.typography.h6.fontSize),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WordListPreview(viewModel: SearchViewModel = hiltViewModel()) {
    val words = viewModel.wordState.collectAsState().value
    WordsList(words = words, onWordItemClicked = {})
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    SearchScreen(onWordItemClicked = {}, showShowRuntimePermission_voiceRecord = {})
}
