package com.template.nanamare.custom

import android.view.WindowManager
import com.template.nanamare.R
import com.template.nanamare.base.ui.BaseDialogFragment
import com.template.nanamare.databinding.LoadingDialogBinding


class LoadingDialog
    : BaseDialogFragment<LoadingDialogBinding>(R.layout.loading_dialog) {

    companion object {
        fun newInstance() = LoadingDialog()
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val windowParams = window!!.attributes
        windowParams.dimAmount = 0.0f
        windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        window.attributes = windowParams
    }

}