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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import english.dictionary.app.R
import english.dictionary.app.data.Word
import english.dictionary.app.data.WordDetailData
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.common.CustomTextField
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue
import english.dictionary.app.ui.theme.blueLight
import english.dictionary.app.util.Alphabet
import english.dictionary.app.util.RecognitionListener
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onWordItemClicked: (Word) -> Unit,
    showShowRuntimePermission_voiceRecord: () -> Unit
) {
    val userName = viewModel.getUserName()
    val textFiledValue = viewModel.textFieldState.collectAsState()
    var clickedAlphabetic = viewModel.alphabeticItemSelectedState.collectAsState()
    var recognitionStateDialog by remember { mutableStateOf(false) }
    var onBeginningOfSpeech by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val words = viewModel.wordState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 56.dp)
    ) {
        if (recognitionStateDialog) {
            RecognitionDialog(
                onDialogDismissed = {
                    stopRecognition(context)
                    recognitionStateDialog = false
                },
                onSpeechIconClicked = {},
                onBeginningOfSpeech = onBeginningOfSpeech,
                speechText = if (onBeginningOfSpeech) stringResource(id = R.string.listening) else stringResource(
                    id = R.string.talkNow
                )
            )
        }

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
                    speechText = {
                        stopRecognition(context)
                        viewModel.updateTextFieldValue(it)
                        onBeginningOfSpeech = false
                        recognitionStateDialog = false
                    },
                    onBeginningOfSpeech = { onBeginningOfSpeech = true }
                )
            },
            modifier = Modifier.padding(bottom = 12.dp)
        )
        CustomTextField(
            textFieldValue = textFiledValue.value,
            onTextFieldTextChanged = {
                viewModel.updateTextFieldValue(it)
            },
            onSearchIconClicked = { /*TODO*/ }) {
        }
        Text(
            text = words.size.toString(), modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(), color = blue
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            WordsList(
                words = words,
                onWordItemClicked = {
                    onWordItemClicked(it)
                    WordDetailData.word = it
                },
                scrollPosition = if (clickedAlphabetic.value.isEmpty()) null else words.indexOfFirst {
                    it.englishTitle?.startsWith(
                        clickedAlphabetic.value
                    ) ?: false
                })
            AlphabeticSection(
                onCharacterItemChanged = {
                    viewModel.onAlphabeticCharItemClicked(it)
                    viewModel.updateAlphabeticValue(it)
                }, selectedChar = clickedAlphabetic.value
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
    onBeginningOfSpeech: () -> Unit
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

        override fun onReadyForSpeech(params: Bundle?) = onBeginningOfSpeech()
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
    speechText: String = "",
    onBeginningOfSpeech: Boolean
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.equalizer))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Dialog(onDismissRequest = { onDialogDismissed() }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .height(300.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp)
            ) {
                if(!onBeginningOfSpeech)
                Icon(
                    painter = painterResource(id = R.drawable.microphone),
                    contentDescription = "speechIcon",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { onSpeechIconClicked() }
                )
                if (onBeginningOfSpeech)
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .size(100.dp)
                            .absolutePadding(top = 12.dp, bottom = 12.dp)
                    )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = speechText.ifEmpty { "..." },
                    style = DefaultTextStyle(fontSize = MaterialTheme.typography.body1.fontSize)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.networkConnectionNeeded),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = DefaultTextStyle(
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        color = Color.LightGray
                    )
                )
            }
        }
    }
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
    onWordItemClicked: (Word) -> Unit,
    scrollPosition: Int?
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LazyColumn(modifier = modifier.width(300.dp), state = listState) {
        items(words.size) {
            (if (words[it].englishTitle.isNullOrEmpty()) stringResource(id = R.string.notFound) else words[it].englishTitle)?.let { it1 ->
                Text(
                    modifier = modifier
                        .padding(3.dp)
                        .clickable { onWordItemClicked(words[it]) },
                    text = it1,
                    style = DefaultTextStyle(fontSize = MaterialTheme.typography.h6.fontSize),
                )
            }
        }
    }

    scrollPosition?.let {
        scope.launch {
            if (it >= 0) {
                listState.scrollToItem(it)
            }
        }
    }
}
