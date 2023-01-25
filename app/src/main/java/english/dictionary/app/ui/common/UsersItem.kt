package english.dictionary.app.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import english.dictionary.app.R

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