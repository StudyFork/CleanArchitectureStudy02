package com.template.nanamare.base.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.template.nanamare.R

abstract class BaseFullSheetDialogFragment<B : ViewDataBinding>(private val layoutId: Int)
    : DialogFragment() {

    lateinit var binding: B

    var onClickListener: ((position: Int, text: String) -> Unit)? = null

    var onDismissListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this

        dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, android.R.color.background_light))

        dialog?.setOnShowListener { dialog ->
            val d = dialog as Dialog
            d.setOnKeyListener { _, keyCode, _ ->                                                   // 백버튼 눌렸을 시 X 버튼과 동일 효과 주기
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    onCloseClick()
                    true
                } else {
                    false
                }
            }
        }

        dialog?.setCanceledOnTouchOutside(false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissListener?.invoke()
        super.onDismiss(dialog)
    }

    /*
    TODO 체크 필요
    override fun dismiss() {
        onDismissListener?.invoke()
        super.dismiss()
    }
    */

    fun onCloseClick() {
        dismiss()
    }

}