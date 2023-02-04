package english.dictionary.app.screen.home

import android.util.LayoutDirection
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(viewModel: HomeViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .size(200.dp)
    ) {

        HorizontalPager(
            count = viewModel.getSliders().size,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(0.dp, 12.dp, 0.dp, 12.dp),
            state = pagerState,

            contentPadding = PaddingValues(end = 50.dp)
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationX = if(pagerState.targetPage != page) 80f else 0f
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    },
                shape = RoundedCornerShape(
                    topStart = if (pagerState.targetPage == page && !pagerState.isScrollInProgress) 0.dp else 20.dp,
                    bottomStart = if (pagerState.targetPage == page && !pagerState.isScrollInProgress) 0.dp else 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                        .align(CenterHorizontally)
                ) {
                    // Image(painter = painterResource(id =  ), contentDescription = )
                }
            }
        }
    }
}