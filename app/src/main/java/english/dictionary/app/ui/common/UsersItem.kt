package english.dictionary.app.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import english.dictionary.app.R

@Composable
fun UsersItem(
    image: Int = R.drawable.user_scan,
    secondImage: Int? = R.drawable.user_scan,
) {
    ConstraintLayout {
        val (largeImage, smallImage) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(largeImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(150.dp)
                .width(90.dp)
                .padding(start = 5.dp, end = 5.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.DarkGray),
            painter = painterResource(id = image),
            contentDescription = "largeImage",
            contentScale = ContentScale.Fit
        )
        if(secondImage != null){
            Image(
                painter = painterResource(id = secondImage),
                contentDescription = "smallImage",
                modifier = Modifier
                    .constrainAs(smallImage) {
                        top.linkTo(largeImage.bottom)
                        bottom.linkTo(largeImage.bottom)
                        start.linkTo(largeImage.start)
                        end.linkTo(largeImage.end)
                    }
                    .size(45.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.DarkGray)
            )
        }
    }
}