package com.template.nanamare.base.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.nanamare.R
import com.template.nanamare.ext.replaceFragmentInActivity

/**
 * Fragment를 보여주기 위한 Activity
 */
abstract class BaseFragmentBindingActivity : AppCompatActivity() {
    protected lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_fragment_binding_activity)
        fragment = createFragment().apply {
            replaceFragmentInActivity(this, R.id.content_frame)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun createFragment(): Fragment

}