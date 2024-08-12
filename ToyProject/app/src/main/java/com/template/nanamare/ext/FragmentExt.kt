package com.template.nanamare.ext

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import org.jetbrains.anko.internals.AnkoInternals

fun Fragment.replaceFragmentInFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun Fragment.addFragmentInFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.transact {
        add(frameId, fragment)
    }
}

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    AnkoInternals.internalStartActivity(requireActivity(), T::class.java, params)
}

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
    AnkoInternals.createIntent(requireActivity(), T::class.java, params)