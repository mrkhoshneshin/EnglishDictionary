package english.dictionary.app.screen.book

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import english.dictionary.app.data.Book
import english.dictionary.app.ui.common.Header
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.backgroundColor
import english.dictionary.app.R

@Composable
fun BooksScreen(books: List<Book>, onBookItemClicked: (Book) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp),
                title = stringResource(id = R.string.books),
                leftIcon = null,
                rightIcon = null,
                onLeftIconClicked = { /*TODO*/ },
                onRightIconClicked = {},
                greetingTitle = false
            )
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.stack_of_books),
                contentDescription = "booksStack"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                for (book in books) {
                    BookItem(
                        book = book,
                        onBookItemClicked = { clickedBook -> onBookItemClicked(clickedBook) })
                }
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onBookItemClicked: (Book) -> Unit) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 6.dp, top = 6.dp, start = 15.dp, end = 15.dp)
        .height(150.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable { onBookItemClicked(book) }) {

        val (image, text) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            elevation = 0.dp,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = book.image),
                contentDescription = "bookImage",
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(6.dp)
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
                .constrainAs(text) {
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                },
            text = book.name,
            style = DefaultTextStyle(
                fontSize = MaterialTheme.typography.body2.fontSize,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}

fun books() = listOf(
    Book(
        1,
        name = "X-ray Diffraction Crystallography",
        url = "https://drive.google.com/file/d/1NjPRX6yCegoy6jKMe9d8g6htmS4S6toL/view?usp=sharing",
        image = R.drawable.xray_diffraction
    ),
    Book(
        2,
        name = "Phase Transformations in metals and alloys",
        url = "https://drive.google.com/file/d/1t8iNVcqOqCqsfQBTAaKKTBZ5xHfoGnvp/view?usp=sharing",
        image = R.drawable.phase_transformations
    ),
    Book(
        3,
        name = "Metal forming",
        url = "https://drive.google.com/file/d/1E_EyoKEU3CjQtBlqfcjv31EE0ddNGX8a/view?usp=sharing",
        image = R.drawable.metal_forming
    ),
    Book(
        4,
        name = "Fundamentals of Materials Science and Engineering",
        url = "https://drive.google.com/file/d/1q2npPB3M1320TdyC1BX0USq0MLXOiDJi/view?usp=sharing",
        image = R.drawable.fundamentals_of_materials
    ),
)