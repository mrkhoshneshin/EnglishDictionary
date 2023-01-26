package english.dictionary.app.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.data.Word
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.common.SearchBox
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue
import english.dictionary.app.ui.theme.blueLight
import english.dictionary.app.util.Alphabet

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), onWordItemClicked: (Word) -> Unit) {
    val userName = viewModel.getUserName()
    var textFiledValue by remember { mutableStateOf("") }
    val words = viewModel.wordState.collectAsState().value
    var clickedAlphabetic by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 56.dp)
    ) {
        Header(
            headerTitle = "Hi $userName, Good midnight",
            leftIcon = R.drawable.menu,
            rightIcon = R.drawable.microphone,
            onLeftIconClicked = {},
            onRightIconClicked = {},
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
    SearchScreen(onWordItemClicked = {})
}
