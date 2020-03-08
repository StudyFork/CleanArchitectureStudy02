package com.template.nanamare.ext

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

@BindingAdapter(value = ["decimalTextAttrChanged"])
fun EditText.setDecimalTextListener(listener: InverseBindingListener) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener.onChange()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

@BindingAdapter(value = ["decimalText"])
fun EditText.setIntValue(value: Int?) {
    // 전역변수로 들고 있으면 언어 변경시 반영이 안되므로 수시로 생성해서 사용
    val decimalFormat = DecimalFormat("#,##0")
    val nonNullValue = value ?: 0

    val strValue = decimalFormat.format(nonNullValue)
    if (text.toString() == strValue) {
        return
    }

    var selectionPosition = selectionEnd + (strValue.length - text.length)
    if (selectionPosition < 0) {
        selectionPosition = 1
    }
    setText(strValue)
    setSelection(selectionPosition)
}


@InverseBindingAdapter(attribute = "decimalText")
fun EditText.getIntValue(): Int {
    // 전역변수로 들고 있으면 언어 변경시 반영이 안되므로 수시로 생성해서 사용
    val groupingSeparator = DecimalFormatSymbols.getInstance().groupingSeparator // 천단위 기호
    return text.toString().replace(Regex("[$groupingSeparator]"), "").let {
        if (it.isEmpty()) {
            0
        } else {
            try {
                return@let it.toInt()
            } catch (e: Exception) {
                e.printStackTrace()
                return@let 0
            }
        }
    }
}

@BindingAdapter(value = ["doubleTextAttrChanged"])
fun EditText.setDoubleTextListener(listener: InverseBindingListener) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener.onChange()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

val doubleFormat = DecimalFormat("#,##0.##")
@BindingAdapter(value = ["doubleText"])
fun EditText.setDoubleValue(value: Double?) {
    val nonNullValue = value ?: .0

    val strValue = doubleFormat.format(nonNullValue)
    if (text.toString() == strValue) {
        return
    }

    var selectionPosition = selectionEnd + (strValue.length - text.length)
    if (selectionPosition < 0) {
        selectionPosition = 1
    }
    setText(strValue)
    setSelection(selectionPosition)
}

@InverseBindingAdapter(attribute = "doubleText")
fun EditText.getDoubleValue(): Double {
    return text.toString().replace(",", "").let {
        if (it.isEmpty()) {
            .0
        } else {
            try {
                return@let it.toDouble()
            } catch (e: Exception) {
                return@let .0
            }
        }
    }
}

fun EditText.limitLength(maxLength: Int) {
    filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
}

fun addAtomicErrorStr(errorDesc: String, addErrorDesc: String, isSpecialStr: Boolean = false): String {
    var newErrorDesc = errorDesc
    when (isSpecialStr) {
        true -> {
            when {
                !newErrorDesc.contains(addErrorDesc) -> newErrorDesc += addErrorDesc
            }
        }
        false -> {
            when {
                !newErrorDesc.contains(addErrorDesc) -> {
                    newErrorDesc += if (newErrorDesc.isEmpty()) {
                        addErrorDesc
                    } else {
                        ", $addErrorDesc"
                    }
                }
            }
        }
    }
    return newErrorDesc
}
