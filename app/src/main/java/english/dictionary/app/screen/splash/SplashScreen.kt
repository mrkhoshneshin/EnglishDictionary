package english.dictionary.app.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.delay
import english.dictionary.app.R
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue1
import english.dictionary.app.ui.theme.blue2
import english.dictionary.app.ui.theme.blue3

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
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        blue3,
                        blue2,
                        blue1,
                        Color.White,
                        Color.White,
                        Color.White,
                        Color.White,
                        Color.White,
                        Color.White,
                        Color.White,
                        Color.White,
                        blue1,
                        blue2,
                        blue3
                    ), tileMode = TileMode.Repeated,
                )
            )
    ) {
        val (icon, title, desc) = createRefs()
        Icon(
            modifier = Modifier
                .size(160.dp)
                .alpha(alpha)
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 12.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "icon",
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(id = R.string.expert_dictionary),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(icon.bottom, margin = 6.dp)
                start.linkTo(icon.start)
                end.linkTo(icon.end)
            },
            style = DefaultTextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier.constrainAs(desc) {
                top.linkTo(title.bottom, margin = 4.dp)
                start.linkTo(title.start)
                end.linkTo(title.end)
            }, text = stringResource(id = R.string.materials_and_metallurgy_engineers),
            style = DefaultTextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )

    }
}

val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}