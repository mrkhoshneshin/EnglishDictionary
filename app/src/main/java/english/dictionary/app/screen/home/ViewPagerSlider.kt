package english.dictionary.app.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(viewModel: HomeViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState(initialPage = 0)
//    LaunchedEffect(Unit) {
//        while (true) {
//            yield()
//            delay(2000L)
//            pagerState.animateScrollToPage(
//                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
//                pageOffset = 1f
//            )
//        }
//    }

    Column(modifier = Modifier
        .fillMaxSize()
        .size(200.dp)) {

        HorizontalPager(
            count = viewModel.getSliders().size,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(0.dp, 12.dp, 0.dp, 12.dp),
            state = pagerState,
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 25.dp, 0.dp),
                shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
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

