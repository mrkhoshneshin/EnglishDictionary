package english.dictionary.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

private val DarkColorPalette = darkColors(
    primary = blue,
    primaryVariant = blue,
    secondary = blue
)

private val LightColorPalette = lightColors(
    primary = blue,
    primaryVariant = blue,
    secondary = blue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DefaultTextStyle(
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
    fontFamily: FontFamily = shabnam,
    fontWeight: FontWeight = FontWeight.Medium
): TextStyle {
    return TextStyle(
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        fontSize = fontSize
    )
}

@Composable
fun EnglishDictionaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}