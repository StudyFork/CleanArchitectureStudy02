package com.template.nanamare.ext

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.template.nanamare.ui.anim.TextViewIntAnimation
import org.jetbrains.anko.textColorResource
import java.util.*

@BindingAdapter(value = ["animInt"])
fun TextView.setAnimInt(value: Int) {
    startAnimation(TextViewIntAnimation(this, to = value))
}

@BindingAdapter(value = ["dateTimeAgo"])
fun TextView.setDateTimeAgo(date: Date?) {
    date ?: return
    val now = Date().time
    val createdAt = date.time
    text = with((now - createdAt) / 1000L) {
        when {
            this < 60 -> {
                //초
                String.format("%1\$d 초 전", this)
            }
            this / 60 < 60 -> {
                // 분
                String.format("%1\$d 분 전", this / 60)
            }
            this / 3600 < 24 -> {
                // 시
                String.format("%1\$d 시 전", this / 3600)
            }
            this / 86400 < 30 -> {
                // 일
                String.format("%1\$d 일 전", this / 86400)
            }
            this / 86400 / 30 < 12 -> {
                // 월
                String.format("%1\$d 월 전", this / 86400 / 30)
            }
            else -> {
                // 년
                String.format("%1\$d 년 전", this / 86400 / 30 / 12)
            }
        }
    }
}

@BindingAdapter(value = ["remitTypeName", "remitDisplayName"])
fun TextView.setRemitTypeText(remitTypeName: String?, remitDisplayName: String?) {
    remitTypeName ?: return
    context.run {
        text = if (remitDisplayName.isNullOrEmpty()) {
            remitTypeName
        } else {
            String.format("%s - %s", remitTypeName, remitDisplayName)
        }
    }
}

@BindingAdapter(value = ["htmlText"])
fun TextView.setHtmlText(htmlText: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
}

@BindingAdapter(value = ["textColorResId"])
fun TextView.setTextColorResId(resId: Int) {
    textColorResource = resId
}