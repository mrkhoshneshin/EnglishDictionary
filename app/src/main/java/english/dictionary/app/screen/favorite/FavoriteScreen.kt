package english.dictionary.app.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue
import english.dictionary.app.R
import english.dictionary.app.ui.theme.backgroundColor

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    searchBox: @Composable () -> Unit,
    alphabets: @Composable () -> Unit,
    words: @Composable () -> Unit,
    emptyState: Boolean = true,
    viewModel: FavoriteWordsViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = modifier
                .padding(15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            header()
            if (emptyState) {
                EmptyState(
                    icon = R.drawable.no_favorite_item,
                    desc = stringResource(id = R.string.emptyStateDesc)
                )
            } else {
                searchBox()
                Spacer(modifier = modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    words()
                    alphabets()
                }
            }
        }
    }
}

@Composable
fun EmptyState(
    icon: Int,
    desc: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "emptyStateIcon",
                Modifier.size(200.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = desc,
                style = DefaultTextStyle(color = Color.DarkGray),
                textAlign = TextAlign.Center
            )
        }
    }
}