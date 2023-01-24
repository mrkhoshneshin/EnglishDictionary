package english.dictionary.app.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.material.slider.Slider
import english.dictionary.app.R
import english.dictionary.app.data.Feature
import english.dictionary.app.data.User
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue

@ExperimentalPagerApi
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var searchBoxState by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 70.dp)
            .fillMaxSize()
    ) {
        GreetingTextSection(
            userName = viewModel.getUserName().collectAsState(initial = "").value,
            onMenuIconClicked = {},
            onSpeechIconClicked = {}
        )
        //TODO add real list here
        ViewPagerSlider()
        SearchBoxSection(
            textFieldValue = searchBoxState,
            onTextFieldTextChanged = { searchBoxState = it },
            onSearchIconClicked = { /*TODO*/ }) {
        }
        FeatureSection(features = viewModel.getFeatures(), onFeatureItemClicked = {})
        UsersListSection(users = viewModel.getUsers(), onShowMoreButtonClicked = {})
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(viewModel = hiltViewModel())
}

@Composable
fun GreetingTextSection(
    modifier: Modifier = Modifier,
    userName: String = "",
    menuIcon: Int = R.drawable.menu,
    speechIcon: Int = R.drawable.microphone,
    onMenuIconClicked: () -> Unit,
    onSpeechIconClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable { onMenuIconClicked() },
                painter = painterResource(id = menuIcon),
                contentDescription = "menu",
                tint = blue
            )
            Text(text = "Hi $userName, ", style = DefaultTextStyle(fontWeight = FontWeight.Bold))
            Text(text = "Good Midnight", style = DefaultTextStyle())
        }
        Icon(
            painter = painterResource(id = speechIcon),
            contentDescription = "speechIcon",
            Modifier
                .size(24.dp)
                .clickable { onSpeechIconClicked() },
            tint = blue
        )
    }
}

@Composable
fun SearchBoxSection(
    modifier: Modifier = Modifier,
    label: String = "Search something",
    selectedStrokeColor: Color = blue,
    icon: Int = R.drawable.search,
    iconTint: Color = blue,
    textFieldValue: String,
    onTextFieldTextChanged: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    onKeyBoardOptionClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = textFieldValue,
            label = { Text(text = label, style = DefaultTextStyle(), color = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "searchIcon",
                    tint = blue
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {}),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            onValueChange = { onTextFieldTextChanged(it) })
    }
}


@Composable
fun FeatureSection(features: List<Feature>, onFeatureItemClicked: (Feature) -> Unit) {
    LazyVerticalGrid(modifier = Modifier.height(150.dp), columns = GridCells . Fixed (2), content = {
        items(features.size) {
            FeatureItem(
                title = features[it].title,
                onFeatureItemClicked = { onFeatureItemClicked(features[it]) })
        }
    }, contentPadding = PaddingValues(all = 15.dp))
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
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "arrow_right"
            )
        }
    }
}

//should change images type when api is ready
@Composable
fun UsersItem(
    image: Int = R.drawable.user_scan,
    smallImage: Int? = null,
) {
    Image(
        modifier = Modifier
            .height(150.dp)
            .width(90.dp)
            .padding(start = 5.dp, end = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.DarkGray),
        painter = painterResource(id = image),
        contentDescription = "userImage",
        contentScale = ContentScale.Fit
    )
}

@Composable
fun UsersListSection(
    users: List<User>,
    title: String = "Joined same as you",
    seeMoreButtonText: String = "See more",
    onShowMoreButtonClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
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
                    style = DefaultTextStyle(fontSize = MaterialTheme.typography.body2.fontSize)
                )
            }
        }
        LazyRow(
            contentPadding = PaddingValues(15.dp)
        ) {
            items(users.size) {
                UsersItem()
            }
        }
    }
}