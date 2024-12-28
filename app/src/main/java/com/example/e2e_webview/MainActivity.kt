package com.example.e2e_webview

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var view: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ENABLE_EDGE_TO_EDGE) {
            // setDecorFitsSystemWindows(false) is the minimal reproducer, but the actual use case is as follows
            enableEdgeToEdge()
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                WindowInsetsCompat.CONSUMED
            }
//            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
        view = WebView(this)
        view.settings.javaScriptEnabled = true
        view.loadUrl("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input")
        setContentView(view)
    }

    companion object {

        // change this to toggle edge-to-edge
        private const val ENABLE_EDGE_TO_EDGE = true
    }
}
