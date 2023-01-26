package english.dictionary.app.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import english.dictionary.app.R
import english.dictionary.app.ui.theme.DefaultTextStyle

@Composable
fun AnimatedSplashScreen(onTimeOvered: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000L)
        onTimeOvered()
    }
    SplashScreen(alpha = alphaAnim.value)
}

//TODO change icon
@Composable
fun SplashScreen(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(160.dp)
                    .alpha(alpha)
                    .padding(bottom = 12.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "icon",
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(id = R.string.expert_dictionary),
                style = DefaultTextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(id = R.string.materials_and_metallurgy_engineers),
                style = DefaultTextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }

    }
}