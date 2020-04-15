package com.egiwon.moviesearch.ui.preview

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.egiwon.moviesearch.ui.detail.MovieDetailActivity.Companion.KEY_TRAILER

class PreviewDialog : DialogFragment() {

    private val key by lazy {
        arguments?.get(KEY_TRAILER)?.let {
            (it as? String) ?: ""
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = WebView(requireActivity())

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(view)
            .create()

        view.settings.javaScriptEnabled = true
        view.webViewClient = WebViewClient()

        if (!key.isNullOrBlank()) {
            view.loadUrl("https://www.themoviedb.org/video/play?key=${key}&height=300")
        }

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}