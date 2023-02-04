package english.dictionary.app.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import english.dictionary.app.R
import english.dictionary.app.data.Book
import english.dictionary.app.data.Feature
import english.dictionary.app.data.User
import english.dictionary.app.data.Word
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.common.CustomTextField
import english.dictionary.app.ui.common.UsersItem
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.backgroundColor
import english.dictionary.app.ui.theme.blue
import english.dictionary.app.util.getLocale
import java.util.*

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onProfileIconClicked: () -> Unit,
    onFeatureItemClicked: (Feature) -> Unit,
    onPageItemClicked: (Book) -> Unit
) {
    var searchBoxState = viewModel.textFieldValue.collectAsState()
    val searchHistoryList = viewModel.searchedHistoryWords.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Header(
                modifier = Modifier.padding(15.dp),
                title = viewModel.getUserName().collectAsState(initial = "").value,
                greetingTitle = true,
                leftIcon = R.drawable.menu,
                rightIcon = R.drawable.user,
                onLeftIconClicked = {},
                onRightIconClicked = { onProfileIconClicked() }
            )
            //TODO add real list here
            Pager(onPagerItemClicked = { bookItem -> onPageItemClicked(bookItem) })
            CustomTextField(
                modifier = Modifier.padding(15.dp),
                textFieldValue = searchBoxState.value,
                onTextFieldTextChanged = { viewModel.updateTextFieldValue(it) },
                onSearchIconClicked = { /*TODO*/ },
                label = stringResource(id = R.string.searchSomething)
            ) {
            }
            FeatureSection(
                features = viewModel.getFeatures(),
                onFeatureItemClicked = {
                    onFeatureItemClicked(it)
                }
            )
            // UsersListSection(users = viewModel.getUsers(), onShowMoreButtonClicked = {})
            if (searchHistoryList.size != 0) {
                SearchHistorySection(words = searchHistoryList, onWordItemClicked = {})
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(viewModel = hiltViewModel(), onProfileIconClicked = {}, onFeatureItemClicked = {}, onPageItemClicked = {})
}

@Composable
fun FeatureSection(features: List<Feature>, onFeatureItemClicked: (Feature) -> Unit) {
    LazyVerticalGrid(modifier = Modifier.height(150.dp), columns = GridCells.Fixed(2), content = {
        items(features.size) {
            FeatureItem(
                title = features[it].title,
                onFeatureItemClicked = { onFeatureItemClicked(features[it]) })
        }
    }, contentPadding = PaddingValues(all = 15.dp))
}

@Composable
fun SearchHistorySection(words: List<Word>, onWordItemClicked: (Word) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {
        TitleShowMore(title = stringResource(id = R.string.history),
            seeMoreButtonText = stringResource(
                id = R.string.seeMore
            ),
            onShowMoreButtonClicked = {})
        LazyRow(
            contentPadding = PaddingValues(15.dp)
        ) {
            items(words.size) {
                HistoryItem(word = words[it], onItemClicked = { onWordItemClicked(words[it]) })
            }
        }
    }
}

@Composable
fun HistoryItem(
    word: Word,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onItemClicked() }, elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (word.englishTitle.isNullOrEmpty()) stringResource(id = R.string.notFound) else word.englishTitle,
                style = DefaultTextStyle(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (word.persianTitle.isNullOrEmpty()) stringResource(
                    id = R.string.notFound
                ) else word.persianTitle,
                style = DefaultTextStyle(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun FeatureItem(
    title: String,
    onFeatureItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onFeatureItemClicked() },
        elevation = 0.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .padding(bottom = 12.dp, top = 12.dp, start = 6.dp, end = 6.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = DefaultTextStyle(fontSize = MaterialTheme.typography.body2.fontSize)
            )
            Icon(
                modifier = Modifier.rotate(if (getLocale() == Locale.US) 0f else 180f),
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "arrow_right"
            )
        }
    }
}


@Composable
fun TitleShowMore(
    title: String = "Joined same as you",
    seeMoreButtonText: String = "See more",
    onShowMoreButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = DefaultTextStyle(fontWeight = FontWeight.Bold))
        TextButton(onClick = { onShowMoreButtonClicked() }, shape = RoundedCornerShape(16.dp)) {
            Text(
                text = seeMoreButtonText,
                style = DefaultTextStyle(
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    color = blue
                )
            )
        }
    }
}

//should change images type when api is ready
@Composable
fun UsersListSection(
    users: List<User>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TitleShowMore(title = "Joined same you", onShowMoreButtonClicked = {})
        LazyRow(
            contentPadding = PaddingValues(15.dp)
        ) {
            items(users.size) {
                UsersItem()
            }
        }
    }
}

