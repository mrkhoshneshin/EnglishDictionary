package english.dictionary.app.screen.home

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import english.dictionary.app.util.NewWebView
import english.dictionary.app.R
import english.dictionary.app.ui.theme.DefaultTextStyle

@Composable
fun WebViewScreen(
    url: String,
) {
    var showLoading by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()) {
        if (showLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f)
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.please_wait),
                    style = DefaultTextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        color = Color.White
                    )
                )
            }
        }
        AndroidWebView(
            url = url,
            context = LocalContext.current,
            mustShowLoading = { mustShow -> showLoading = mustShow })
    }
}

@Composable
fun AndroidWebView(url: String, context: Context, mustShowLoading: (Boolean) -> Unit) {
    AndroidView(modifier = Modifier.fillMaxSize(), factory = {
        NewWebView(it).apply {
            enableCrossdomain41()
            settings.allowUniversalAccessFromFileURLs = true
            settings.allowUniversalAccessFromFileURLs = true
            settings.javaScriptEnabled = true
            loadUrl(url)
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    mustShowLoading(true)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    mustShowLoading(false)
                }
            }
        }
    })
}