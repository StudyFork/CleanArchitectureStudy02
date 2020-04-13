package com.egiwon.moviesearch.ui.preview

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class PreviewDialog : DialogFragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = WebView(requireActivity())

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(view)
            .create()

        view.settings.javaScriptEnabled = true
        view.webViewClient = WebViewClient()
        view.loadUrl("https://www.themoviedb.org/video/play?key=t6g0dsQzfqY&height=300")

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}