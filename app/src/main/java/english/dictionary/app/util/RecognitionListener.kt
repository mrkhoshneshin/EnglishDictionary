package english.dictionary.app.util

import android.os.Bundle
import android.speech.SpeechRecognizer
import android.util.Log

abstract class RecognitionListener: android.speech.RecognitionListener {

    private val TAG = "RecognitionListener"
    
    override fun onReadyForSpeech(params: Bundle?) {
        Log.i(TAG, "onReadyForSpeech: ")
    }

    override fun onBeginningOfSpeech() {
        Log.i(TAG, "onBeginningOfSpeech: ")
    }

    override fun onRmsChanged(rmsdB: Float) {
        Log.i(TAG, "onRmsChanged: ")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        Log.i(TAG, "onBufferReceived: ")
    }

    override fun onEndOfSpeech() {
        Log.i(TAG, "onEndOfSpeech: ")
    }

    override fun onError(error: Int) {

        Log.i(TAG, "onError: $error")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        Log.i(TAG, "onPartialResults: ")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        Log.i(TAG, "onEvent: ")
    }
}