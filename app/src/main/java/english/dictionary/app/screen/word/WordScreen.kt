package english.dictionary.app.screen.word

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import english.dictionary.app.R
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.theme.DefaultTextStyle

//TODO implement receiving word scenario
@Composable
fun WordScreen(viewModel: WordDetailViewModel = hiltViewModel()) {
    val userName = viewModel.getUserName()
    val word = viewModel.getWord()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Header(
            modifier = Modifier.padding(bottom = 32.dp),
            headerTitle = "Hi $userName, Good midnight",
            leftIcon = R.drawable.menu,
            rightIcon = R.drawable.heart_outlined,
            onLeftIconClicked = { },
            onRightIconClicked = {})
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = word.englishTitle,
            style = DefaultTextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = word.englishDescription,
            style = DefaultTextStyle()
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = word.persianTitle,
            style = DefaultTextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = word.persianDescription,
            style = DefaultTextStyle(),
            textAlign = TextAlign.End
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.DarkGray)
        ) {
            //TODO change this box to image
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WordDetailPreview() {
    WordScreen()
}