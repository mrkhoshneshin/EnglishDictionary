package english.dictionary.app.screen.home

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(
    url: String
) {
    AndroidWebView(url = url, context = LocalContext.current)
}
@Composable
fun AndroidWebView(url: String, context: Context) {
    Box(modifier = Modifier.fillMaxSize()){
        AndroidView(factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.allowContentAccess = true
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        })
    }
}